/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import org.junit.Assert;
import org.junit.Test;
import edu.spiriev.spm.domain.model.*;
/**
 *
 * @author root_spiriev
 */

public class MusicalPieceLoaderTest {
    
    @Test
    public void parseTest() {
        
        MusicalPieceLoader testLoader = new MusicalPieceLoader();
        String testStr = "0/1/Kabalevsky/Waltz";
        MusicalPiece testPiece = testLoader.parse(testStr);
        Assert.assertTrue(testPiece instanceof MusicalPiece);
    }
    
}
