package com.codecool.simplenotes;

import com.codecool.simplenotes.endpoint.NoteEndpoint;
import com.codecool.simplenotes.model.Note;
import com.codecool.simplenotes.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteEndpoint.class)
public class NoteEndpointTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Test
    public void get_BeforeAddingNotes_ShouldReturnEmptyJson() throws Exception {
        when(noteService.getNotes()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/api/notes/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void post_AddOneNote_ShouldReturnOk() throws Exception {
        Note note = new Note("Test Title", "Test Content");
        ObjectMapper objectMapper = new ObjectMapper();
        String noteJson = objectMapper.writeValueAsString(note);

        when(noteService.createNote(note)).thenReturn(note);

        this.mockMvc.perform(post("/api/notes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(noteJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.content").value("Test Content"));
    }

    @Test
    public void remove_RemoveOneNote_ShouldReturnOk() throws Exception {
        this.mockMvc.perform(delete("/api/notes/1")).andDo(print()).andExpect(status().isOk());
        verify(noteService).removeNote(1);
    }

    @Test
    public void put_UpdateNote_ShouldReturnUpdatedNote() throws Exception {
        Note note = new Note("Updated Title", "Test Content");
        ObjectMapper objectMapper = new ObjectMapper();
        String noteJson = objectMapper.writeValueAsString(note);

        when(noteService.updateNote(3, note)).thenReturn(note);

        this.mockMvc.perform(put("/api/notes/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(noteJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.content").value("Test Content"));
    }
}

