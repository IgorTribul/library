package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Reader;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.service.AddNewBook;
import com.example.library.service.SelectBookForReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class BookController {

    private final AuthorRepository authorRepository;
    private final AddNewBook addNewBook;
    private final BookRepository bookRepository;
    private final SelectBookForReader selectBookForReader;

    public BookController(AuthorRepository authorRepository,
                          AddNewBook addNewBook,
                          BookRepository bookRepository,
                          SelectBookForReader selectBookForReader) {
        this.authorRepository = authorRepository;
        this.addNewBook = addNewBook;
        this.bookRepository = bookRepository;
        this.selectBookForReader = selectBookForReader;
    }

    @PostMapping ("/generate")
    public List<Author> findBooksByAuthorId(){

        addNewBook.addNewBook("Mumu", "Turgenev");
        addNewBook.addNewBook("Onegin", "Pushkin");
        addNewBook.addNewBook("Little tragedy", "Pushkin");
        addNewBook.addNewBook("Ruslan and Ludmila", "Pushkin");

        selectBookForReader.selectBook("Gena","Little tragedy");
        selectBookForReader.selectBook("Vova","Onegin");
        selectBookForReader.selectBook("Gena","Mumu");
        selectBookForReader.selectBook("Vova","Little tragedy");

        return authorRepository.findAll();
    }
    @GetMapping("/book/readersByBookId/{bookId}")
    public List<Reader> getReadersByBookId(@PathVariable Integer bookId){

        return bookRepository.getOne(bookId).getReaders();
    }
}
