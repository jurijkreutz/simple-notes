package com.codecool.simplenotes;

import com.codecool.simplenotes.model.Note;
import com.codecool.simplenotes.model.repository.NoteRepository;
import jakarta.el.PropertyNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RepositoryTests {

    @Test
    public void getNotes_NoNoteAdded_ReturnsEmptyList() {
        NoteRepository repository = new NoteRepository();
        List<Note> emptyList = new ArrayList<>();

        List<Note> noteList = repository.getAllNotes();

        Assertions.assertEquals(emptyList, noteList);
    }

    @Test
    public void addNote_OneNoteAdded_ReturnsCorrectList() {
        NoteRepository repository = new NoteRepository();
        Note noteToAdd = new Note("testtitle", "testcontent");
        List<Note> referenceList = new ArrayList<>();
        referenceList.add(noteToAdd);

        repository.addNote(noteToAdd);

        Assertions.assertEquals(referenceList, repository.getAllNotes());
    }

    @Test
    public void removeNote_OneNoteRemoved_ReturnsEmptyList() {
        NoteRepository repository = new NoteRepository();
        Note noteToAdd = new Note("testtitle", "testcontent");
        int noteId = 1;
        noteToAdd.setId(noteId);
        repository.addNote(noteToAdd);

        repository.removeNote(noteId);

        Assertions.assertEquals(new ArrayList<>(), repository.getAllNotes());
    }

    @Test
    public void updateNote_OneNoteChanged_ReturnsUpdatedNote() {
        NoteRepository repository = new NoteRepository();
        Note noteToAdd = new Note("testtitle", "testcontent");
        int noteId = 1;
        noteToAdd.setId(noteId);
        repository.addNote(noteToAdd);
        Note updatedNote = new Note("testtitle1", "testcontent1");

        Note returnedNote = repository.updateNote(noteId, updatedNote);

        Assertions.assertEquals(updatedNote.getTitle(), returnedNote.getTitle());
        Assertions.assertEquals(updatedNote.getContent(), returnedNote.getContent());
    }

    @Test
    public void updateNote_NoteDoesNotExist_ThrowsException() {
        NoteRepository repository = new NoteRepository();
        Note updatedNote = new Note("testtitle1", "testcontent1");

        Assertions.assertThrows(PropertyNotFoundException.class, () -> {
            repository.updateNote(1, updatedNote);
        });
    }

}
