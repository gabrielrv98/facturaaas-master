/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.Cliente;
import es.uvigo.esei.dagss.facturaaas.entidades.EstadoFactura;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
import java.util.Date;
import java.util.List;

/**
 *
 * @author OrenadorOmega
 */
public interface FacturaDAO {
    public Factura buscarTodasFacturas(Cliente propietario);
    public Factura buscarPorNumeroDeFactura(Cliente propietario, String numeroDeFactura);
    public List<Factura> buscarPorFecha(Cliente propietario, Date fecha);
    public List<Factura> buscarPorEstado(Cliente propietario, EstadoFactura estado);
    
}
