package com.example.LibraryManagement.DTO;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReaderRequestDTO {

    @NotBlank(message = "Book Name is required")
    private String readerName;

    @NotNull(message = "Author ID is required")
    private long authorId;

    @Email
    private String email;

    @NotBlank
    private String phone;

    @Min(value = 16, message = "Minimum 16 years age is required")
    private int age;

}
