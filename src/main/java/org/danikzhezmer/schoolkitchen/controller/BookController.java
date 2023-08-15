package org.danikzhezmer.schoolkitchen.controller;

import org.danikzhezmer.schoolkitchen.entity.Book;
import org.danikzhezmer.schoolkitchen.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/new_book")
    public String newBookForm(Model model){
        model.addAttribute("book", new Book());
        return "new_book";
    }
    @PostMapping ("/new_book")
    public String submitForm(@ModelAttribute Book book, Model model){
        model.addAttribute("book", book);
        return "book_saved";
    }
}
