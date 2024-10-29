package com.npichuzhkin.PageableTask.controllers;


import com.npichuzhkin.PageableTask.services.BookService;
import com.npichuzhkin.PageableTask.views.dto.BookDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDTO>> showAllBooks(){
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<BookDTO>> pageShowAllBooks(Pageable pageable){
        return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> showBook(@PathVariable UUID id){
        return  new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> addBook(@RequestBody @Valid BookDTO bookDTO){
        bookService.add(bookDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateBook(@PathVariable UUID id,
                                                 @RequestBody @Valid BookDTO bookDTO){
        bookService.update(id, bookDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable UUID id){
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
