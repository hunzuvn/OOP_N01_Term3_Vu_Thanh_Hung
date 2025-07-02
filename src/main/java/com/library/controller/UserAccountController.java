//src/main/java/com/library/controller/UserAccountController.java
package com.library.controller;

import com.library.dto.UserDTO;
import com.library.entity.UserAccount;
import com.library.service.UserAccountService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserAccount> userAccounts = userAccountService.getAllUserAccounts();
        List<UserDTO> userDTOs = userAccounts.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        UserAccount userAccount = userAccountService.getUserAccountById(id)
                .orElseThrow(() -> new RuntimeException("UserAccount not found with id " + id));
        UserDTO userDTO = modelMapper.map(userAccount, UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        UserAccount userRequest = modelMapper.map(userDTO, UserAccount.class);
        UserAccount newUser = userAccountService.createUserAccount(userRequest);
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
        userAccountService.deleteUserAccount(id);
        return ResponseEntity.noContent().build();
    }
}