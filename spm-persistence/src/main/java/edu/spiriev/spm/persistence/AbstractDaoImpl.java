/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import edu.spiriev.spm.dao.api.EntityMarker;
import edu.spiriev.spm.dao.api.AbstractDao;
import edu.spiriev.spm.dao.api.Parser;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author root_spiriev
 */
public class AbstractDaoImpl<T, E> implements AbstractDao{
    
    private final EntityManager em;
    private final Parser<T, E> parser;
    private final Class<E> entityType;

	public AbstractDaoImpl(EntityManager em, Parser<T, E> parser, Class<E> entityType) {
		this.em = em;
		this.parser = parser;
		this.entityType = entityType;
	}

    
    @Override
    public List<T> loadAll() {
        
        Query query = em.createNamedQuery(entityType.getSimpleName() + ".findAll");
        List<E> entities = query.getResultList();
        
        List<T> data = new ArrayList<>();
        
        entities.stream().forEach((entity) -> {
            data.add(parser.parse(entity));
        });
        
        return data;
    }
    
}
