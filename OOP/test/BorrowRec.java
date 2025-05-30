package com.example.librarymanagement.test;

import com.example.librarymanagement.entity.BookEntity;
import com.example.librarymanagement.entity.BorrowRecordEntity;
import com.example.librarymanagement.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BorrowRecordEntityTest {

    @Test
    public void testBorrowRecordEntitySettersAndGetters() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("Alice");

        BookEntity book = new BookEntity();
        book.setId(100L);
        book.setTitle("1984");

        LocalDate borrowDate = LocalDate.of(2023, 1, 1);
        LocalDate returnDate = LocalDate.of(2023, 1, 10);

        BorrowRecordEntity record = new BorrowRecordEntity();
        record.setId(500L);
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(borrowDate);
        record.setReturnDate(returnDate);
        record.setReturned(true);

        assertEquals(500L, record.getId());
        assertEquals("Alice", record.getUser().getName());
        assertEquals("1984", record.getBook().getTitle());
        assertEquals(borrowDate, record.getBorrowDate());
        assertEquals(returnDate, record.getReturnDate());
        assertTrue(record.isReturned());
    }
}
