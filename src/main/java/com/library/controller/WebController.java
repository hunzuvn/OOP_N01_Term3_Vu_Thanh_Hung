package com.library.controller;

import com.library.entity.Book;
import com.library.entity.BorrowRecord;

import com.library.service.BookService;
import com.library.service.BorrowRecordService;

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
    private BookService bookService;

    @Autowired
    private BorrowRecordService borrowRecordService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/borrow-form")
    public String showBorrowForm(Model model) {
        return "borrowForm";
    }

    @PostMapping("/borrow")
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
        return "redirect:/web/borrows";
    }
    @GetMapping("/return-form")
    public String showReturnForm(Model model) {
        model.addAttribute("unreturnedRecords", borrowRecordService.getUnreturnedBorrowRecords());
        return "returnForm";
    }

    @PostMapping("/return")
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
        return "redirect:/web/borrows";
    }
}