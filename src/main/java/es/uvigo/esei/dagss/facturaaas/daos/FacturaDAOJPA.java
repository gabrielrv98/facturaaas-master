/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.EstadoFactura;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
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
public class FacturaDAOJPA extends GenericoDAOJPA<Factura, Long> implements FacturaDAO{
    
   
    @Override
    public List<Factura> buscarTodasFacturas(Usuario propietario){
        TypedQuery<Factura> query = em.createQuery("SELECT f FROM Factura AS f WHERE f.propietario.id = :idpropietario", Factura.class);
        query.setParameter("idpropietario", propietario.getId());
        return query.getResultList();
        
    }
    
    @Override
    public Factura buscarPorNumeroDeFactura(Usuario propietario,String numeroDeFactura) {
        TypedQuery<Factura> query = 
                em.createQuery("SELECT u "
                        + "FROM Factura AS u "
                        + "WHERE u.numeroDeFactura = :numeroDeFactura AND u.propietario.id = :idPropietario", Factura.class);
        query.setParameter("numeroDeFactura", numeroDeFactura);
        query.setParameter("idPropietario", propietario.getId());
        
        List<Factura> resultados = query.getResultList();
        if ((resultados != null) && (!resultados.isEmpty())) {
            return resultados.get(0);
        }
        return null;  // No encontrado
    }
    
    @Override
    public List<Factura> buscarPorFecha(Usuario propietario,Date fecha) {
        TypedQuery<Factura> query = em.createQuery("SELECT u "
                + "FROM Factura AS u "
                + "WHERE u.fecha = :fecha AND u.propietario.id = :idPropietario", Factura.class);
        query.setParameter("fecha", fecha);
        query.setParameter("idPropietario", propietario.getId());
        return query.getResultList();
    }

    @Override
    public List<Factura> buscarPorEstado(Usuario propietario,EstadoFactura estado) {
        TypedQuery<Factura> query = em.createQuery("SELECT u FROM Factura AS u  "
                + "WHERE u.estado = :estado AND u.propietario.id = :idPropietario", Factura.class);
        query.setParameter("estado", estado);
        query.setParameter("idPropietario", propietario.getId());
        return query.getResultList();
    }
    
    @Override
    public int maxNumeroDeFactura(){
        Query query = em.createNativeQuery("SELECT MAX(NUMERODEFACTURA) FROM Factura");
        return query.getFirstResult();
    }
    
    
    
    
}
