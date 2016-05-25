/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import edu.spiriev.spm.dao.api.BusinessConnection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author root_spiriev
 */
public class JpaDatabaseConnection implements BusinessConnection {
    
    private final String managerName;
    private EntityManagerFactory emf;
    private EntityManager em;

    public JpaDatabaseConnection(String managerName) {
        this.managerName = managerName;
    }

    public EntityManager getEm() {
        return em;
    }
    
    
    
    @Override
    public void commitTransaction() {
        
        if (em.isOpen()) {
            em.getTransaction().commit();
            em.close();
            System.out.println("Entity Manager is now closed");
        } else {
          
        }
    }

    @Override
    public void makeConnection(String[] props) {
        
        this.emf = Persistence.createEntityManagerFactory(this.managerName);
        if(emf.isOpen()) {
            this.em = emf.createEntityManager();
            em.getTransaction().begin();
        } else {
            throw new IllegalArgumentException("Manager with given name does not exist or there are invalid connection properties." +
                                                "Check persistence.xml");
        }
        
    }
    
    
    
}
