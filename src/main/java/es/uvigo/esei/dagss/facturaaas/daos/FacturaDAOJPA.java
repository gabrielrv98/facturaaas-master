/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.Cliente;
import es.uvigo.esei.dagss.facturaaas.entidades.EstadoFactura;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author OrenadorOmega
 */
@Stateless
public class FacturaDAOJPA extends GenericoDAOJPA<Factura, Long> implements FacturaDAO{
    
    //dice que no encuentra u.NUMERODEFACTURA 
    // estado actual: ( db aun no existe)
    @Override
    public Factura buscarPorNumeroDeFactura(Cliente cliente,String numeroDeFactura) {
        TypedQuery<Factura> query = 
                em.createQuery("SELECT u "
                        + "FROM Factura AS u "
                        + "WHERE u.NUMERODEFACTURA = :numeroDeFactura"
                        + "WHERE u.CLIENTE = :cliente", Factura.class);
        query.setParameter("numeroDeFactura", numeroDeFactura);
        query.setParameter("cliente", cliente.getId());
        
        List<Factura> resultados = query.getResultList();
        if ((resultados != null) && (!resultados.isEmpty())) {
            return resultados.get(0);
        }
        return null;  // No encontrado
    }
    
    @Override
    public List<Factura> buscarPorFecha(Cliente cliente,Date fecha) {
        TypedQuery<Factura> query = em.createQuery("SELECT u "
                + "FROM Factura AS u "
                + "WHERE u.FECHADEEMISION = :fecha"
                + "WHERE u.CLIENTE = :cliente", Factura.class);
        query.setParameter("fecha", fecha);
        query.setParameter("cliente", cliente.getId());
        return query.getResultList();
    }

    @Override
    public List<Factura> buscarPorEstado(Cliente cliente,EstadoFactura estado) {
        TypedQuery<Factura> query = em.createQuery("SELECT u "
                + "FROM Factura AS u "
                + "WHERE u.ESTADO = :estado"
                + "WHERE u.CLIENTE = :cliente", Factura.class);
        query.setParameter("estado", estado);
        query.setParameter("cliente", cliente.getId());
        return query.getResultList();
    }
    
}
