/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.controladores.usuario;

import es.uvigo.esei.dagss.facturaaas.controladores.AutenticacionController;
import es.uvigo.esei.dagss.facturaaas.daos.DatosFacturacionDAO;
import es.uvigo.esei.dagss.facturaaas.daos.FacturaDAO;
import es.uvigo.esei.dagss.facturaaas.daos.LineaFacturaDAO;
import es.uvigo.esei.dagss.facturaaas.daos.TipoIVADAO;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
import es.uvigo.esei.dagss.facturaaas.entidades.LineaFactura;
import es.uvigo.esei.dagss.facturaaas.entidades.TipoIVA;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author grvidal
 */
@Named(value = "lineaFacturaController")
@ViewScoped
public class LineaFacturaController implements Serializable {
   
    private Factura factura;
    private List<LineaFactura> lineas;
    private LineaFactura lineaActual;
    private boolean esNuevo;
    
    @Inject 
    private TipoIVADAO tipoIVADAO;
    
    @Inject
    private FacturaDAO facturaDAO;
    
    @Inject
    private LineaFacturaDAO dao;
    
    @Inject
    private AutenticacionController autenticacionController;
    
    @Inject
    private DatosFacturacionDAO dfDao;

    public Factura getFactura(){
        return factura;
    }

    public void setFactura(Factura n){
        this.factura = n;
    
    }    
    
    public void showLineas(){
        lineas = refrescarLista();
    }

    public List<LineaFactura> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaFactura> lineas) {
        this.lineas = lineas;
    }

    public LineaFactura getLineaActual() {
        return lineaActual;
    }

    public void setLineaActual(LineaFactura lineaActual) {
        this.lineaActual = lineaActual;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    public LineaFacturaDAO getDao() {
        return dao;
    }

    public void setDao(LineaFacturaDAO dao) {
        this.dao = dao;
    }
    
    @PostConstruct
    public void cargaInicial() {
        this.factura = this.autenticacionController.getFacturaActual();
        this.lineaActual = null;
        this.esNuevo = false;
        this.lineas = refrescarLista();
    }
    
    public List<TipoIVA> getTipoIVA(){
        return tipoIVADAO.buscarActivos();
    }
    
    public void doBuscarTodos() {
        this.lineas = refrescarLista();
    }
    
    public void doNuevo() {
        this.esNuevo = true;
        this.lineaActual = new LineaFactura();
        this.lineaActual.setNumeroDeFactura(factura);
        this.lineaActual.setCantidad(1);
        this.lineaActual.setIva(dfDao.buscarConPropietario(autenticacionController.getUsuarioLogueado()).getTipoIVAPorDefecto());
        
    }
    
    public void doEditar(LineaFactura linea) {
        this.esNuevo = false;
        this.lineaActual = linea;
    }
    
    public void doGuardarEditado() {
        float total = lineaActual.getCantidad() * lineaActual.getPrecio();
        total -= ( lineaActual.getDescuento() * total ) / 100;// aplicado el descuento
        
        total += (float) (lineaActual.getIva().getPorcentaje() * total) / 100;//aplicando iva
        this.lineaActual.setTotal( total );
        
        this.facturaDAO.actualizar(factura);
        if (this.esNuevo) {
            factura.setImporte( factura.getImporte() + total);//se actualiza el importe
            dao.crear(lineaActual);
        } else {
            
            LineaFactura lineaAux = dao.buscarPorClave(lineaActual.getId());
            total = factura.getImporte() - lineaAux.getTotal() + lineaActual.getTotal(); 
            factura.setImporte(total);
            dao.actualizar(lineaActual);
        }
        this.lineas = refrescarLista();
        this.lineaActual = null;
        this.esNuevo = false;
    }
    
    public void doBorrar(LineaFactura linea){
        dao.eliminar(linea);
        this.factura.setImporte( factura.getImporte() - linea.getTotal());
        this.lineas = this.refrescarLista();
    }
    
    public void doCancelarEditado() {
        this.lineaActual = null;
        this.esNuevo = false;
    }
    
    private List<LineaFactura> refrescarLista() {
        return dao.buscarTodasLineaFacturas(factura.getNumeroDeFactura());
    }
    
}
