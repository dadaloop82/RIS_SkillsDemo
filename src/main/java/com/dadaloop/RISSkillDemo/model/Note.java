package com.dadaloop.RISSkillDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Represents the entity of notes with JPA annotations for data persistence in the database.
 */
@Entity
public class Note {
    // Auto-generated unique identifier for the note
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Title of the note
    private String title;

    // Text content of the note
    private String text;

    // Constructors and getter/setter methods for id, title, and text
    public Note() {
    }

    // Constructor with fields
    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }

    // Getter and setter methods for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and setter methods for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter methods for text
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
