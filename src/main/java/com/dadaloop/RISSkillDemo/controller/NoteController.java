package com.dadaloop.RISSkillDemo.controller;

import com.dadaloop.RISSkillDemo.model.NoteEntity;
import com.dadaloop.RISSkillDemo.service.NoteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.lang.NonNull;

@RestController // Marks this class as a REST controller, capable of handling HTTP requests.
@RequestMapping("/api/notes") // Maps HTTP requests to /api/notes to methods in this controller.
public class NoteController {

  @Autowired
  private NoteService noteService; // Injects the NoteService to use its functionalities.

  /**
   * Retrieves all notes from the database.
   *
   * @return A ResponseEntity containing a list of NoteEntity instances and the
   *         HTTP status.
   */
  @GetMapping
  public ResponseEntity<List<NoteEntity>> getAllNotes() {
    try {
      List<NoteEntity> allNotes = noteService.getAllNotes();
      return new ResponseEntity<>(allNotes, HttpStatus.OK); // Return the list of notes with status OK.
    } catch (Exception e) {
      // Return Internal Server Error if an exception occurs
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Retrieves a single note by its ID.
   *
   * @param id The ID of the note to retrieve.
   * @return A ResponseEntity with the found note or a not found status.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getNoteById(@PathVariable("id") @NonNull Long id) {
    try {
      Optional<NoteEntity> optionalNote = noteService.getNoteById(id);
      if (optionalNote.isPresent()) {
        return new ResponseEntity<>(optionalNote.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Adds a new note to the database.
   *
   * @param note The NoteEntity to add.
   * @return A ResponseEntity with the saved note and the HTTP status.
   */
  @PostMapping
  public ResponseEntity<?> addNote(@RequestBody @NonNull NoteEntity note) {
    try {
      NoteEntity savedNote = noteService.addNote(note);
      return new ResponseEntity<>(savedNote, HttpStatus.CREATED); // Return the saved note with status CREATED.
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return Internal Server Error on exception.
    }
  }

  /**
   * Updates an existing note identified by its ID.
   *
   * @param id          The ID of the note to update.
   * @param updatedNote The new data for the note.
   * @return A ResponseEntity with the updated note or a not found status.
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> updateNote(
      @PathVariable("id") @NonNull Long id,
      @RequestBody @NonNull NoteEntity updatedNote) {
    try {
      Optional<NoteEntity> updatedNoteEntityOptional = noteService.updateNote(
          id,
          updatedNote);
      if (updatedNoteEntityOptional.isPresent()) {
        return new ResponseEntity<>(
            updatedNoteEntityOptional.get(),
            HttpStatus.OK); // Return the updated note with status OK.
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return Not Found if the note doesn't exist.
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return Internal Server Error on exception.
    }
  }

  /**
   * Deletes an existing note identified by its ID.
   *
   * @param id The ID of the note to delete.
   * @return A ResponseEntity indicating the result of the delete operation.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteNote(@PathVariable("id") @NonNull Long id) {
    try {
      boolean deleted = noteService.deleteNote(id);
      if (deleted) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return No Content if the note was successfully deleted.
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return Not Found if the note doesn't exist.
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return Internal Server Error on exception.
    }
  }
}
