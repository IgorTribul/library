package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Reader;
import com.example.library.repository.AuthorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/author/readersByAuthorId/{authorId}")
    public List<Reader> getReadersByAuthorId(@PathVariable Integer authorId){
        Optional<Author> author = authorRepository.findById(authorId);
        if(author.isPresent()){
            List<Book> booksByAuthor = author.get().getBooks();
            List<Reader> readers = new ArrayList<>();
            for (Book b :booksByAuthor){
                if (b.getReaders() != null) {
                    readers.addAll(b.getReaders());
                }
            }
            return readers;
        }
        return Collections.emptyList();
    }

    @GetMapping("/author/booksByAuthorId/{authorId}")
    public List<Book> getBooksByAuthorId(@PathVariable Integer authorId){
        Optional<Author> author = authorRepository.findById(authorId);
        if(author.isPresent()){
            return author.get().getBooks();
        }
        return Collections.emptyList();
    }
}
