/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * The main class which creates an annual disposition list, for all the students
 * in the Student resource file. When adding information about a student or
 * school dates in the resource files, use "/"(forth slash) for delimiter
 * between tokens (tokens are grades, student names, ability, musical piece
 * names, complexity etc.)
 *
 * @author root_spiriev
 */
public class AnnualLessonDisposition {

    /**
     * This method creates a specific to a certain student schedule according to
     * dates and musical pieces.
     *
     * @param student - a student object
     * @param studyDates - a list of study dates for the specific student's
     * grade
     * @param musicalPieces - a list of musical pieces with appropriate grade,
     * shuffled
     * @return
     */
    public WeeklySchedule createSpecificSchedule(
            Student student,
            List<StudyDate> studyDates,
            List<MusicalPiece> musicalPieces
    ) {

        List<MusicalPiece> studentMusicalPieces = makeStudentMusicalPieces(musicalPieces, student);
        if (studentMusicalPieces.isEmpty()) {
            throw new IllegalArgumentException("There are no suitable musical pieces for a student. Check student ability or musical" +
                                                                                           " piece complexity");
        }

        LinkedHashMap<StudyDate, Lesson> schedule = new LinkedHashMap<>();
        for (StudyDate studyDate : studyDates) {

            schedule.put(studyDate, new Lesson(null, null, null));
        }
        initializeSchedule(schedule, studentMusicalPieces, student) ;
        
        WeeklySchedule specificSchedule = new WeeklySchedule(schedule);

        return specificSchedule;
    }

    private List<MusicalPiece> makeStudentMusicalPieces(List<MusicalPiece> mPieces, Student student) {

        List<MusicalPiece> studentMusicalPieces = new ArrayList<>();

        for (MusicalPiece piece : mPieces) {

            if (student.getGrade().equals(piece.getGrade())
                    && student.getAbility() >= piece.getComplexity()) {

                studentMusicalPieces.add(piece);
            }
        }

//        studentMusicalPieces = mPieces
//                .stream()
//                .filter(
//                        (MusicalPiece piece) -> student.getGrade().equals(piece.getGrade()) && student.getAbility() >= piece.getComplexity() 
//                )
//                .collect(Collectors.toList());
        Collections.shuffle(studentMusicalPieces);

        return studentMusicalPieces;
    }

    private void initializeSchedule(Map<StudyDate, Lesson> schedule,
            List<MusicalPiece> studentMusicalPieces,
            Student student) {
        BiConsumer<Lesson, MusicalPiece>[] lessonPieceSetters = new BiConsumer[]{
            (Object l, Object m) -> {
                ((Lesson) l).setPiece1((MusicalPiece) m);
            },
            (Object l, Object m) -> {
                ((Lesson) l).setPiece2((MusicalPiece) m);
            },
            (Object l, Object m) -> {
                ((Lesson) l).setPiece3((MusicalPiece) m);
            }};

        MusicalPieceStudyCalculator calc = new MusicalPieceStudyCalculator(10);

        Iterator<MusicalPiece> musicalPieceSequence = studentMusicalPieces.iterator();
        //need method refactoring
        for (BiConsumer<Lesson, MusicalPiece> lessonPieceSetter : lessonPieceSetters) {
            
            int studiedWeeks = 1;
            MusicalPiece mp;
            if (musicalPieceSequence.hasNext()) {
                mp = musicalPieceSequence.next();
            } else {
                mp = studentMusicalPieces.get(0);
            }
            int mpStudyWeeks = calc.calculateStudyWeeks(student, mp);

            for (Lesson lesson : schedule.values()) {

                if (studiedWeeks == mpStudyWeeks) {

                    studiedWeeks = 1;
                    if (musicalPieceSequence.hasNext()) {
                        mp = musicalPieceSequence.next();
                        mpStudyWeeks = calc.calculateStudyWeeks(student, mp);
                    } else {
                        mp = studentMusicalPieces.get(0);
                        mpStudyWeeks = calc.calculateStudyWeeks(student, mp);
                    }
                }
                lessonPieceSetter.accept(lesson, mp);
                studiedWeeks++;
            }
        }
    }
}
