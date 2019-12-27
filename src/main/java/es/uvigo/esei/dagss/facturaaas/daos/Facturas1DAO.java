/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.EstadoFactura;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
import java.util.Date;
import java.util.List;

/**
 *
 * @author OrenadorOmega
 */
public interface Facturas1DAO {
    public Factura buscarPorNumeroFactura(String numeroDeFactura);
    public List<Factura> buscarPorFecha(Date fecha);
    public List<Factura> buscarPorEstado(EstadoFactura estado);
    
}
