//src/main/java/com/library/service/BorrowRecordService.java
package com.library.service;

import com.library.entity.Book;
import com.library.entity.BorrowRecord;
import com.library.entity.UserAccount;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRecordRepository;
import com.library.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowRecordService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional // việc lấy tất cả bản ghi mượn
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }

    public Optional<BorrowRecord> getBorrowRecordById(Integer id) {
        return borrowRecordRepository.findById(id);
    }

    @Transactional // có nhiều thao tác DB
    public BorrowRecord borrowBook(Integer userId, Integer bookId) {
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("UserAccount not found with id " + userId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + bookId));

        if (!book.getAvailable()) {
            throw new RuntimeException("Book is not available for borrowing.");
        }

        // Kiểm tra xem người dùng đã mượn cuốn sách này mà chưa trả chưa
        if (borrowRecordRepository.findByUserAndBookAndReturnedFalse(userAccount, book).isPresent()) {
            throw new RuntimeException("User has already borrowed this book and not returned it yet.");
        }

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setUser(userAccount);
        borrowRecord.setBook(book);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setDueDate(LocalDate.now().plusWeeks(2)); // Ví dụ: Hạn trả 2 tuần
        borrowRecord.setReturned(false);
        borrowRecord.setFineAmount(0.0);

        book.setAvailable(false); // Cập nhật trạng thái sách không còn khả dụng
        bookRepository.save(book); // Lưu thay đổi trạng thái sách

        return borrowRecordRepository.save(borrowRecord);
    }

    @Transactional
    public BorrowRecord returnBook(Integer recordId) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("BorrowRecord not found with id " + recordId));

        if (borrowRecord.getReturned()) {
            throw new RuntimeException("Book has already been returned for this record.");
        }

        borrowRecord.setReturned(true);
        borrowRecord.setFineAmount(calculateFine(borrowRecord.getDueDate())); // Tính tiền phạt
        borrowRecord.setReturnDate(LocalDate.now()); // Cập nhật ngày trả thực tế

        // Cập nhật trạng thái sách là khả dụng trở lại
        Book book = borrowRecord.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return borrowRecordRepository.save(borrowRecord);
    }

    private Double calculateFine(LocalDate dueDate) {
        if (LocalDate.now().isAfter(dueDate)) {
            long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
            return daysOverdue * 1000.0; // Ví dụ: 1000 VND mỗi ngày quá hạn
        }
        return 0.0;
    }

    @Transactional // Đảm bảo giao dịch cho việc lấy bản ghi mượn theo người dùng
    public List<BorrowRecord> getBorrowRecordsByUser(Integer userId) {
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("UserAccount not found with id " + userId));
        return borrowRecordRepository.findByUser(userAccount);
    }

    @Transactional // Đảm bảo giao dịch cho việc lấy bản ghi mượn theo sách
    public List<BorrowRecord> getBorrowRecordsByBook(Integer bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + bookId));
        return borrowRecordRepository.findByBook(book);
    }

    @Transactional // Đảm bảo giao dịch cho việc lấy sách mượn chưa trả của người dùng
    public List<BorrowRecord> getBorrowedBooksNotReturnedByUser(Integer userId) {
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("UserAccount not found with id " + userId));
        return borrowRecordRepository.findByUserAndReturnedFalse(userAccount);
    }

    @Transactional // Đảm bảo giao dịch cho việc lấy các bản ghi chưa trả
    public List<BorrowRecord> getUnreturnedBorrowRecords() {
        return borrowRecordRepository.findByReturned(false);
    }
}