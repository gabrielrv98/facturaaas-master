/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author OrenadorOmega
 */
@Entity
@Table(name = "LINEA_FACTURA")
public class LineaFactura implements Serializable{
        
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "FACTURA_NUMERODEFACTURA")
    private Factura numeroDeFactura;
        
    private String concepto;
    
    private int cantidad;
    
    private float precio;
    
    private int descuento;
    
    @ManyToOne
    @JoinColumn(name = "TIPO_IVA_ID")
    private TipoIVA iva;

    private float total;
    

    public LineaFactura() {
    }

    public LineaFactura(Long id, Factura numeroDeFactura, String concepto, float precio, int cantidad, int descuento, TipoIVA iva, float total) {
        this.id = id;
        this.numeroDeFactura = numeroDeFactura;
        this.concepto = concepto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.iva = iva;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Factura getNumeroDeFactura() {
        return numeroDeFactura;
    }

    public void setNumeroDeFactura(Factura factura) {
        this.numeroDeFactura = factura;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public TipoIVA getIva() {
        return iva;
    }

    public void setIva(TipoIVA iva) {
        this.iva = iva;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    


    //ni idea si hace falta
    @Override
    public int hashCode() {
        if (id != null) {
            return Objects.hashCode(this.numeroDeFactura);
        } else {
            return hashCodePorContenido();
        }
    }

        //ni idea si hace falta

    @Override
    public boolean equals(Object obj) {
        if (id != null) {
            return equalsPorId(obj);
        } else {
            return equalsPorContenido(obj);
        }
    }

        //ni idea si hace falta
    public int hashCodePorContenido() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.numeroDeFactura);
        hash = 67 * hash + Objects.hashCode(this.concepto);
        hash = 67 * hash + Objects.hashCode(this.precio);
        hash = 67 * hash + Objects.hashCode(this.cantidad);
        hash = 67 * hash + Objects.hashCode(this.iva);
        hash = 67 * hash + Objects.hashCode(this.descuento);
        hash = 67 * hash + Objects.hashCode(this.total);
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
        final LineaFactura other = (LineaFactura) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.numeroDeFactura, other.numeroDeFactura)) {
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
        final LineaFactura other = (LineaFactura) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LneaFactura{" + "id=" + +id+ " nFactura " +numeroDeFactura +  ", precio=" + precio + " cantidad "+ cantidad +", descuento=" + descuento 
                + ", iva=" + iva + ", total=" + total +'}';
    }
}
