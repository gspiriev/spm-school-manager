/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.MusicalPiece;

/**
 *
 * @author root_spiriev
 */
public class MusicalPieceParser implements Parser<MusicalPiece, MusicalPiece>{

    @Override
    public MusicalPiece parse(String stringToParse) {
        Grade[] grades = Grade.values();
        String[] unformattedPieceArr = stringToParse.split("/");
       int complexity;
        if (unformattedPieceArr.length < 5 || 
             unformattedPieceArr[4].equals("") || 
             !isNumeric(unformattedPieceArr[4])) {
            
            complexity = 5;
        } else {
            complexity = Integer.parseInt(unformattedPieceArr[4]);
        }
        MusicalPiece piece = new MusicalPiece(
              unformattedPieceArr[3], 
              unformattedPieceArr[2],
             complexity , 
              grades[Integer.parseInt(unformattedPieceArr[1]) - 1]);
        
        return piece;
    }

    @Override
    public MusicalPiece parse(MusicalPiece entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNumeric(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
       
}
