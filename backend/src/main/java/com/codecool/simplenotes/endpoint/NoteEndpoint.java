package com.codecool.simplenotes.endpoint;

import com.codecool.simplenotes.model.Note;
import com.codecool.simplenotes.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/api/notes")
public class NoteEndpoint {

    private final NoteService noteService;

    public NoteEndpoint(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getNotes() {
        return noteService.getNotes();
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        noteService.createNote(note);
        return note;
    }

    @DeleteMapping("/{id}")
    public void removeNote(@PathVariable("id") int id) {
        noteService.removeNote(id);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable("id") int id, @RequestBody Note note) {
        return noteService.updateNote(id, note);
    }

}
