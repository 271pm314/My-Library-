package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.DTO.AuthorRequestDTO;
import com.example.LibraryManagement.DTO.AuthorResponseDTO;
import com.example.LibraryManagement.Entity.Author;
import com.example.LibraryManagement.Repository.AuthorRepo;
import com.example.LibraryManagement.Repository.BookRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AuthorService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto) {

        log.info("Processing Author Registration Request");

        if(authorRepo.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Author with Email " + dto.getEmail() + " already exists");
        }
        Author author= new Author();
        author.setAuthorName(dto.getAuthorName());
        author.setEmail(dto.getEmail());


        Author saved = authorRepo.save(author);

        log.info("Author Created With Id {} ", author.getId());

        return mapToDTO(saved);
    }

    @Cacheable(value = "authors", key = "authorId")
    public AuthorResponseDTO getAuthorById(Long id) {
        Author author = authorRepo.getById(id);
        return mapToDTO(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    @CacheEvict(value = "authors", key = "authorId")
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

