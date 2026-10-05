package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.DTO.ReaderRequestDTO;
import com.example.LibraryManagement.DTO.ReaderResponseDTO;
import com.example.LibraryManagement.Entity.Reader;
import com.example.LibraryManagement.Service.ReaderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/readers")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController ( ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping
    public ResponseEntity<ReaderResponseDTO> createReader(@RequestBody @Valid ReaderRequestDTO req){
        ReaderResponseDTO response = readerService.createReader(req);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderResponseDTO> searchReader(@PathVariable Long id) {
        return ResponseEntity.ok(readerService.getReaderById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReaderResponseDTO>> searchReaders() {
        List<Reader> readers = readerService.getAllReaders();
        List<ReaderResponseDTO> response = new ArrayList<>();

        for(Reader reader : readers){
            response.add(ReaderService.mapToDTO(reader));
        }
        return new ResponseEntity<>(response, HttpStatus.OK); // Return the list with a 200 OK status

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
        readerService.deleteById(id);
        return ResponseEntity.notFound().build();

    }

}
