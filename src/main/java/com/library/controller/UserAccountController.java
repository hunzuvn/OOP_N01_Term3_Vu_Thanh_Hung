package com.library.controller;

import com.library.dto.UserDTO;
import com.library.entity.UserAccount;
import com.library.service.UserAccountService; // <-- Dòng import đã sửa
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users") // Bạn có thể giữ /api/users hoặc đổi thành /api/user-accounts
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService; // <-- Đổi tên biến ở đây

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserAccount> userAccounts = userAccountService.getAllUserAccounts(); // <-- Gọi đúng phương thức của Service
        List<UserDTO> userDTOs = userAccounts.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        // Cần xử lý Optional trả về từ Service
        UserAccount userAccount = userAccountService.getUserAccountById(id)
                .orElseThrow(() -> new RuntimeException("UserAccount not found with id " + id)); // Hoặc một custom exception
        UserDTO userDTO = modelMapper.map(userAccount, UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        UserAccount userRequest = modelMapper.map(userDTO, UserAccount.class);
        UserAccount newUser = userAccountService.createUserAccount(userRequest); // <-- Gọi đúng phương thức của Service
        UserDTO newUserDTO = modelMapper.map(newUser, UserDTO.class);
        return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO) {
        UserAccount userRequest = modelMapper.map(userDTO, UserAccount.class);
        UserAccount updatedUser = userAccountService.updateUserAccount(id, userRequest); // <-- Gọi đúng phương thức của Service
        UserDTO updatedUserDTO = modelMapper.map(updatedUser, UserDTO.class);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userAccountService.deleteUserAccount(id); // <-- Gọi đúng phương thức của Service
        return ResponseEntity.noContent().build();
    }
}