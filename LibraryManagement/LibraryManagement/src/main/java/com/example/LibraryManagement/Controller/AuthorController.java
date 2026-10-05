package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.DTO.AuthorRequestDTO;
import com.example.LibraryManagement.DTO.AuthorResponseDTO;
import com.example.LibraryManagement.Entity.Author;
import com.example.LibraryManagement.Service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController (AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody @Valid AuthorRequestDTO req){
        AuthorResponseDTO response = authorService.createAuthor(req);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> searchAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> searchAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        List<AuthorResponseDTO> response = new ArrayList<>();

        for(Author author : authors){
            response.add(AuthorService.mapToDTO(author));
        }
        return new ResponseEntity<>(response, HttpStatus.OK); // Return the list with a 200 OK status

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.notFound().build();

    }

}




