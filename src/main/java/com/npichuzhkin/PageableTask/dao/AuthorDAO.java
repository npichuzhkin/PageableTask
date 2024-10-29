package com.npichuzhkin.PageableTask.dao;

import com.npichuzhkin.PageableTask.models.Author;
import com.npichuzhkin.PageableTask.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class AuthorDAO {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorDAO(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public Author findByName(String name){
        return authorRepository.findAuthorByName(name);
    }

    @Transactional(readOnly = true)
    public Author findById(UUID id){
        return authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
