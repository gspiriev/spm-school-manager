/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.MusicalPiece;
/**
 *
 * @author root_spiriev
 */
public class MusicalPieceHibernateParser implements Parser<MusicalPiece, MusicalPiecesEntity>{
    
    @Override
    public MusicalPiece parse(String stringToParse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public MusicalPiece parse(MusicalPiecesEntity entity) {
        MusicalPiece nextPiece = new MusicalPiece(entity.getPieceName(),
                                         entity.getComposer(),
                                         entity.getComplexity(),
                                         Grade.valueOf(entity.getMusicalPieceGradeEntity().getGradeId().getGradeName()));
        return nextPiece;
    }
    
}
