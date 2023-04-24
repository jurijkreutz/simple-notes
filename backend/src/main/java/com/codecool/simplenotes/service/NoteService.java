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

    public void createNote(Note note) {
        noteRepository.getAllNotes().add(note);
    }

    public void removeNote(int id) {
        noteRepository.getAllNotes().removeIf(note -> note.getId() == id);
    }

    public void updateNote(int id, Note updatedNote) {
        Note noteToUpdate = noteRepository.getAllNotes().stream().filter(note -> note.getId() == id)
                .findFirst()
                .orElseThrow(PropertyNotFoundException::new);
        noteToUpdate.setContent(updatedNote.getContent());
        noteToUpdate.setTitle(updatedNote.getTitle());
    }
}
