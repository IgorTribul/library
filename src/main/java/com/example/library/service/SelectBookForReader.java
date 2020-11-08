package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Reader;
import com.example.library.repository.BookRepository;
import com.example.library.repository.ReaderRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SelectBookForReader {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public SelectBookForReader(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public void selectBook(String readerName, String bookName) {
        Book book = bookRepository.findBookByBookName(bookName);
        Reader reader = readerRepository.findReaderByReaderName(readerName);
        boolean chekReader = readerRepository.existsReaderByReaderName(readerName);
        List<Reader> readers = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();

        if (chekReader) {
            bookList = reader.getLibrary();
            bookList.add(book);
            reader.setLibrary(bookList);
            if(book.getReaders() == null){
                readers.add(reader);
                book.setReaders(readers);
            }else {
                readers = book.getReaders();
                readers.add(reader);
                book.setReaders(readers);
            }
        } else {
            Reader newReader = new Reader();
            newReader.setReaderName(readerName);
            bookList.add(book);
            newReader.setLibrary(bookList);
            readerRepository.save(newReader);
            if(book.getReaders() == null){
                readers.add(newReader);
                book.setReaders(readers);
            }else {
                readers = book.getReaders();
                readers.add(newReader);
                book.setReaders(readers);
            }
        }
    }
}

