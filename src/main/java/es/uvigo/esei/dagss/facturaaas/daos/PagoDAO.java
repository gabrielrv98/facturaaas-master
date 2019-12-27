/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import es.uvigo.esei.dagss.facturaaas.entidades.EstadoPago;
import es.uvigo.esei.dagss.facturaaas.entidades.Pago;
import java.util.Date;
import java.util.List;

/**
 *
 * @author OrenadorOmega
 */
public interface PagoDAO extends GenericoDAO<Pago, Long>{
    public List<Pago> buscarTodosLosPagos(Usuario propietario);
    public Pago buscarPorNumeroDeFactura(Usuario propietario, String numeroDeFactura);
    public List<Pago> buscarPorFechaVencimiento(Usuario propietario, Date fechavencimiento);
    public List<Pago> buscarPorEstado(Usuario propietario, EstadoPago estado);
    
}
