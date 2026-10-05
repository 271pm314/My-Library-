package com.example.LibraryManagement.DTO;

import com.example.LibraryManagement.Entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter @Setter
public class AuthorResponseDTO {

    private long authorId;

    private String authorName;
    private String email;
    private List<Book> books;

}
