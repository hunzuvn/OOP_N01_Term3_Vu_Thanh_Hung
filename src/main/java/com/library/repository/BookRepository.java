//src/main/java/com/library/repository/BookRepository.java
package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // Tìm sách theo tiêu đề (title) hoặc tác giả (author)
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByCategory(String category);
    Book findByIsbn(String isbn); // Tìm sách theo ISBN, duy nhất

    // Phương thức đã được thêm vào để tìm sách theo trạng thái 'available'
    List<Book> findByAvailable(boolean available);
}