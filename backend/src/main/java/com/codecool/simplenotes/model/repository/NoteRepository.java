package com.codecool.simplenotes.model.repository;

import com.codecool.simplenotes.model.Note;
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

}
