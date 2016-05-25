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
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
/**
 *
 * @author root_spiriev
 */
public class MusicalPieceHibernateLoader implements MusicalPieceDao{
    
    private final EntityManager em;

    public MusicalPieceHibernateLoader(EntityManager em) {
        this.em = em;
    }
    
    
    
    @Override
    public List<MusicalPiece> loadMusicalPieces() {
        
        TypedQuery<MusicalPiecesEntity> musicalPiecesQuery = em.createNamedQuery("MusicalPiecesEntity.findAll", MusicalPiecesEntity.class);
        List<MusicalPiecesEntity> musicalPieceEntities = musicalPiecesQuery.getResultList();
        
        List<MusicalPiece> musicalPieces = new ArrayList<>();
        
        
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
