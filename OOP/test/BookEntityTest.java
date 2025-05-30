package com.example.librarymanagement.test;

import com.example.librarymanagement.entity.BookEntity;
import com.example.librarymanagement.entity.CategoryEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookEntityTest {

    @Test
    public void testBookEntitySettersAndGetters() {
        BookEntity book = new BookEntity();
        book.setId(1L);
        book.setTitle("Sample Title");
        book.setAuthor("Author Name");
        book.setIsbn("123456789");
        book.setPublishDate(LocalDate.of(2020, 1, 1));
        book.setTotalCopies(10);
        book.setAvailableCopies(7);

        CategoryEntity category = new CategoryEntity();
        category.setId(1L);
        category.setName("Fiction");
        book.setCategory(category);

        assertEquals(1L, book.getId());
        assertEquals("Sample Title", book.getTitle());
        assertEquals("Author Name", book.getAuthor());
        assertEquals("123456789", book.getIsbn());
        assertEquals(LocalDate.of(2020, 1, 1), book.getPublishDate());
        assertEquals(10, book.getTotalCopies());
        assertEquals(7, book.getAvailableCopies());
        assertEquals("Fiction", book.getCategory().getName());
    }
}
