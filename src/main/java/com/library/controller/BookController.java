//src/main/java/com/library/controller/BookController.java
package com.library.controller;

import com.library.dto.BookDTO;
import com.library.entity.Book;
import com.library.service.BookService; // Đảm bảo đã import BookService
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDTO> bookDTOs = books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        // Xử lý Optional<Book>
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id)); // Hoặc một Custom Exception
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return ResponseEntity.ok(bookDTO);
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO) {
        Book bookRequest = modelMapper.map(bookDTO, Book.class);
        Book newBook = bookService.addBook(bookRequest);
        BookDTO newBookDTO = modelMapper.map(newBook, BookDTO.class);
        return new ResponseEntity<>(newBookDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer id, @Valid @RequestBody BookDTO bookDTO) {
        Book bookRequest = modelMapper.map(bookDTO, Book.class);
        Book updatedBook = bookService.updateBook(id, bookRequest);
        BookDTO updatedBookDTO = modelMapper.map(updatedBook, BookDTO.class);
        return ResponseEntity.ok(updatedBookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String isbn) {

        List<Book> books;
        if (title != null && !title.isEmpty()) {
            books = bookService.searchBooksByTitle(title);
        } else if (author != null && !author.isEmpty()) {
            books = bookService.searchBooksByAuthor(author);
        } else if (category != null && !category.isEmpty()) {
            books = bookService.getBooksByCategory(category);
        } else if (isbn != null && !isbn.isEmpty()) {
            Book book = bookService.getBookByIsbn(isbn);
            books = (book != null) ? List.of(book) : List.of();
        }
        else {
            books = bookService.getAllBooks();
        }

        List<BookDTO> bookDTOs = books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }
}