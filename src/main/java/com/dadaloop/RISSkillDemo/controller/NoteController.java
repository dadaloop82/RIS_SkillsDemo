package com.dadaloop.RISSkillDemo.controller;

import com.dadaloop.RISSkillDemo.model.Note;
// import com.dadaloop.RISSkillDemo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Defines REST controllers to handle requests related to notes.
 */
// @RestController
// @RequestMapping("/api/notes")
public class NoteController {
    // // Autowired service for managing notes
    // @Autowired
    // private NoteService noteService;

    // // Endpoint to get all notes
    // @GetMapping
    // public List<Note> getAllNotes() {
    //     // Returns all notes
    //     return noteService.getAllNotes();
    // }

    // // Endpoint to add a new note
    // @PostMapping
    // public ResponseEntity<Note> addNote(@RequestBody Note note) {
    //     // Adds a new note and returns it with HTTP status CREATED
    //     Note addedNote = noteService.addNote(note);
    //     return new ResponseEntity<>(addedNote, HttpStatus.CREATED);
    // }

    // // Endpoint to update an existing note
    // @PutMapping("/{id}")
    // public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody Note updatedNote) {
    //     // Updates an existing note and returns it with HTTP status OK, or returns
    //     // NOT_FOUND if the note doesn't exist
    //     Note updated = noteService.updateNote(id, updatedNote);
    //     if (updated != null) {
    //         return new ResponseEntity<>(updated, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

    // // Endpoint to delete an existing note
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
    //     // Deletes an existing note and returns NO_CONTENT
    //     noteService.deleteNote(id);
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
}
