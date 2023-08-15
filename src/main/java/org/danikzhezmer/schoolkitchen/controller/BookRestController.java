package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.entity.Book;
import org.danikzhezmer.schoolkitchen.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/books")
public class BookRestController {
    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
