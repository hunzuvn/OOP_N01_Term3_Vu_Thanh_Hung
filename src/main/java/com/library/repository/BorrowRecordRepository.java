package com.library.repository;

import com.library.entity.BorrowRecord;
import com.library.entity.Book;
import com.library.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {
    // Tìm các bản ghi mượn sách của một người dùng cụ thể
    List<BorrowRecord> findByUser(UserAccount user);

    // Tìm các bản ghi mượn sách của một cuốn sách cụ thể
    List<BorrowRecord> findByBook(Book book);

    // Tìm các bản ghi mượn sách chưa trả của một người dùng
    List<BorrowRecord> findByUserAndReturnedFalse(UserAccount user);

    // Tìm một bản ghi mượn sách cụ thể chưa trả của một cuốn sách và người dùng
    Optional<BorrowRecord> findByUserAndBookAndReturnedFalse(UserAccount user, Book book);

    // <<<<< THÊM DÒNG NÀY VÀO ĐÂY >>>>>
    // Phương thức để tìm tất cả các bản ghi mượn dựa trên trạng thái trả
    List<BorrowRecord> findByReturned(boolean returned);
}