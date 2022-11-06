package com.example.junitproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.junitproject.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
