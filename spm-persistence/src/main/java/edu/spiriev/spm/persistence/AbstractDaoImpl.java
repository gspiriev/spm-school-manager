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
public class AbstractDaoImpl<T extends Comparable<T>, E extends EntityMarker> implements AbstractDao{
    
    private final EntityManager em;
    private Parser<T, E> parser;
    private String className;

    public AbstractDaoImpl(EntityManager em) {
        this.em = em;
    }

    public void setParser(Parser<T, E> parser) {
        this.parser = parser;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
    @Override
    public List<T> loadAll() {
        
        Query query = em.createNamedQuery(className + ".findAll");
        List<E> entities = query.getResultList();
        
        List<T> data = new ArrayList<>();
        
        entities.stream().forEach((entity) -> {
            data.add(parser.parse(entity));
        });
        
        return data;
    }
    
}
