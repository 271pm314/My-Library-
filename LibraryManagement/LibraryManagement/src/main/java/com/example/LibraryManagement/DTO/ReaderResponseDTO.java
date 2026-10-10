package com.example.LibraryManagement.DTO;

import com.example.LibraryManagement.Entity.Book;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
public class ReaderResponseDTO implements Serializable {

        private static final long serialVersionUID = 1L;
        private long readerId;
        private long age;
        private String readerName;
        private String email;
        private List<Book> books;
        private String phone;


    }


