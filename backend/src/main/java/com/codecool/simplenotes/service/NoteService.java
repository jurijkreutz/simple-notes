package com.codecool.simplenotes.service;

import com.codecool.simplenotes.config.UserDetailsImplementation;
import com.codecool.simplenotes.model.Note;
import com.codecool.simplenotes.model.User;
import com.codecool.simplenotes.model.repository.NoteRepository;
import com.codecool.simplenotes.model.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<Note> getNotes() {
        Optional<User> user = getCurrentUser();
        if (user.isPresent()) {
            return user.get().getNotes();
        }
        throw new ObjectNotFoundException(User.class, "User not found.");
    }

    public Note createNote(Note note) {
        Optional<User> user = getCurrentUser();
        if (user.isPresent()) {
            user.get().getNotes().add(note);
            userRepository.save(user.get());
            return note;
        }
        else {
            throw new ObjectNotFoundException(User.class, "User not found.");
        }
    }

    public void removeNote(int id) {
        Optional<User> user = getCurrentUser();
        if (user.isEmpty()) {
            throw new ObjectNotFoundException(User.class, "User not found.");
        }
        Optional<Note> foundNote = user.get().getNotes()
                .stream()
                .filter(note -> note.getId() == id)
                .findFirst();
        if (foundNote.isEmpty()) {
            throw new ObjectNotFoundException(Note.class, "Note to be removed not found.");
        }
        user.get().getNotes().removeIf(note ->
                Objects.equals(note.getId(), id));
        userRepository.save(user.get());
    }

    public Note updateNote(int id, Note updatedNote) {
        Optional<User> user = getCurrentUser();
        if (user.isPresent()) {
            Optional<Note> noteToUpdate = user.get().getNotes()
                    .stream()
                    .filter(note -> note.getId() == id)
                    .findFirst();
            if (noteToUpdate.isPresent()) {
                noteToUpdate.get().setTitle(updatedNote.getTitle());
                noteToUpdate.get().setContent(updatedNote.getContent());
                userRepository.save(user.get());
                return noteToUpdate.get();
            }
            else {
                throw new ObjectNotFoundException(Note.class, "Note to be updated not found.");
            }
        }
        throw new ObjectNotFoundException(User.class, "User not found.");
    }

    public List<Note> getNotesByTitle(String title) {
        return noteRepository.findNotesByTitleContainsIgnoreCase(title);
    }


    private Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (authentication != null) {
            UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
            return userRepository.findById(userDetails.getId());
        }
        return Optional.empty();
    }

}
