package com.example.LibraryManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Books")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(nullable = false, name = "Book Name")
    private String bookName;

    @Column(nullable = false, unique = true, name = "ISBN")
    private String isbn;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "authorId", nullable = false)
     private Author author;

    @Column(nullable = false, name = "Author Name")
    private String authorName;

    private int publishedYear;

    private int numberOfCopies;

    private int availableCopies;

}
