package com.library.controller;

import com.library.entity.UserAccount;
import com.library.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/web/users")
public class UserAccountWebController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping
    public String listUsers(Model model) {
        List<UserAccount> users = userAccountService.getAllUserAccounts();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add-form")
    public String showAddUserForm(Model model) {
        return "userAddForm";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name,
                          @RequestParam String email,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String role,
                          @RequestParam(required = false) String address,
                          RedirectAttributes redirectAttributes) {
        try {
            UserAccount newUser = new UserAccount();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setRole(UserAccount.Role.valueOf(role.toUpperCase()));
            newUser.setAddress(address);

            userAccountService.createUserAccount(newUser);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm người dùng thành công!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi vai trò: " + e.getMessage());
            return "redirect:/web/users/add-form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm người dùng: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/web/users/add-form";
        }
        return "redirect:/web/users";
    }
}