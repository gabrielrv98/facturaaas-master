package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.EstadoPago;
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
    public List<Pago> buscarTodosLosPagos(){
        TypedQuery<Pago> query = 
                em.createQuery("SELECT u "
                        + "FROM Pago AS u ", Pago.class);
        
        return query.getResultList();
    }
    
    @Override
    public Pago buscarPorNumeroDeFactura(String numeroDeFactura) {
        TypedQuery<Pago> query = 
                em.createQuery("SELECT u "
                        + "FROM Pago AS u "
                        + "WHERE u.numeroDeFactura = :numeroDeFactura", Pago.class);
        query.setParameter("numeroDeFactura", numeroDeFactura);
        
        List<Pago> resultados = query.getResultList();
        if ((resultados != null) && (!resultados.isEmpty())) {
            return resultados.get(0);
        }
        return null;  // No encontrado
    }
    
    @Override
    public List<Pago> buscarPorFechaVencimiento(Date fecha) {
        TypedQuery<Pago> query = em.createQuery("SELECT u "
                + "FROM Pago AS u "
                + "WHERE u.fechaVencimiento = :fecha", Pago.class);
        query.setParameter("fechaVencimiento", fecha);
        return query.getResultList();
    }

    @Override
    public List<Pago> buscarPorEstado(EstadoPago estado) {
        TypedQuery<Pago> query = em.createQuery("SELECT u FROM Pago AS u  "
                + "WHERE u.estado = :estado", Pago.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    
    
    
    
    
}