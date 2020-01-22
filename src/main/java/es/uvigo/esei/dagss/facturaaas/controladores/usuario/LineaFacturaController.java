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
   
    private Long nFactura;
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

    public Long getNFactura(){
        return nFactura;
    }

    public void setNFactura(Long n){
        this.nFactura = n;
        this.lineas = dao.buscarTodasLineaFacturas(n);
    
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
        
        //this.lineas = refrescarLista();
        this.lineaActual = null;
        this.esNuevo = false;
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
        
        
        Factura factura = facturaDAO.buscarPorClave(nFactura);
        
        if( factura == null){
            this.lineaActual.setNumeroDeFactura(null);
        }else this.lineaActual.setNumeroDeFactura(factura);
        
        
        this.lineaActual.setCantidad(5);
        this.lineaActual.setIva(dfDao.buscarConPropietario(autenticacionController.getUsuarioLogueado()).getTipoIVAPorDefecto());
        
    }
    
    public void doEditar(LineaFactura linea) {
        this.esNuevo = false;
        this.lineaActual = linea;
    }
    
    public void doGuardarEditado() {
        float total = lineaActual.getCantidad() * lineaActual.getPrecio();
        total -= ( lineaActual.getDescuento() * total ) / 100;
        this.lineaActual.setTotal( total );
        
        if (this.esNuevo) {
            dao.crear(lineaActual);
        } else {
            dao.actualizar(lineaActual);
        }
        this.lineas = refrescarLista();
        this.lineaActual = null;
        this.esNuevo = false;
    }
    
    public void doCancelarEditado() {
        this.lineaActual = null;
        this.esNuevo = false;
    }
    
    private List<LineaFactura> refrescarLista() {
        return dao.buscarTodasLineaFacturas(nFactura);
    }
    
}
