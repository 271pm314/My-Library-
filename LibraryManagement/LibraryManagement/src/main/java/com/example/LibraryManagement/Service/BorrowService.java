package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.DTO.BorrowResponseDTO;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.BookStatus;
import com.example.LibraryManagement.Entity.BorrowRecord;
import com.example.LibraryManagement.Entity.Reader;
import com.example.LibraryManagement.ExceptionHandler.ResourceNotFoundException;
import com.example.LibraryManagement.Repository.BookRepo;
import com.example.LibraryManagement.Repository.BorrowRepo;
import com.example.LibraryManagement.Repository.ReaderRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@AllArgsConstructor
public class BorrowService {

    private BorrowRepo borrowRepo;
    private BookRepo bookRepo;
    private ReaderRepo readerRepo;

    private final double DAILY_FINE = 1.0;
    private final int MAX_DUE_DAYS = 14;
    private final int MAX_BOOKS_ALLOWED = 3;


    @Transactional
    @CacheEvict(value = "books", key = "#bookId")
    public BorrowResponseDTO Borrow(long readerId, long bookId) {

        log.info("Processing book loan request. BookId: {}, ReaderId: {}", bookId, readerId);

        Reader reader = readerRepo.findById(readerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reader not found"));
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException(book.getBookName() + " book is not available");
        }

        int activeIssueBooks = borrowRepo.countByReaderIdAndStatus(readerId, BookStatus.BORROWED);

        if (activeIssueBooks > MAX_BOOKS_ALLOWED) {
            throw new IllegalStateException("You have already issued maximum allowed books " + MAX_BOOKS_ALLOWED);
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setBook(book);
        borrowRecord.setReader(reader);
        borrowRecord.setStatus(BookStatus.BORROWED);
        borrowRecord.setReturnDate(LocalDate.now().plusDays(MAX_DUE_DAYS));
        borrowRepo.save(borrowRecord);

        log.info("Book successfully loaned. RecordId: {}, DueDate: {}",
                borrowRecord.getId(), borrowRecord.getDueDate());

        return mapToDTO(borrowRecord);

    }

    @Transactional
    @CacheEvict(value = "books", key = "#bookId")
    public BorrowResponseDTO returnBook(Long bookId, Long readerId) {

        log.info("Processing book return request. BookId: {}, ReaderId: {}", bookId, readerId);


        BorrowRecord record = borrowRepo.findByBook_IdAndReader_IdAndStatus(bookId, readerId, BookStatus.BORROWED);

        //    .orElseThrow(() -> new ResourceNotFoundException("No active borrow record found for this book and reader"));

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepo.save(book);

        record.setReturnDate(LocalDate.now());
        record.setStatus(BookStatus.RETURNED);


        // Calculate late fees if applicable
        if (record.getReturnDate().isAfter(record.getDueDate())) {
            long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(record.getDueDate(), record.getReturnDate());
            record.setFine(overdueDays * DAILY_FINE);

        }

        log.info("Book successfully returned. RecordId: {}, ReturnDate: {}",
                record.getId(), record.getReturnDate());

        return mapToDTO(record);

    }




    public BorrowResponseDTO mapToDTO (BorrowRecord borrow) {
        BorrowResponseDTO response = new BorrowResponseDTO();

        response.setBorrowId(borrow.getId());
        response.setBorrowDate(borrow.getBorrowDate());
        response.setDueDate(borrow.getDueDate());
        response.setReturnDate(borrow.getReturnDate());
        response.setBook(borrow.getBook());
        response.setStatus(borrow.getStatus());
        response.setFine(borrow.getFine());
        response.setNumberOfAvailableCopies(borrow.getBook().getAvailableCopies());
        response.setISBN(borrow.getBook().getIsbn());
        response.setAuthorId(borrow.getBook().getAuthor().getId());
        response.setReaderName(borrow.getReader().getReaderName());
        response.setPublishedYear(borrow.getBook().getPublishedYear());
        response.setBookName(borrow.getBook().getBookName());
        response.setBookId(borrow.getBook().getId());

        return response;
    }

}
