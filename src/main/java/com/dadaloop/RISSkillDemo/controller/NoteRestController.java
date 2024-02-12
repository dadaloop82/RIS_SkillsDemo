package com.dadaloop.RISSkillDemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dadaloop.RISSkillDemo.model.NoteEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteRestController {

    private List<NoteEntity> notes = new ArrayList<>();

    // Endpoint to get all notes
    @GetMapping
    public ResponseEntity<List<NoteEntity>> getAllNotes() {
        NoteEntity noteEntity = new NoteEntity();
        List<NoteEntity> randomNotes = noteEntity.generateRandomNotes();
        return new ResponseEntity<>(randomNotes, HttpStatus.OK);
    }

    // Endpoint to add a new note
    @PostMapping
    public ResponseEntity<NoteEntity> addNote(@RequestBody NoteEntity note) {
        notes.add(note);
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    // Endpoint to update an existing note
    @PutMapping("/{id}")
    public ResponseEntity<NoteEntity> updateNote(@PathVariable("id") int id, @RequestBody NoteEntity updatedNote) {
        if (id >= 0 && id < notes.size()) {
            notes.set(id, updatedNote);
            return new ResponseEntity<>(updatedNote, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete an existing note
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") int id) {
        if (id >= 0 && id < notes.size()) {
            notes.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}