package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository <Author, Long> {
boolean existsByEmail(String email);
}
