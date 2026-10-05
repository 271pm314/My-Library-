package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepo extends JpaRepository<Reader, Long> {
    boolean existsByEmail(String email);
}
