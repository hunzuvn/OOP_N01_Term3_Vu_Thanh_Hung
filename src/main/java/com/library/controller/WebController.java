package com.library.controller;

import com.library.entity.UserAccount;
import com.library.entity.Book;
// import com.library.entity.BorrowRecord; // Vẫn không cần nếu listBorrowRecords đã được chuyển

import com.library.service.UserAccountService;
import com.library.service.BookService;
import com.library.service.BorrowRecordService; // <-- ĐẢM BẢO DÒNG NÀY CÓ!

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private BookService bookService;

    @Autowired // <-- ĐẢM BẢO DÒNG NÀY KHÔNG BỊ BÌNH LUẬN HOẶC XÓA!
    private BorrowRecordService borrowRecordService;

    // --- Trang chủ hoặc trang chào mừng ---
    @GetMapping("/")
    public String home() {
        return "index"; // Trả về file index.html
    }

    // --- Endpoint để hiển thị danh sách người dùng ---
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserAccount> users = userAccountService.getAllUserAccounts();
        model.addAttribute("users", users);
        return "users"; // Trả về tên của file template: users.html
    }

    // --- Endpoint để hiển thị danh sách sách ---
    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books"; // Trả về books.html
    }

    // --- Endpoint cho trang danh sách bản ghi mượn ---
    // PHƯƠNG THỨC NÀY ĐÃ ĐƯỢC XÓA HOẶC BÌNH LUẬN ĐỂ TRÁNH TRÙNG LẶP
    // @GetMapping("/borrows")
    // public String listBorrowRecords(Model model) {
    //     // ...
    //     return "borrowRecords";
    // }

    // --- Endpoint để hiển thị form mượn sách ---
    @GetMapping("/borrow-form")
    public String showBorrowForm(Model model) {
        model.addAttribute("users", userAccountService.getAllUserAccounts());
        model.addAttribute("books", bookService.getAvailableBooks()); // Lấy sách có sẵn
        return "borrowForm"; // Sẽ render borrowForm.html
    }

    // --- Endpoint để xử lý việc mượn sách khi form được gửi đi ---
    @PostMapping("/borrow")
    public String borrowBook(@RequestParam("userId") Integer userId,
                             @RequestParam("bookId") Integer bookId,
                             RedirectAttributes redirectAttributes) {
        try {
            borrowRecordService.borrowBook(userId, bookId); // <-- Cần borrowRecordService ở đây
            redirectAttributes.addFlashAttribute("successMessage", "Mượn sách thành công!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi mượn sách: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi không xác định khi mượn sách.");
            e.printStackTrace(); // In stack trace ra console để debug
        }
        return "redirect:/web/borrows"; // Chuyển hướng về trang danh sách bản ghi mượn (nay do BorrowRecordWebController xử lý)
    }

    // --- Endpoint để hiển thị form trả sách ---
    @GetMapping("/return-form")
    public String showReturnForm(Model model) {
        model.addAttribute("unreturnedRecords", borrowRecordService.getUnreturnedBorrowRecords()); // <-- Cần borrowRecordService ở đây
        return "returnForm"; // Sẽ render returnForm.html
    }

    // --- Endpoint để xử lý việc trả sách khi form được gửi đi ---
    @PostMapping("/return")
    public String returnBook(@RequestParam("recordId") Integer recordId,
                             RedirectAttributes redirectAttributes) {
        try {
            borrowRecordService.returnBook(recordId); // <-- Cần borrowRecordService ở đây
            redirectAttributes.addFlashAttribute("successMessage", "Trả sách thành công!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi trả sách: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi không xác định khi trả sách.");
            e.printStackTrace(); // In stack trace ra console để debug
        }
        return "redirect:/web/borrows"; // Chuyển hướng về trang danh sách bản ghi mượn (nay do BorrowRecordWebController xử lý)
    }
}