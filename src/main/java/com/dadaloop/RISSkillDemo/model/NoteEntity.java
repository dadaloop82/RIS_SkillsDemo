package com.dadaloop.RISSkillDemo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
@RequestMapping("/api/notes")
public class NoteEntity {

    @JsonProperty("title")
    private String title;

    @JsonProperty("text")
    private String text;

    // Costruttore senza argomenti
    public NoteEntity() {}

    public NoteEntity(String title, String text) {
        this.title = title;
        this.text = text;
    }

    // Method to generate random notes
    public static List<NoteEntity> generateRandomNotes() {
        List<NoteEntity> notes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String title = "Test Titolo " + random.nextInt(100); // Genera un numero casuale da 0 a 99 e lo concatena al titolo
            String text = "Test Testo " + random.nextInt(100); // Genera un numero casuale da 0 a 99 e lo concatena al testo
            NoteEntity note = new NoteEntity(title, text);
            notes.add(note);
        }
        return notes;
    }
}