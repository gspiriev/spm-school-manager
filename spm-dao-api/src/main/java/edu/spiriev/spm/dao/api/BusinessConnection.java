/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.api;

/**
 *
 * @author root_spiriev
 */
public interface BusinessConnection {
    
    void commitTransaction();
    
    void makeConnection(String[] props);
    
}
