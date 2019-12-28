/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.Cliente;
import es.uvigo.esei.dagss.facturaaas.entidades.EstadoFactura;
import es.uvigo.esei.dagss.facturaaas.entidades.EstadoPago;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
import es.uvigo.esei.dagss.facturaaas.entidades.Pago;
import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author OrenadorOmega
 */
@Stateless
public class PagoDAOJPA extends GenericoDAOJPA<Pago, Long> implements PagoDAO{

    @Override
    public List<Pago> buscarPagosDeCliente(Usuario propietario) {
        TypedQuery<Pago> query = em.createQuery("SELECT u FROM Pago AS u WHERE u.cliente.propietario = :propietario", Pago.class);
        query.setParameter("propietario", propietario.getId());
        return query.getResultList();
    }

    @Override
    public Pago buscarPorNumeroDeFactura(Usuario propietario, String numeroDeFactura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pago> buscarPorFechaVencimiento(Usuario propietario, Date fechavencimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pago> buscarPorEstado(Usuario propietario, EstadoPago estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
