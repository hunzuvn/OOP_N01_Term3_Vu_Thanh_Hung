package com.example.librarymanagement.entity;

import java.time.LocalDate;

public class BorrowRecordEntity {

    private Long id;
    private UserEntity user;
    private BookEntity book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;

    public BorrowRecordEntity() {}

    public BorrowRecordEntity(UserEntity user, BookEntity book, LocalDate borrowDate, LocalDate returnDate, boolean returned) {
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.returned = returned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
