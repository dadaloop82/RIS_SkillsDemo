package com.dadaloop.RISSkillDemo.service;

import com.dadaloop.RISSkillDemo.model.Note;
// import com.dadaloop.RISSkillDemo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
 * Contains business logic for CRUD operations on notes.
 */

@Service
public class NoteService {

    // @Autowired
    // private NoteRepository noteRepository;

    // // Method to get all notes
    // public List<Note> getAllNotes() {
    //     return noteRepository.findAll();
    // }

    // // Method to add a new note
    // public Note addNote(Note note) {
    //     return noteRepository.save(note);
    // }

    // // Method to update an existing note
    // public Note updateNote(Long id, Note updatedNote) {
    //     Note existingNote = noteRepository.findById(id).orElse(null);
    //     if (existingNote != null) {
    //         existingNote.setTitle(updatedNote.getTitle());
    //         existingNote.setText(updatedNote.getText());
    //         return noteRepository.save(existingNote);
    //     }
    //     return null; // Return null if note doesn't exist
    // }

    // // Method to delete an existing note
    // public void deleteNote(Long id) {
    //     noteRepository.deleteById(id);
    // }
}
