/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.controladores.usuario;

import es.uvigo.esei.dagss.facturaaas.controladores.AutenticacionController;
import es.uvigo.esei.dagss.facturaaas.daos.DatosFacturacionDAO;
import es.uvigo.esei.dagss.facturaaas.daos.FacturaDAO;
import es.uvigo.esei.dagss.facturaaas.entidades.EstadoFactura;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "facturasController")
@ViewScoped
public class FacturasControler implements Serializable{
    
    private List<Factura> facturas;
    private Factura facturaActual;//se usa cuando se crea una nueva factura.
    private boolean esNuevo;
    private String textoBusqueda;

   
    @Inject
    private FacturaDAO dao;
    
    @Inject
    private DatosFacturacionDAO DFdao;

    @Inject
    private AutenticacionController autenticacionController;

        
    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFcturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Factura getFacturaActual() {
        return facturaActual;
    }

    public void setFacturaActual(Factura facturaActual) {
        this.facturaActual = facturaActual;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }
    
    

    @PostConstruct
    public void cargaInicial() {
        this.facturas = refrescarLista();
        this.facturaActual = null;
        this.esNuevo = false;
    }

    
    public void doBuscarPorNumeroDeFactura() {
        this.facturaActual = dao.buscarPorNumeroDeFactura(autenticacionController.getUsuarioLogueado(),
                textoBusqueda);
    }

    //TODO improve
    //Es relativamente pobre, no controla las busquedas de fechas inexistentes
    public void doBuscarPorFecha() {
        
        //String pattern = "(^\d{1,2}-\d{1,2}-[\d{2}\d{4}]$)";
        String pattern = "(^[0-9]{1,2}-[0-9]{1,2}-[[0-9]{2}[0-9]{4}]$)";
        if (textoBusqueda.matches(pattern)) {
            Calendar date = Calendar.getInstance();
            String[] fecha =  textoBusqueda.split("-");
            
            int year = Integer.parseInt(fecha[2]);
            //si el año solo tiene 2 digitos.
            if(year < 100 ){
                //si el año buscado es menor que los dos ultimos digitos del año actual, se busca 20xx
                if ( year < ( Calendar.getInstance().get(Calendar.YEAR) - 2000)){ 
                    year+= 2000;
                }
                //si el año buscado es mayor que el año actual pero solo tiene dos digitos se buca 19xx
                else if(year > ( Calendar.getInstance().get(Calendar.YEAR) - 2000)){
                    year+= 1900;
                }
            }
            
            date.set(Calendar.YEAR, year);
            date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fecha[0]));
            date.set(Calendar.MONTH, Integer.parseInt(fecha[1]));
            
            this.facturas = dao.buscarPorFecha(autenticacionController.getUsuarioLogueado(), date.getTime());
        }
        
        //la busqueda esta mal hecha, no se como avisar al usuario
        
    }
    
    public void doBuscarPorEstado(){
        switch (textoBusqueda) {
            case "ABONADA": this.facturas = dao.buscarPorEstado
                    (autenticacionController.getUsuarioLogueado(),EstadoFactura.ABONADA);
                break;
            case "ANULADA": this.facturas = dao.buscarPorEstado
                    (autenticacionController.getUsuarioLogueado(),EstadoFactura.ANULADA);
                break;
            case "EMITIDA": this.facturas = dao.buscarPorEstado
                    (autenticacionController.getUsuarioLogueado(),EstadoFactura.EMITIDA);
                break;
            case "PAGADA": this.facturas = dao.buscarPorEstado
                    (autenticacionController.getUsuarioLogueado(),EstadoFactura.PAGADA);
                break;
            case "RECLAMADA": this.facturas = dao.buscarPorEstado
                    (autenticacionController.getUsuarioLogueado(),EstadoFactura.RECLAMADA);
                break;
                
            default:
                
                throw new AssertionError();
        }
    }
    public void doBuscarTodos() {
        this.facturas = refrescarLista();
    }
    
    
    public void doNuevo() {
        this.esNuevo = true;
        this.facturaActual = new Factura();
        this.facturaActual.setNumeroDeFactura((long) dao.maxNumeroDeFactura()+1);//nuevo numero de factura
        this.facturaActual.setFecha(Calendar.getInstance().getTime());
        this.facturaActual.setEstado(EstadoFactura.EMITIDA);
        long id = autenticacionController.getUsuarioLogueado().getId();
        this.facturaActual.setFormaDePago(DFdao.buscarConPropietario(autenticacionController.getUsuarioLogueado()).getFormaPagoPorDefecto());
    }

    public void doEditar(Factura factura) {
        this.esNuevo = false;
        this.facturaActual = factura;
    }


    public void doGuardarEditado() {
        if (this.esNuevo) {
            dao.crear(facturaActual);
        } else {
            dao.actualizar(facturaActual);
        }
        this.facturas = refrescarLista();
        this.facturaActual = null;
        this.esNuevo = false;
    }

    public void doCancelarEditado() {
        this.facturaActual = null;
        this.esNuevo = false;
    }

    private List<Factura> refrescarLista() {
        return dao.buscarTodasFacturas(autenticacionController.getUsuarioLogueado());
    }

}
