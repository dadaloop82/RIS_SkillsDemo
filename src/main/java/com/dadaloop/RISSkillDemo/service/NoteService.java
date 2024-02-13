package com.dadaloop.RISSkillDemo.service;

import com.dadaloop.RISSkillDemo.model.NoteEntity;
import com.dadaloop.RISSkillDemo.repository.NoteRepository; // Make sure to import the correct repository
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

/**
 * The NoteService class provides business logic for managing notes.
 * It uses the NoteRepository to interact with the database.
 * This class is annotated with @Service to indicate that it's a Spring service component.
 */
@Service
public class NoteService {

  private static final Logger LOGGER = Logger.getLogger(NoteService.class.getName());


  @Autowired
  private NoteRepository noteRepository; // Autowiring the NoteRepository to access its methods

  /**
   * Retrieves all notes from the database.
   *
   * @return A list of NoteEntity instances representing all notes in the database.
   */
  public List<NoteEntity> getAllNotes() {
    return noteRepository.findAll();
  }

  /**
   * Retrieves a note by its ID.
   *
   * @param id The ID of the note to retrieve.
   * @return An Optional containing the NoteEntity if found, or an empty Optional if not found.
   */
  public Optional<NoteEntity> getNoteById(Long id) {
    return noteRepository.findById(id);
  }

  /**
   * Adds a new note to the database.
   *
   * @param note The NoteEntity to add to the database.
   * @return The saved NoteEntity with its new ID.
   */
  public NoteEntity addNote(NoteEntity note) {
    return noteRepository.save(note);
  }

  /**
   * Updates an existing note in the database.
   *
   * @param id The ID of the note to update.
   * @param updatedNote The NoteEntity containing the updated fields.
   * @return An Optional containing the updated NoteEntity, or an empty Optional if the note does not exist.
   */
  public Optional<NoteEntity> updateNote(Long id, NoteEntity updatedNote) {
    Optional<NoteEntity> optionalExistingNote = noteRepository.findById(id);
    if (optionalExistingNote.isPresent()) {
      NoteEntity existingNote = optionalExistingNote.get();
      existingNote.setTitle(updatedNote.getTitle());
      existingNote.setText(updatedNote.getText());
      LOGGER.info("Note with ID " + id + " successfully updated.");
      return Optional.of(noteRepository.save(existingNote));
    }
    return Optional.empty();
  }

  /**
   * Deletes an existing note from the database.
   *
   * @param id The ID of the note to delete.
   * @return true if the note was successfully deleted, false if the note does not exist.
   */
  public boolean deleteNote(Long id) {
    if (noteRepository.existsById(id)) {
      noteRepository.deleteById(id);
      LOGGER.info("Note with ID " + id + " successfully deleted.");
      return true;
    }
    LOGGER.warning(
      "Unable to delete note with ID " + id + ". Note does not exist."
    );
    return false;
  }

  /**
   * Checks the connection status to the database.
   *
   * @return true if the connection is active, false if it's inactive.
   */
  public boolean checkDBConnection() {
    try {
      noteRepository.count();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Retrieves the number of records in the database.
   *
   * @return The number of records in the database.
   */
  public int getRecordCount() {
    return (int) noteRepository.count();
  }
}
