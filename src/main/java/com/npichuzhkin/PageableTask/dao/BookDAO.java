package com.npichuzhkin.PageableTask.dao;

import com.npichuzhkin.PageableTask.models.Book;
import com.npichuzhkin.PageableTask.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class BookDAO {

    private final BookRepository bookRepository;

    @Autowired
    public BookDAO(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void create(Book newBook){
        bookRepository.save(newBook);
    }

    @Transactional
    public void update(Book updatedBook){
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(UUID id){
        bookRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public Book findById(UUID id){
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Page<Book> findAll(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
}
