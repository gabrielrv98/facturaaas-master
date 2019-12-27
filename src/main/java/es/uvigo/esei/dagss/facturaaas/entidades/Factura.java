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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "FACTURA")
public class Factura implements Serializable{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroDeFactura;//hace referencia a NUMERODEFACTURA

    private String ejercicio;

    private String cliente;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @ManyToOne
    private FormaPago formaDePago;//es la del usuario ac activo
    //copie de USUARIO-Cliente-DatosFacturacion

    @Enumerated(EnumType.STRING)
    private EstadoFactura estado;
    
    private String comentarios;

    public Factura() {
    }

    public Factura(Long id, String ejercicio, String cliente, Date fecha,
                        FormaPago formaDePago) {
        this.numeroDeFactura = id;
        this.ejercicio = ejercicio;
        this.cliente = cliente;
        this.fecha = fecha;
        this.formaDePago = formaDePago;
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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
        hash = 67 * hash + Objects.hashCode(this.ejercicio);
        hash = 67 * hash + Objects.hashCode(this.cliente);
        hash = 67 * hash + Objects.hashCode(this.fecha);
        hash = 67 * hash + Objects.hashCode(this.formaDePago);
        hash = 67 * hash + Objects.hashCode(this.estado);
        hash = 67 * hash + Objects.hashCode(this.comentarios);
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
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (this.estado != other.estado) {
            return false;
        }
        //no se si forma de pago tamben va aqui porque si no esta pagada no tiene forma de pago
        if (!Objects.equals(this.formaDePago, other.formaDePago)) {
            return false;
        }
        if (!Objects.equals(this.comentarios, other.comentarios)) {
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
        return "Factura{" + "id=" + numeroDeFactura + ", ejercicio=" + ejercicio + ", cliente=" + cliente + ", fecha=" + fecha 
                + ", formaDePago=" + formaDePago.toString() + ", estado=" + estado + ", comentarios=" + comentarios +'}';
    }
}
