/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.LineaFactura;
import java.util.List;

/**
 *
 * @author grvidal
 */
public interface LineaFacturaDAO extends GenericoDAO<LineaFactura, Long> {
    public List<LineaFactura> buscarTodasLineaFacturas(Long numeroFactura);
}
