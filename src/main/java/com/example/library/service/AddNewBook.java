package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddNewBook {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AddNewBook(AuthorRepository authorRepository,
                      BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void addNewBook (String bookName, String authorName) {

        Book book = new Book();
        book.setBookName(bookName);
        boolean checkAuthor = authorRepository.existsAuthorByAuthorName(authorName);
        Author author = authorRepository.findByAuthorName(authorName);

        if (checkAuthor) {

            book.setAuthor(author);
            bookRepository.save(book);
            author.getBooks().add(book);

        }else {
            Author newAuthor = new Author();
            newAuthor.setAuthorName(authorName);
            authorRepository.save(newAuthor);
            book.setAuthor(newAuthor);
            bookRepository.save(book);
            List<Book> listBooksOfAuthor = new ArrayList<>();
            listBooksOfAuthor.add(book);
            newAuthor.setBooks(listBooksOfAuthor);
        }
    }
}
