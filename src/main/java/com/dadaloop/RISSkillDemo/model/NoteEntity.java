package com.dadaloop.RISSkillDemo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/notes")
public class NoteEntity {

    private String title;
    private String text;

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
            String title = "Title " + (i + 1);
            String text = "Text " + (i + 1);
            NoteEntity note = new NoteEntity(title, text);
            notes.add(note);
        }
        return notes;
    }
}
