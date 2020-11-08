package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Reader;
import com.example.library.repository.ReaderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class ReaderController {

    private final ReaderRepository readerRepository;

    public ReaderController(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @GetMapping("reader/booksOfReader/{id}")

    public List<Book> getLibrary(@PathVariable Integer id){
        Optional<Reader> readerOptional = readerRepository.findById(id);
        if(readerOptional.isPresent()){
            return readerOptional.get().getLibrary();
        }else {
            return Collections.emptyList();
        }
    }
}
