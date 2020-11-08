package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {

    Reader findReaderByReaderName(String readerName);
    boolean existsReaderByReaderName(String readerName);
}
