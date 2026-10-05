package com.example.LibraryManagement.DTO;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

    @Data
    public class AuthorRequestDTO {

        @Id
        @NotNull(message = "Author ID is required")
        private long authorId;

        @NotBlank(message = "Author Name is required")
        private String authorName;

        @Email
        private String email;



    }

