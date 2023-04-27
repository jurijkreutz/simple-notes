package com.codecool.simplenotes.service;

import com.codecool.simplenotes.model.Note;
import com.codecool.simplenotes.model.repository.NoteRepository;
import jakarta.el.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotes() {
        return noteRepository.getAllNotes();
    }

    public Note createNote(Note note) {
        noteRepository.addNote(note);
        return note;
    }

    public void removeNote(int id) {
        noteRepository.removeNote(id);
    }

    public Note updateNote(int id, Note updatedNote) {
        return noteRepository.updateNote(id, updatedNote);
    }
}
