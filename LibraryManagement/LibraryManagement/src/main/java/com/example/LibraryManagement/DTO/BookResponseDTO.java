package com.example.LibraryManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookResponseDTO {

    private long id;
    private String bookName;
    private String ISBN;
    private String authorName;
    private int publishedYear;
    private int availableCopies;


}
