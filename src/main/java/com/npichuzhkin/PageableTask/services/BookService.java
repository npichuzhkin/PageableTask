package com.npichuzhkin.PageableTask.services;

import com.npichuzhkin.PageableTask.dao.AuthorDAO;
import com.npichuzhkin.PageableTask.dao.BookDAO;
import com.npichuzhkin.PageableTask.models.Author;
import com.npichuzhkin.PageableTask.models.Book;
import com.npichuzhkin.PageableTask.views.dto.AuthorDTO;
import com.npichuzhkin.PageableTask.views.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final BookDAO bookDAO;

    private final AuthorDAO authorDAO;

    @Autowired
    public BookService(BookDAO bookDAO, AuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
    }

    public void add(BookDTO bookDTO){
        String bookName = bookDTO.getName();
        Author author = authorDAO.findByName(bookDTO.getAuthor().getName());
        bookDAO.create(new Book(bookName, author));
    }

    public void update(UUID id, BookDTO bookDTO){
        String bookName = bookDTO.getName();
        Author author = authorDAO.findByName(bookDTO.getAuthor().getName());
        bookDAO.update(new Book(id, bookName, author));
    }

    public void delete(UUID id){
        bookDAO.delete(id);
    }

    public BookDTO findById(UUID id){
        Book book = bookDAO.findById(id);
        AuthorDTO authorDTO = new AuthorDTO(book.getAuthor().getName());
        return new BookDTO(book.getId(), book.getName(), authorDTO);
    }

    public List<BookDTO> findAll(){
        List<Book> books = bookDAO.findAll();
        List<BookDTO> bookDTOs = new LinkedList<>();

        for (Book book: books) {
            AuthorDTO authorDTO = new AuthorDTO(book.getAuthor().getName());
            bookDTOs.add(new BookDTO(book.getId(), book.getName(), authorDTO));
        }

        return bookDTOs;
    }

    public Page<BookDTO> findAll(Pageable pageable){
        Page<Book> books = bookDAO.findAll(pageable);
        List<BookDTO> bookDTOs = new LinkedList<>();

        for (Book book: books) {
            AuthorDTO authorDTO = new AuthorDTO(book.getAuthor().getName());
            bookDTOs.add(new BookDTO(book.getId(), book.getName(), authorDTO));
        }

        return new PageImpl<>(bookDTOs, PageRequest.of(books.getNumber(), books.getSize()), books.getTotalElements());
    }
}
