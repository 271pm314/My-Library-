package com.example.LibraryManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class BookResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String bookName;
    private String ISBN;
    private String authorName;
    private int publishedYear;
    private int availableCopies;


}
