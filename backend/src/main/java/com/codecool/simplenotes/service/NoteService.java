package com.codecool.simplenotes.service;

import com.codecool.simplenotes.config.UserDetailsImplementation;
import com.codecool.simplenotes.model.Note;
import com.codecool.simplenotes.model.User;
import com.codecool.simplenotes.model.repository.NoteRepository;
import com.codecool.simplenotes.model.repository.UserRepository;
import jakarta.el.PropertyNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<Note> getNotes() {
        Optional<User> user = getCurrentUser();
        return user.map(User::getNotes).orElse(null);
    }

    public Note createNote(Note note) {
        Optional<User> user = getCurrentUser();
        if (user.isPresent()) {
            user.get().getNotes().add(note);
            noteRepository.save(note);
            userRepository.save(user.get());
            return note;
        }
        else {
            throw new ObjectNotFoundException(User.class, "User");
        }
    }

    public void removeNote(int id) {
        Optional<User> user = getCurrentUser();
        if (user.isPresent()) {
            Optional<Note> foundNote = user.get().getNotes()
                    .stream()
                    .filter(note -> note.getId() == id)
                    .findFirst();
            if (foundNote.isPresent()) {
                Integer noteId = foundNote.get().getId();
                user.get().getNotes().removeIf(note ->
                        Objects.equals(note.getId(), noteId));
                userRepository.save(user.get());
                if (noteRepository.findById(noteId).isPresent()) {
                    noteRepository.deleteById(noteId);
                }
            }
        }
        else {
            throw new ObjectNotFoundException(User.class, "User");
        }
    }

    //TODO: add updating by user (user A shouldn't be able to update note of user B)
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


    private Optional<User> getCurrentUser() {
        UserDetailsImplementation userDetails = (UserDetailsImplementation) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return userRepository.findById(userDetails.getId());
    }

}
