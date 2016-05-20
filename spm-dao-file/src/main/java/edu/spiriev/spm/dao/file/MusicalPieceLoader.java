/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.MusicalPieceDao;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.MusicalPiece;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



/**
 * Loads and creates a musical piece list from a file with musical pieces
 * @author root_spiriev
 */
public class MusicalPieceLoader implements MusicalPieceDao{
    
    private final File musicalPiecesFile;

    public MusicalPieceLoader(File musicalPieces) {

        musicalPiecesFile = musicalPieces;
    }
    
    @Override
    public List<MusicalPiece> loadMusicalPieces(){
        
       ArrayList<MusicalPiece> musicalPieceList = new ArrayList<>();
        
        try(
        BufferedReader reader = new BufferedReader(new FileReader(this.musicalPiecesFile)))
        {
            
            while(reader.ready()) {
                
                MusicalPiece piece = parse(reader.readLine());
                musicalPieceList.add(piece);
            }
        } catch (Exception e) {
            System.err.println("Musical piece file not found");
        }
        return musicalPieceList;
    }
    
    public MusicalPiece parse(String unformattedPiece) {
        
        Grade[] grades = Grade.values();
        String[] unformattedPieceArr = unformattedPiece.split("/");
        MusicalPiece piece = new MusicalPiece(
              unformattedPieceArr[3], 
              unformattedPieceArr[2],
              5, 
              grades[Integer.parseInt(unformattedPieceArr[1]) - 1]);
        
        return piece;
    }

}
