package com.example.librarymanagement.test;

import com.example.librarymanagement.entity.BookEntity;
import com.example.librarymanagement.service.BookList;

public class BookListTest {
    public static void main(String[] args) {
        BookList bookList = new BookList();

        // Tạo sách mẫu
        BookEntity book = new BookEntity(1L, "Effective Java", "Joshua Bloch", "Programming", 5);

        // Thêm sách
        bookList.addBook(book);

        // Hiển thị sách
        System.out.println("Danh sách sách sau khi thêm:");
        System.out.println(bookList.getAllBooks());

        // Cập nhật sách
        BookEntity updatedBook = new BookEntity(1L, "Effective Java - Updated", "Joshua Bloch", "Programming", 10);
        bookList.updateBook(1L, updatedBook);
        System.out.println("Sau khi cập nhật:");
        System.out.println(bookList.getBookById(1L));

        // Xoá sách
        bookList.removeBookById(1L);
        System.out.println("Sau khi xoá:");
        System.out.println(bookList.getAllBooks());
    }
}
