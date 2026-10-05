package com.example.LibraryManagement.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BorrowRequestDTO {

    @NotBlank(message = "Reader Name is required")
    private String readerName;

    @NotBlank(message = "Reader Id is required")
    private long readerId;

    @NotBlank(message = "Book Name is required")
    private String bookName;

    @NotBlank(message = "Book Id is required")
    private long bookId;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^[0-9]{10,13}$", message = "ISBN must be between 10 and 13 digits")
    private String ISBN;

    @Min(value = 1, message = "At least One Copy Must be registered")
    private int numberOfCopies;

    @Min(value = 1900, message = "Enter books published after 1900")
    private int publishedYear;

    @NotNull(message = "Author ID is required")
    private long authorId;

}
