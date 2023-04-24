package com.codecool.simplenotes.endpoint;

import com.codecool.simplenotes.model.Note;
import com.codecool.simplenotes.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = {"*"}, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/notes/")
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
    public void createNote(@RequestBody Note note) {
        noteService.createNote(note);
    }

    @DeleteMapping("/{id}")
    public void removeNote(@PathVariable("id") int id) {
        noteService.removeNote(id);
    }

    @PutMapping("/{id}")
    public void updateNote(@PathVariable("id") int id, @RequestBody Note note) {
        noteService.updateNote(id, note);
    }

}
