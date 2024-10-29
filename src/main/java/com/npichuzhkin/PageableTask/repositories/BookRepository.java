package com.npichuzhkin.PageableTask.repositories;

import com.npichuzhkin.PageableTask.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>, PagingAndSortingRepository<Book, UUID> {

}
