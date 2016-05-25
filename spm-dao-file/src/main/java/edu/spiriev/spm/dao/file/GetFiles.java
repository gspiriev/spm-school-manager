/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.BusinessConnection;
import java.io.File;

/**
 *
 * @author root_spiriev
 */
public class GetFiles implements BusinessConnection {
    
    private File[] resources;

    public File[] getResources() {
        
        return resources;
    }
    
    
    @Override
    public void commitTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeConnection(String[] props) {
        
        ClassLoader cl = this.getClass().getClassLoader();
        
        this.resources = new File[] {new File(cl.getResource(props[0]).getFile()),
                    new File(cl.getResource(props[1]).getFile()),
                    new File(cl.getResource(props[2]).getFile())};
    }
    
    
    
}
