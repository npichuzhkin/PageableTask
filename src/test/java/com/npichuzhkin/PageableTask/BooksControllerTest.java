package com.npichuzhkin.PageableTask;

import com.npichuzhkin.PageableTask.controllers.BooksController;
import com.npichuzhkin.PageableTask.services.BookService;
import com.npichuzhkin.PageableTask.views.dto.AuthorDTO;
import com.npichuzhkin.PageableTask.views.dto.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(BooksController.class)
public class BooksControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(new BooksController(bookService)).build();
    }

    @Test
    public void showAllBooksShouldReturnListOfBooks() throws Exception {
        BookDTO bookDTO = new BookDTO("Book", new AuthorDTO("Author"));
        when(bookService.findAll()).thenReturn(Collections.singletonList(bookDTO));

        mockMvc.perform(get("/api/v1/books/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Book"))
                .andExpect(jsonPath("$[0].author.name").value("Author"));
    }

    @Test
    public void showBookShouldReturnBookById() throws Exception {
        UUID bookId = UUID.randomUUID();
        BookDTO bookDTO = new BookDTO("Book",  new AuthorDTO("Author"));
        when(bookService.findById(bookId)).thenReturn(bookDTO);

        mockMvc.perform(get("/api/v1/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Book"))
                .andExpect(jsonPath("$.author.name").value("Author"));
    }

    @Test
    public void addBookShouldReturnOkStatus() throws Exception {

        mockMvc.perform(post("/api/v1/books/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Book\", \"author\":\"Author\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBookShouldReturnOkStatus() throws Exception {

        mockMvc.perform(put("/api/v1/books/{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated\", \"author\":\"Updated\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBookShouldReturnOkStatus() throws Exception {

        mockMvc.perform(delete("/api/v1/books/{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
