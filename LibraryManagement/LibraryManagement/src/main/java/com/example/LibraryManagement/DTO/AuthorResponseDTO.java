package com.example.LibraryManagement.DTO;

import com.example.LibraryManagement.Entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter @Setter
public class AuthorResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private long authorId;

    private String authorName;
    private String email;
    private List<Book> books;

}
