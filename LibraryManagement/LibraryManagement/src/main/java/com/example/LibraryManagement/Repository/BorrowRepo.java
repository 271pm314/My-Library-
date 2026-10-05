package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.BookStatus;
import com.example.LibraryManagement.Entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepo extends JpaRepository < BorrowRecord, Long> {

    BorrowRecord findByBook_IdAndReader_IdAndStatus (long a, long b, BookStatus s);

    List<Book> getBooksByReaderId(long a);

    int countByReaderIdAndStatus(long id, BookStatus s);



}
