package com.library.controller;

import com.library.dto.BorrowRecordDTO;
import com.library.entity.BorrowRecord;
import com.library.service.BorrowRecordService; // Đảm bảo đã import BorrowRecordService
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/borrows") // hoac /api/borrow-records
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<BorrowRecordDTO>> getAllBorrowRecords() {
        List<BorrowRecord> records = borrowRecordService.getAllBorrowRecords();
        List<BorrowRecordDTO> recordDTOs = records.stream()
                .map(record -> modelMapper.map(record, BorrowRecordDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(recordDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowRecordDTO> getBorrowRecordById(@PathVariable Integer id) {
        // Xử lý Optional<BorrowRecord>
        BorrowRecord borrowRecord = borrowRecordService.getBorrowRecordById(id)
                .orElseThrow(() -> new RuntimeException("BorrowRecord not found with id " + id)); // Hoặc Custom Exception
        BorrowRecordDTO borrowRecordDTO = modelMapper.map(borrowRecord, BorrowRecordDTO.class);
        return ResponseEntity.ok(borrowRecordDTO);
    }

    // Endpoint để mượn sách
    @PostMapping("/borrow")
    public ResponseEntity<BorrowRecordDTO> borrowBook(@RequestParam Integer userId, @RequestParam Integer bookId) {
        BorrowRecord newRecord = borrowRecordService.borrowBook(userId, bookId);
        BorrowRecordDTO newRecordDTO = modelMapper.map(newRecord, BorrowRecordDTO.class);
        return new ResponseEntity<>(newRecordDTO, HttpStatus.CREATED);
    }

    // Endpoint để trả sách
    @PutMapping("/return/{recordId}")
    public ResponseEntity<BorrowRecordDTO> returnBook(@PathVariable Integer recordId) {
        BorrowRecord updatedRecord = borrowRecordService.returnBook(recordId);
        BorrowRecordDTO updatedRecordDTO = modelMapper.map(updatedRecord, BorrowRecordDTO.class);
        return ResponseEntity.ok(updatedRecordDTO);
    }

    @GetMapping("/user/{userId}") // Lấy tất cả bản ghi mượn của một người dùng
    public ResponseEntity<List<BorrowRecordDTO>> getBorrowRecordsByUserId(@PathVariable Integer userId) {
        List<BorrowRecord> records = borrowRecordService.getBorrowRecordsByUser(userId); // Đã sửa tên phương thức
        List<BorrowRecordDTO> recordDTOs = records.stream()
                .map(record -> modelMapper.map(record, BorrowRecordDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(recordDTOs);
    }

    @GetMapping("/book/{bookId}") // Lấy tất cả bản ghi mượn của một cuốn sách
    public ResponseEntity<List<BorrowRecordDTO>> getBorrowRecordsByBookId(@PathVariable Integer bookId) {
        List<BorrowRecord> records = borrowRecordService.getBorrowRecordsByBook(bookId); // Đã sửa tên phương thức
        List<BorrowRecordDTO> recordDTOs = records.stream()
                .map(record -> modelMapper.map(record, BorrowRecordDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(recordDTOs);
    }
    @GetMapping("/user/{userId}/current")
    public ResponseEntity<List<BorrowRecordDTO>> getBorrowedBooksNotReturnedByUser(@PathVariable Integer userId) {
        List<BorrowRecord> records = borrowRecordService.getBorrowedBooksNotReturnedByUser(userId);
        List<BorrowRecordDTO> recordDTOs = records.stream()
                .map(record -> modelMapper.map(record, BorrowRecordDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(recordDTOs);
    }
}