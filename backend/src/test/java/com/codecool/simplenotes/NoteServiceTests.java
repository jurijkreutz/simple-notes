package com.codecool.simplenotes;

import com.codecool.simplenotes.model.Note;
import com.codecool.simplenotes.model.repository.NoteRepository;
import com.codecool.simplenotes.service.NoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NoteServiceTests {

    @Test
    public void getNotes_NoNoteAdded_ReturnsEmptyList() {
        NoteRepository repository = Mockito.mock(NoteRepository.class);
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
        NoteService service = new NoteService(repository);

        List<Note> noteList = service.getNotes();

        Assertions.assertEquals(new ArrayList<>(), noteList);
    }

    @Test
    public void getNotes_OneNoteAdded_ReturnsListWithOneNote() {
        NoteRepository repository = Mockito.mock(NoteRepository.class);
        Note newNote = new Note("test", "testcontent");
        List<Note> simulatedList = new ArrayList<>();
        simulatedList.add(newNote);
        Mockito.when(repository.findAll()).thenReturn(simulatedList);
        NoteService service = new NoteService(repository);

        List<Note> noteList = service.getNotes();

        Assertions.assertEquals(simulatedList, noteList);
    }

    @Test
    public void createNote_AddOneNote_CallsRepositoryMethod() {
        NoteRepository repository = Mockito.mock(NoteRepository.class);
        NoteService service = new NoteService(repository);

        Note noteToAdd = new Note("test", "testnote");

        service.createNote(noteToAdd);

        Mockito.verify(repository).save(noteToAdd);
    }

    @Test
    public void createNote_AddOneNote_ReturnsAddedNote() {
        NoteRepository repository = Mockito.mock(NoteRepository.class);
        NoteService service = new NoteService(repository);
        Note noteToAdd = new Note("test", "testnote");

        Note addedNote = service.createNote(noteToAdd);

        Assertions.assertEquals(noteToAdd, addedNote);
    }

    @Test
    public void removeNote_RemoveOneNote_CallsRepositoryMethod() {
        NoteRepository repository = Mockito.mock(NoteRepository.class);
        NoteService service = new NoteService(repository);
        int id = 1;

        service.removeNote(id);

        Mockito.verify(repository).deleteById(id);
    }

    @Test
    public void updateNote_UpdateOneNote_ReturnsUpdatedNote() {
        NoteRepository repository = Mockito.mock(NoteRepository.class);
        NoteService service = new NoteService(repository);
        Note noteToUpdate = new Note("testtitle", "testcontent");
        int idToUpdate = 1;
        noteToUpdate.setId(idToUpdate);
        Note updatedNote = new Note("testtitle1", "testcontent1");
        Mockito.when(repository.findById(idToUpdate)).thenReturn(Optional.of(noteToUpdate));
        Mockito.when(repository.save(noteToUpdate)).thenReturn(updatedNote);

        Note returnedNote = service.updateNote(idToUpdate, updatedNote);

        Assertions.assertEquals("testtitle1", returnedNote.getTitle());
        Assertions.assertEquals("testcontent1", returnedNote.getContent());
    }

}
