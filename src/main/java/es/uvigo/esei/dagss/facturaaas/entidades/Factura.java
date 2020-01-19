/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author grvidal
 */
@Entity
@Table(name = "FACTURA")
public class Factura implements Serializable{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroDeFactura;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    
    private String ejercicio;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario propietario;

    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name = "FORMA_PAGO_ID")
    private FormaPago formaDePago;

    @Enumerated(EnumType.STRING)
    private EstadoFactura estado;
    
    private String comentarios;
    
    private float importe;
    
    @ManyToOne
    @JoinColumn(name = "TIPO_IVA_ID")
    private TipoIVA iva;

    public Factura() {
    }

    public Factura(Long numeroDeFactura, String nombreCliente, Cliente cliente, String nifCliente, String ejercicio, 
            Usuario propietario, Date fecha, FormaPago formaDePago, EstadoFactura estado, String comentarios,
            float importe, TipoIVA iva) {
        this.numeroDeFactura = numeroDeFactura;
        this.cliente = cliente;
        this.ejercicio = ejercicio;
        this.propietario = propietario;
        this.fecha = fecha;
        this.formaDePago = formaDePago;
        this.estado = estado;
        this.comentarios = comentarios;
        this.importe = importe;
        this.iva = iva;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public String getNombreCliente() {
        return this.cliente.getNombre();
    }
    
    public String getNifCliente() {
        return this.cliente.getNombre();
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public TipoIVA getIva() {
        return iva;
    }

    public void setIva(TipoIVA iva) {
        this.iva = iva;
    }

    

    public Long getNumeroDeFactura() {
        return numeroDeFactura;
    }

    public void setNumeroDeFactura(Long numeroDeFactura) {
        this.numeroDeFactura = numeroDeFactura;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public FormaPago getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(FormaPago formaDePago) {
        this.formaDePago = formaDePago;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public void setEstado(EstadoFactura estado) {
        this.estado = estado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }


    //ni idea si hace falta
    @Override
    public int hashCode() {
        if (numeroDeFactura != null) {
            return Objects.hashCode(this.numeroDeFactura);
        } else {
            return hashCodePorContenido();
        }
    }

        //ni idea si hace falta

    @Override
    public boolean equals(Object obj) {
        if (numeroDeFactura != null) {
            return equalsPorId(obj);
        } else {
            return equalsPorContenido(obj);
        }
    }

        //ni idea si hace falta
    public int hashCodePorContenido() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.numeroDeFactura);
        hash = 67 * hash + Objects.hashCode(this.cliente);
        hash = 67 * hash + Objects.hashCode(this.ejercicio);
        hash = 67 * hash + Objects.hashCode(this.propietario);
        hash = 67 * hash + Objects.hashCode(this.fecha);
        hash = 67 * hash + Objects.hashCode(this.formaDePago);
        hash = 67 * hash + Objects.hashCode(this.estado);
        hash = 67 * hash + Objects.hashCode(this.comentarios);
        hash = 67 * hash + Objects.hashCode(this.importe);
        hash = 67 * hash + Objects.hashCode(this.iva);
        return hash;
    }

    public boolean equalsPorContenido(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Factura other = (Factura) obj;
        if (!Objects.equals(this.numeroDeFactura, other.numeroDeFactura)) {
            return false;
        }
        if (!Objects.equals(this.ejercicio, other.ejercicio)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.propietario, other.propietario)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.formaDePago, other.formaDePago)) {
            return false;
        }
        if (!Objects.equals(this.comentarios, other.comentarios)) {
            return false;
        }
        
        if (!Objects.equals(this.importe, other.importe)) {
            return false;
        }
        
        if (!Objects.equals(this.iva, other.iva)) {
            return false;
        }
        return true;
    }

    private boolean equalsPorId(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Factura other = (Factura) obj;
        if (!Objects.equals(this.numeroDeFactura, other.numeroDeFactura)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Factura{" + "id=" + numeroDeFactura + ", ejercicio=" + ejercicio + 
                ", cliente=" + cliente.toString() + ", fecha=" + fecha 
                + ", formaDePago=" + formaDePago.toString() + ", estado=" + estado.toString() +
                ", comentarios=" + comentarios 
                + ", importe=" + importe + ", tipo iva=" + iva.toString() +'}';
    }
}
