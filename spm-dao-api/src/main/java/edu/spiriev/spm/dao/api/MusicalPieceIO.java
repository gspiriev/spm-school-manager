/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.file_manipulation;

import edu.spiriev.domain_model.Grade;
import edu.spiriev.domain_model.MusicalPiece;
import java.util.ArrayList;

/**
 *
 * @author root_spiriev
 */
public interface MusicalPieceIO {
    
    ArrayList<MusicalPiece> readAndCreateGradedMusicalPieceList(Grade g);
    
}
