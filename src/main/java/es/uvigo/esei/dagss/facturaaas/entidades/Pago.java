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
@Table(name = "PAGO")
public class Pago implements Serializable{
    // nombre y nif del cliente
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroDeFactura;//hace referencia a NUMERODEFACTURA

    @ManyToOne
    private Cliente cliente;
    
    private int importe;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavencimiento;
    
    @Enumerated(EnumType.STRING)
    private EstadoPago estado;

    public Pago() {
    }

    public Pago(Long numeroDeFactura, Cliente cliente, int importe, Date fechavencimiento,
            EstadoPago estado) {
        
        
        this.numeroDeFactura = numeroDeFactura;
        this.cliente = cliente;
        this.importe = importe;
        this.fechavencimiento = fechavencimiento;
    }

    public Long getNumeroDeFactura() {
        return numeroDeFactura;
    }

    public void setNumeroDeFactura(Long numeroDeFactura) {
        this.numeroDeFactura = numeroDeFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setcliente(Cliente cliente) {
        this.cliente = cliente;
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
        hash = 67 * hash + Objects.hashCode(this.cliente);
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
        if (!Objects.equals(this.cliente, other.cliente)) {
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
        return "Pago{" + "id=" + numeroDeFactura + ", nombrecliente=" + cliente.getNombre() + ", idcliente=" + cliente.getNif() + ", fecha=" + fechavencimiento 
                +", estado=" + estado + ", importe=" + importe +'}';
    }
}
