/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import edu.spiriev.spm.dao.api.MusicalPieceDao;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.MusicalPiece;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author root_spiriev
 */
public class MusicalPieceHibernateLoader implements MusicalPieceDao{
    
    protected final SQLiteLoader loadFromSQLite;

    public MusicalPieceHibernateLoader(SQLiteLoader loadFromSQLite) {
        this.loadFromSQLite = loadFromSQLite;
    }
    
    @Override
    public List<MusicalPiece> loadMusicalPieces() {
        List<MusicalPiece> musicalPieces = new ArrayList<>();
        List<MusicalPiecesEntity> musicalPieceEntities = this.loadFromSQLite.getMusicalPieceEntities();
        
        MusicalPiece nextPiece = null;
        for (MusicalPiecesEntity musicalPieceEntity: musicalPieceEntities) {
            
            nextPiece = new MusicalPiece(musicalPieceEntity.getPieceName(),
                                         musicalPieceEntity.getComposer(),
                                         musicalPieceEntity.getComplexity(),
                                         Grade.valueOf(musicalPieceEntity.getMusicalPieceGradeEntity().getGradeId().getGradeName()));
            musicalPieces.add(nextPiece);
            
        }
        return musicalPieces;
    }
    
}
