package com.example.library;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Reader;
import com.example.library.service.AddNewBook;
import com.example.library.service.SelectBookForReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AndToAndTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AddNewBook addNewBook;
    @Autowired
    SelectBookForReader selectBookForReader;

    @Test
    public void dataGeneration() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Author[] allAuthors = objectMapper.readerFor(Author.class).readValue(response,Author[].class);
        List<Author> findAllAuthors = new ArrayList<>(Arrays.asList(allAuthors));

        Assertions.assertEquals(findAllAuthors.size(), 2);
        Assertions.assertFalse(findAllAuthors.isEmpty());

    }

    @Test
    public void getReadersByAuthorId() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/author/readersByAuthorId/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Reader[] allReaders = objectMapper.readerFor(Reader.class).readValue(response,Reader[].class);
        List<Reader> findAllReaders = new ArrayList<>(Arrays.asList(allReaders));

        Assertions.assertEquals(findAllReaders.size(), 1);
        Assertions.assertFalse(findAllReaders.isEmpty());
    }
    @Test
    public void getBooksByAuthorId() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/author/booksByAuthorId/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Book[] allBooks = objectMapper.readerFor(Book.class).readValue(response,Book[].class);
        List<Book> findAllBooks = new ArrayList<>(Arrays.asList(allBooks));

        Assertions.assertEquals(findAllBooks.size(), 3);
        Assertions.assertFalse(findAllBooks.isEmpty());
    }
    @Test
    public void getReadersByBookId() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/book/readersByBookId/3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Reader[] readersByBookId = objectMapper.readerFor(Reader.class).readValue(response,Reader[].class);
        List<Reader> readersOfBook = new ArrayList<>(Arrays.asList(readersByBookId));

        Assertions.assertEquals(readersOfBook.size(), 1);
        Assertions.assertFalse(readersOfBook.isEmpty());
    }

    @Test
    public void getLibraryOfReader() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/reader/booksOfReader/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Book[] books = objectMapper.readerFor(Book.class).readValue(response,Book[].class);
        List<Book> booksOfReader = new ArrayList<>(Arrays.asList(books));

        Assertions.assertEquals(booksOfReader.size(), 1);
        Assertions.assertFalse(booksOfReader.isEmpty());
    }
}
