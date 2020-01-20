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

@Entity
@Table(name = "PAGO")
public class Pago implements Serializable{
    // nombre y nif del cliente
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
  
    private Long numeroDeFactura;//hace referencia a NUMERODEFACTURA

    private String nombreCliente;
    
    private String nifCliente;
    
    private int importe;

    @Temporal(TemporalType.TIMESTAMP)

    private Date fechavencimiento;
    
    @Enumerated(EnumType.STRING)

    private EstadoPago estado;

    public Pago() {
    }

    public Pago(Long numeroDeFactura, String nombreCliente, String nifCliente, int importe, Date fechavencimiento,
            EstadoPago estado) {
        
        
        this.numeroDeFactura = numeroDeFactura;
        this.nombreCliente = nombreCliente;
        this.nifCliente = nifCliente;
        this.importe = importe;
        this.fechavencimiento = fechavencimiento;
    }

    public Long getNumeroDeFactura() {
        return numeroDeFactura;
    }

    public void setNumeroDeFactura(Long numeroDeFactura) {
        this.numeroDeFactura = numeroDeFactura;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNifCliente() {
        return nifCliente;
    }

    public void setNifCliente(String nifCliente) {
        this.nifCliente = nifCliente;
    }

    
    

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
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
        hash = 67 * hash + Objects.hashCode(this.nombreCliente);
        hash = 67 * hash + Objects.hashCode(this.nifCliente);
        hash = 67 * hash + Objects.hashCode(this.fechavencimiento);
        hash = 67 * hash + Objects.hashCode(this.estado);
        hash = 67 * hash + Objects.hashCode(this.importe);
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
        final Pago other = (Pago) obj;
        if (!Objects.equals(this.numeroDeFactura, other.numeroDeFactura)) {
            return false;
        }
        if (!Objects.equals(this.nombreCliente, other.nombreCliente)) {
            return false;
        }
        if (!Objects.equals(this.nifCliente, other.nifCliente)) {
            return false;
        }
        if (!Objects.equals(this.fechavencimiento, other.fechavencimiento)) {
            return false;
        }
        
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.importe, other.importe)) {
            return false;
        }
        //no se si forma de pago tamben va aqui porque si no esta pagada no tiene forma de pago
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
        final Pago other = (Pago) obj;
        if (!Objects.equals(this.numeroDeFactura, other.numeroDeFactura)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pago{" + "id=" + numeroDeFactura + ", nombrecliente=" + nombreCliente + ", idcliente=" + nifCliente + ", fecha=" + fechavencimiento 
                +", estado=" + estado + ", importe=" + importe +'}';
    }
}
