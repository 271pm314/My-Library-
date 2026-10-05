package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.DTO.AuthorRequestDTO;
import com.example.LibraryManagement.DTO.AuthorResponseDTO;
import com.example.LibraryManagement.Entity.Author;
import com.example.LibraryManagement.Repository.AuthorRepo;
import com.example.LibraryManagement.Repository.BookRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@AllArgsConstructor
public class AuthorService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto) {

        if(authorRepo.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Author with Email " + dto.getEmail() + " already exists");
        }
        Author author= new Author();
        author.setAuthorName(dto.getAuthorName());
        author.setEmail(dto.getEmail());


        Author saved = authorRepo.save(author);
        return mapToDTO(saved);
    }

    public AuthorResponseDTO getAuthorById(Long id) {
        Author author = authorRepo.getById(id);
        return mapToDTO(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public void deleteById(Long id) {
        authorRepo.deleteById(id);
    }


    public static AuthorResponseDTO mapToDTO(Author author){
        AuthorResponseDTO response = new AuthorResponseDTO();
        response.setAuthorId(author.getId());
        response.setAuthorName(author.getAuthorName());
        response.setEmail(author.getEmail());

        return response;
    }
}

