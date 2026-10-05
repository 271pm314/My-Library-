package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.DTO.ReaderRequestDTO;
import com.example.LibraryManagement.DTO.ReaderResponseDTO;
import com.example.LibraryManagement.Entity.Reader;
import com.example.LibraryManagement.Repository.BookRepo;
import com.example.LibraryManagement.Repository.ReaderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReaderService {

    private final BookRepo bookRepo;
    private final ReaderRepo readerRepo;

    public ReaderResponseDTO createReader(ReaderRequestDTO dto) {

        if(readerRepo.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Reader with Email " + dto.getEmail() + " already exists");
        }
        Reader reader = new Reader();
        reader.setReaderName(dto.getReaderName());
        reader.setEmail(dto.getEmail());
        reader.setAge(dto.getAge());
        reader.setPhone(dto.getPhone());


        Reader saved = readerRepo.save(reader);
        return mapToDTO(saved);
    }

    public ReaderResponseDTO getReaderById(Long id) {
         Reader reader = readerRepo.getById(id);
        return mapToDTO(reader);
    }

    public List<Reader> getAllReaders() {
        return readerRepo.findAll();
    }

    public void deleteById(Long id) {
        readerRepo.deleteById(id);
    }


    public static ReaderResponseDTO mapToDTO(Reader reader){
        ReaderResponseDTO response = new ReaderResponseDTO();
        response.setReaderId(reader.getId());
        response.setReaderName(reader.getReaderName());
        response.setEmail(reader.getEmail());
        response.setAge(reader.getAge());
        response.setPhone(reader.getPhone());
        response.setBooks(reader.getIssuedBooks());

        return response;
    }
}
