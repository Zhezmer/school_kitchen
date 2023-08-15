package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.entity.Book;
import org.danikzhezmer.schoolkitchen.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") Long id, Model model){
        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "book_card";
    }

    @GetMapping
    public String getBookList(Model model){
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books",books);
        return "book_list";
    }
}
