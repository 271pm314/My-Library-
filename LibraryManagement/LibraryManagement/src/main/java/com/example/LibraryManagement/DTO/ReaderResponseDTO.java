package com.example.LibraryManagement.DTO;

import com.example.LibraryManagement.Entity.Book;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ReaderResponseDTO {

        private long readerId;
        private long age;
        private String readerName;
        private String email;
        private List<Book> books;
        private String phone;


    }


