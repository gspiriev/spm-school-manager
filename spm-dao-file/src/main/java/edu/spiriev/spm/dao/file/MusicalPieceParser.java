/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.EntityMarker;
import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.MusicalPiece;

/**
 *
 * @author root_spiriev
 */
public class MusicalPieceParser implements Parser<MusicalPiece, EntityMarker>{

    @Override
    public MusicalPiece parse(String stringToParse) {
        Grade[] grades = Grade.values();
        String[] unformattedPieceArr = stringToParse.split("/");
        MusicalPiece piece = new MusicalPiece(
              unformattedPieceArr[3], 
              unformattedPieceArr[2],
              5, 
              grades[Integer.parseInt(unformattedPieceArr[1]) - 1]);
        
        return piece;
    }

    @Override
    public MusicalPiece parse(EntityMarker entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
