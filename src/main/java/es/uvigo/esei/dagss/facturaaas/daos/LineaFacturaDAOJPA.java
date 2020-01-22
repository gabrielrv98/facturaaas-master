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
 * @author grvidal
 */
@Stateless
public class LineaFacturaDAOJPA extends GenericoDAOJPA<LineaFactura, Long> implements LineaFacturaDAO {

    @Override
    public List<LineaFactura> buscarTodasLineaFacturas(Long numeroFactura) {
    
        TypedQuery<LineaFactura> query = em.createQuery("SELECT li FROM LineaFactura AS li WHERE li.numeroDeFactura.numeroDeFactura = :nFactura", LineaFactura.class);
        query.setParameter("nFactura", numeroFactura);
        
        return query.getResultList();
    }
    
    @Override
    public void eliminar(LineaFactura l){
        TypedQuery<LineaFactura> query = em.createQuery("DELETE FROM LineaFactura AS li WHERE li.id = :nFactura", LineaFactura.class);
        query.setParameter("nFactura", l.getId());
        query.executeUpdate();
    }
    
    
    
}
