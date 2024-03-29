package com.dadaloop.RISSkillDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NoteEntity {

  /**
   * The ID field is the primary key of the entity.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The title field represents the title of the note.
   */
  private String title;

  /**
   * The text field represents the content of the note.
   */
  private String text;

  /**
   * Default constructor without arguments.
   */
  public NoteEntity() {}

  /**
   * Constructor with arguments to create a new NoteEntity instance with a title and text.
   * @param title The title of the note.
   * @param text The content of the note.
   */
  public NoteEntity(String title, String text) {
    this.title = title;
    this.text = text;
  }

  // Getters and Setters

  /**
   * Gets the ID of the note.
   * @return The ID of the note.
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the ID of the note.
   * @param id The new ID of the note.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the title of the note.
   * @return The title of the note.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of the note.
   * @param title The new title of the note.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets the text of the note.
   * @return The text of the note.
   */
  public String getText() {
    return text;
  }

  /**
   * Sets the text of the note.
   * @param text The new text of the note.
   */
  public void setText(String text) {
    this.text = text;
  }
}
