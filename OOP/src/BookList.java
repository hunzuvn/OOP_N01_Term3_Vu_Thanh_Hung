package com.example.librarymanagement.service;

import com.example.librarymanagement.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookList {

    private final List<BookEntity> books = new ArrayList<>();

    public List<BookEntity> getAllBooks() {
        return books;
    }

    public void addBook(BookEntity book) {
        books.add(book);
    }

    public boolean removeBookById(Long id) {
        return books.removeIf(book -> book.getId().equals(id));
    }

    public boolean updateBook(Long id, BookEntity updatedBook) {
        Optional<BookEntity> optionalBook = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();

        if (optionalBook.isPresent()) {
            BookEntity book = optionalBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setIsbn(updatedBook.getIsbn());
            book.setPublishDate(updatedBook.getPublishDate());
            book.setTotalCopies(updatedBook.getTotalCopies());
            book.setAvailableCopies(updatedBook.getAvailableCopies());
            book.setCategory(updatedBook.getCategory());
            return true;
        } else {
            return false;
        }
    }

    public BookEntity getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
