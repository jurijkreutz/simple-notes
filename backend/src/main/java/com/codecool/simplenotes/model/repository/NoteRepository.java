package com.codecool.simplenotes.model.repository;

import com.codecool.simplenotes.model.Note;
import jakarta.el.PropertyNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Repository
public class NoteRepository {

    private List<Note> allNotes;

    public NoteRepository() {
        this.allNotes = new ArrayList<>();
    }

    public void addNote(Note note) {
        allNotes.add(note);
    }

    public void removeNote(int id) {
        allNotes.removeIf(note -> note.getId() == id);
    }

    public Note updateNote(int id, Note updatedNote) {
        Note noteToUpdate = allNotes.stream().filter(note -> note.getId() == id)
                .findFirst()
                .orElseThrow(PropertyNotFoundException::new);
        noteToUpdate.setContent(updatedNote.getContent());
        noteToUpdate.setTitle(updatedNote.getTitle());
        return noteToUpdate;
    }

}
