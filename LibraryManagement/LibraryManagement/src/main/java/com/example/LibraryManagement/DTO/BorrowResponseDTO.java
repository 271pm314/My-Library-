package com.example.LibraryManagement.DTO;

import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.BookStatus;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowResponseDTO {

    private long borrowId;

    private Book book;

    private String bookName;

    private long bookId;

    private String ISBN;

    private int numberOfAvailableCopies;

    private int publishedYear;

    private long authorId;

    private String readerName;


    private LocalDate borrowDate;

    private LocalDate returnDate;

    private LocalDate dueDate;

    private BookStatus status;

    private double fine;

}
