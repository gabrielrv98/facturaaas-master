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
        TypedQuery<Pago> query = em.createQuery("SELECT u FROM Pago AS u WHERE u.idCliente = :propietario", Pago.class);
        query.setParameter("propietario", propietario.getId());
        return query.getResultList();
    }

    @Override
    public Pago buscarPorNumeroDeFactura(Usuario propietario, Long numeroDeFactura) {
        TypedQuery<Pago> query = em.createQuery("SELECT u FROM Pago AS u WHERE u.idCliente = :idpropietario "
                + "AND u.numeroDeFactura = :numeroDeFactura", Pago.class);
        query.setParameter("idpropietario", propietario.getId());
        query.setParameter("numeroDeFactura", numeroDeFactura);
        List<Pago> resultados = query.getResultList();
        if ((resultados != null) && (!resultados.isEmpty())) {
            return resultados.get(0);
        }
        return null;  // No encontrado
    }

    @Override
    public List<Pago> buscarPorFechaVencimiento(Usuario propietario, Date fechavencimiento) {
        TypedQuery<Pago> query = em.createQuery("SELECT u FROM Pago AS u  "
                + "WHERE u.fechavencimiento > :busqueda ", Pago.class);
        query.setParameter("busqueda", fechavencimiento);
        return query.getResultList();
    }

    @Override
    public List<Pago> buscarPorEstado(Usuario propietario,EstadoPago estado) {
        TypedQuery<Pago> query = em.createQuery("SELECT u FROM Pago AS u  "
                + "WHERE u.estado = :estado AND u.idCliente = :idPropietario", Pago.class);
        query.setParameter("estado", estado);
        query.setParameter("idPropietario", propietario.getId());
        return query.getResultList();
    }

    
}
