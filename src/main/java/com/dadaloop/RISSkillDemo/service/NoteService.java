package com.dadaloop.RISSkillDemo.service;

import com.dadaloop.RISSkillDemo.model.NoteEntity;
import com.dadaloop.RISSkillDemo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class NoteService {

  private static final Logger LOGGER = Logger.getLogger(NoteService.class.getName());

  @Autowired
  private NoteRepository noteRepository;

  public List<NoteEntity> getAllNotes() {
    return noteRepository.findAll();
  }

  public Optional<NoteEntity> getNoteById(@NonNull Long id) {
    return noteRepository.findById(id);
  }

  public NoteEntity addNote(@NonNull NoteEntity note) {
    return noteRepository.save(note);
  }

  public Optional<NoteEntity> updateNote(@NonNull Long id, @NonNull NoteEntity updatedNote) {
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

  public boolean deleteNote(@NonNull Long id) {
    if (noteRepository.existsById(id)) {
      noteRepository.deleteById(id);
      LOGGER.info("Note with ID " + id + " successfully deleted.");
      return true;
    }
    LOGGER.warning(
        "Unable to delete note with ID " + id + ". Note does not exist.");
    return false;
  }

  public boolean checkDBConnection() {
    try {
      noteRepository.count();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public int getRecordCount() {
    return (int) noteRepository.count();
  }
}
