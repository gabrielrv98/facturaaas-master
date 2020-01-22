/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.controladores.usuario;

import es.uvigo.esei.dagss.facturaaas.controladores.AutenticacionController;
import es.uvigo.esei.dagss.facturaaas.daos.FormaPagoDAO;
import es.uvigo.esei.dagss.facturaaas.daos.PagoDAO;
import es.uvigo.esei.dagss.facturaaas.entidades.EstadoPago;
import es.uvigo.esei.dagss.facturaaas.entidades.Pago;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "pagosController")
@ViewScoped
public class PagosController implements Serializable{
    
    private List<Pago> pagos;
    private Pago pagoAux;//se usa cuando se crea una nueva factura o se edta una
    private List<Pago> pagosFactura;
    private boolean esNuevo;
    private String textoBusqueda;

   
    @Inject
    private PagoDAO dao;
    
    @Inject
    private FormaPagoDAO FPdao;

    @Inject
    private AutenticacionController autenticacionController;

        
    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public Pago getPagoAux() {
        return pagoAux;
    }

    public void setPagoAux(Pago pagoAux) {
        this.pagoAux = pagoAux;
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
        this.pagos = refrescarLista();
        this.pagoAux = null;
        this.esNuevo = false;
    }

    public void doBuscarPorCliente() {
        this.pagos = dao.buscarPagosDeCliente(autenticacionController.getUsuarioLogueado());
        
    }
    
    public void doBuscarPorNumeroDeFactura() {
        this.pagoAux = dao.buscarPorNumeroDeFactura(autenticacionController.getUsuarioLogueado(),
                Long.parseLong(textoBusqueda));
    }

    //TODO improve
    //Es relativamente pobre, no controla las busquedas de fechas inexistentes
    public void doBuscarPorFechaVencimiento() {
        
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
            
            this.pagos = dao.buscarPorFechaVencimiento(autenticacionController.getUsuarioLogueado(), date.getTime());
        }
        
        //la busqueda esta mal hecha, no se como avisar al usuario
        
    }
    
    public void doBuscarPorEstado(){
        switch (textoBusqueda) {
            case "PENDIENTE": this.pagos = dao.buscarPorEstado
                    (autenticacionController.getUsuarioLogueado(),EstadoPago.PENDIENTE);
                break;
            case "PAGADO": this.pagos = dao.buscarPorEstado
                    (autenticacionController.getUsuarioLogueado(),EstadoPago.PAGADO);
                break;
            case "ANULADO": this.pagos = dao.buscarPorEstado
                    (autenticacionController.getUsuarioLogueado(),EstadoPago.ANULADO);
                break;
                
            default:
                
                throw new AssertionError();
        }
    }
    
    public void doNuevo(Factura factura) {
        this.esNuevo = true;
        this.pagosFactura = new LinkedList<>();
        Integer periodicidad = factura.getFormaDePago().getPeriodicidad();
        Integer nPagos = factura.getFormaDePago().getNumeroPagos();
            for (int i = 0; i < nPagos; i++){
                this.pagoAux = new Pago();
                this.pagoAux.setNumeroDeFactura(factura.getNumeroDeFactura());//nuevo numero de factura
                this.pagoAux.setCliente(factura.getCliente());
                this.pagoAux.setEstado(EstadoPago.PENDIENTE);
                this.pagoAux.setPropietario(factura.getPropietario());
                this.pagoAux.setFechavencimiento(sumarRestarDiasFecha(factura.getFecha(), factura.getFormaDePago().getPeriodicidad() * i));
                this.pagoAux.setImporte((int)factura.getImporte() / factura.getFormaDePago().getNumeroPagos());
                this.pagosFactura.add(pagoAux);
            }
    }
    
     private Date sumarRestarDiasFecha(Date fecha, Integer dias){
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fecha); // Configuramos la fecha que se recibe
      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
      return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
 }

    public void doEditar(Pago pago) {
        this.esNuevo = false;
        this.pagoAux = pago;
    }


    public void doGuardarEditado() {
        if (this.esNuevo) {
            while (pagosFactura != null && !pagosFactura.isEmpty()){
                dao.crear(pagosFactura.get(0));
                pagosFactura.remove(0);
            }
        } else {
            dao.actualizar(pagoAux);
        }
        this.pagos = refrescarLista();
        this.pagoAux = null;
        this.esNuevo = false;
    }

    public void doCancelarEditado() {
        this.pagoAux = null;
        this.esNuevo = false;
    }

    private List<Pago> refrescarLista() {
        return dao.buscarPagosDeCliente(autenticacionController.getUsuarioLogueado());
    }

}
