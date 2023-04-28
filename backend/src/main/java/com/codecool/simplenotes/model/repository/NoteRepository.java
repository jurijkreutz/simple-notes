package com.codecool.simplenotes.model.repository;

import com.codecool.simplenotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    /*public Note updateNote(int id, Note updatedNote) {
        Note noteToUpdate = allNotes.stream().filter(note -> note.getId() == id)
                .findFirst()
                .orElseThrow(PropertyNotFoundException::new);
        noteToUpdate.setContent(updatedNote.getContent());
        noteToUpdate.setTitle(updatedNote.getTitle());
        return noteToUpdate;
    }*/

}
