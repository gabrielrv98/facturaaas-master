/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.Cliente;
import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import es.uvigo.esei.dagss.facturaaas.entidades.EstadoFactura;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
import java.util.Date;
import java.util.List;

/**
 *
 * @author grvidal
 */
public interface FacturaDAO extends GenericoDAO<Factura, Long>{
    public List<Factura> buscarTodasFacturas(Usuario propietario);
    public List<Factura> buscarPorNumeroDeFactura(Usuario propietario, String numeroDeFactura);
    public List<Factura> buscarPorFecha(Usuario propietario, Date fecha);
    public List<Factura> buscarPorEstado(Usuario propietario, EstadoFactura estado);
    public List<Factura> buscarPorCliente(Usuario propietario, Cliente cliente);
    
}
