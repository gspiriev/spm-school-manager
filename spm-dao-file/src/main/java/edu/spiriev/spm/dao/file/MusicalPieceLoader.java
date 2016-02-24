/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.MusicalPieceIO;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.MusicalPiece;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



/**
 * Loads and creates a musical piece list from a file with musical pieces
 * @author root_spiriev
 */
public class MusicalPieceLoader implements MusicalPieceIO{
    
    private final InputStream musicalPiecesFile;

    public MusicalPieceLoader() {
        ClassLoader cl = getClass().getClassLoader();
        this.musicalPiecesFile = cl.getResourceAsStream("Graded_Pieces_All_CSV.csv");
    }
    
    @Override
    public ArrayList<MusicalPiece> readAndCreateAllMusicalPieceList(){
        
       ArrayList<MusicalPiece> musicalPieceList = new ArrayList<>();
        
        try(
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.musicalPiecesFile)))
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
