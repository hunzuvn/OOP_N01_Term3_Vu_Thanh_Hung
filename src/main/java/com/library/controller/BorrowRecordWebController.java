package com.library.controller;

import com.library.entity.BorrowRecord;
import com.library.service.BorrowRecordService;
import com.library.service.UserAccountService; // <-- Thêm import
import com.library.service.BookService;       // <-- Thêm import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // <-- Thêm import
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;  // <-- Thêm import
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // <-- Thêm import

import java.util.List;

@Controller
@RequestMapping("/web/borrows")
public class BorrowRecordWebController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @Autowired // <-- THÊM DÒNG NÀY
    private UserAccountService userAccountService;

    @Autowired // <-- THÊM DÒNG NÀY
    private BookService bookService;

    @GetMapping // Khi người dùng truy cập /web/borrows
    public String listBorrowRecords(Model model) {
        List<BorrowRecord> borrowRecords = borrowRecordService.getAllBorrowRecords();
        model.addAttribute("borrowRecords", borrowRecords);
        return "borrowRecords";
    }

    // --- Endpoint để hiển thị form mượn sách --- (Di chuyển từ WebController)
    @GetMapping("/borrow-form") // Đường dẫn sẽ là /web/borrows/borrow-form
    public String showBorrowForm(Model model) {
        model.addAttribute("users", userAccountService.getAllUserAccounts());
        model.addAttribute("books", bookService.getAvailableBooks());
        return "borrowForm";
    }

    // --- Endpoint để xử lý việc mượn sách khi form được gửi đi --- (Di chuyển từ WebController)
    @PostMapping("/borrow") // Đường dẫn sẽ là /web/borrows/borrow
    public String borrowBook(@RequestParam("userId") Integer userId,
                             @RequestParam("bookId") Integer bookId,
                             RedirectAttributes redirectAttributes) {
        try {
            borrowRecordService.borrowBook(userId, bookId);
            redirectAttributes.addFlashAttribute("successMessage", "Mượn sách thành công!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi mượn sách: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi không xác định khi mượn sách.");
            e.printStackTrace();
        }
        return "redirect:/web/borrows"; // Chuyển hướng về trang danh sách bản ghi mượn
    }

    // --- Endpoint để hiển thị form trả sách --- (Di chuyển từ WebController)
    @GetMapping("/return-form") // Đường dẫn sẽ là /web/borrows/return-form
    public String showReturnForm(Model model) {
        model.addAttribute("unreturnedRecords", borrowRecordService.getUnreturnedBorrowRecords());
        return "returnForm";
    }

    // --- Endpoint để xử lý việc trả sách khi form được gửi đi --- (Di chuyển từ WebController)
    @PostMapping("/return") // Đường dẫn sẽ là /web/borrows/return
    public String returnBook(@RequestParam("recordId") Integer recordId,
                             RedirectAttributes redirectAttributes) {
        try {
            borrowRecordService.returnBook(recordId);
            redirectAttributes.addFlashAttribute("successMessage", "Trả sách thành công!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi trả sách: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi không xác định khi trả sách.");
            e.printStackTrace();
        }
        return "redirect:/web/borrows"; // Chuyển hướng về trang danh sách bản ghi mượn
    }
}