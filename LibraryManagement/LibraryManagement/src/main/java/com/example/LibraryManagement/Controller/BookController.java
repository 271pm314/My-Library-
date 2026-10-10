package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.DTO.BookRequestDTO;
import com.example.LibraryManagement.DTO.BookResponseDTO;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.LibraryManagement.Service.BookService.mapToDTO;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

   private final BookService lService;

    public BookController(BookService lService) {
        this.lService = lService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody @Valid BookRequestDTO req){
        BookResponseDTO response = lService.createBook(req);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> searchBook(@PathVariable Long id) {
        return ResponseEntity.ok(lService.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> searchBooks() {
       List<Book> books = lService.getAllBooks();
       List<BookResponseDTO> response = new ArrayList<>();

       for(Book book : books){
           response.add(mapToDTO(book));
       }
        return new ResponseEntity<>(response, HttpStatus.OK); // Return the list with a 200 OK status

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        lService.deleteById(id);
        return ResponseEntity.notFound().build();

    }

}
