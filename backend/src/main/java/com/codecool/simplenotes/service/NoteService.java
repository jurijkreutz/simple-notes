package com.codecool.simplenotes.service;

import com.codecool.simplenotes.model.Note;
import com.codecool.simplenotes.model.repository.NoteRepository;
import jakarta.el.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public Note createNote(Note note) {
        noteRepository.save(note);
        return note;
    }

    public void removeNote(int id) {
        noteRepository.deleteById(id);
    }

    public Note updateNote(int id, Note updatedNote) {
        Optional<Note> noteToUpdate = noteRepository.findById(id);
        if (noteToUpdate.isPresent()) {
            noteToUpdate.get().setTitle(updatedNote.getTitle());
            noteToUpdate.get().setContent(updatedNote.getContent());
            return noteRepository.save(noteToUpdate.get());
        }
        return null;
    }

    public List<Note> getNotesByTitle(String title) {
        return noteRepository.findNotesByTitleContainsIgnoreCase(title);
    }

}
