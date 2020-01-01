/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.LineaFactura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author OrenadorOmega
 */
@Stateless
public class LineaFacturaDAOJPA extends GenericoDAOJPA<LineaFactura, Long> implements LineaFacturaDAO {

    @Override
    public List<LineaFactura> buscarTodasLineaFacturas(Long numeroFactura) {
    
        TypedQuery<LineaFactura> query = em.createQuery("SELECT l FROM LineaFactura AS l WHERE l.numeroDeFactura = :nFactura", LineaFactura.class);
        query.setParameter("nFactura", numeroFactura);
        
        return query.getResultList();
    }
    
    
    
}
