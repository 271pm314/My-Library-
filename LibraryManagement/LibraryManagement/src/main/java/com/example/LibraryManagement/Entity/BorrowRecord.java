package com.example.LibraryManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter  @Setter
@AllArgsConstructor @NoArgsConstructor
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @JoinColumn(name = "bookId", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @JoinColumn(name = "readerId", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Reader reader;

    @Column(nullable = false)
    private LocalDate borrowDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus status;

    private double fine;


}
