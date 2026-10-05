package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.DTO.BookRequestDTO;
import com.example.LibraryManagement.DTO.BookResponseDTO;

import com.example.LibraryManagement.Entity.Author;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Repository.AuthorRepo;
import com.example.LibraryManagement.Repository.BookRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LibraryService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public BookResponseDTO createBook(BookRequestDTO dto) {

        if(bookRepo.existsByisbn(dto.getISBN())) {
            throw new IllegalArgumentException("Book with ISBN " + dto.getISBN() + " number already exists");
        }

        Book book = new Book();
       Optional<Author> author = authorRepo.findById(dto.getAuthorId());
       String authorName = author.map(Author::getAuthorName).orElse("Unknown Author");
       book.setBookName(dto.getBookName());
       book.setIsbn(dto.getISBN());
       book.setAvailableCopies(dto.getNumberOfCopies());
       book.setPublishedYear(dto.getPublishedYear());
       book.setNumberOfCopies(dto.getNumberOfCopies());
       book.setAuthorName(authorName);

        Book saved = bookRepo.save(book);
        return mapToDTO(saved);
    }

    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepo.getById(id);
        return mapToDTO(book);
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public void deleteById(Long id) {
        bookRepo.deleteById(id);
    }


    public static BookResponseDTO mapToDTO(Book book){
        BookResponseDTO response = new BookResponseDTO();
        response.setId(book.getId());
        response.setISBN(book.getIsbn());
        response.setBookName(book.getBookName());
        response.setAvailableCopies(book.getAvailableCopies());
        response.setPublishedYear((book.getPublishedYear()));
        response.setAuthorName(book.getAuthorName());

        return response;
    }
}
