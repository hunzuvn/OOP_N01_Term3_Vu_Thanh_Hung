package com.library.service;

import com.library.entity.UserAccount;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    public Optional<UserAccount> getUserAccountById(Integer id) {
        return userAccountRepository.findById(id);
    }

    public UserAccount createUserAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public UserAccount updateUserAccount(Integer id, UserAccount userAccountDetails) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserAccount not found with id " + id));

        userAccount.setName(userAccountDetails.getName());
        userAccount.setEmail(userAccountDetails.getEmail());
        userAccount.setUsername(userAccountDetails.getUsername());
        userAccount.setPassword(userAccountDetails.getPassword());
        userAccount.setRole(userAccountDetails.getRole());
        userAccount.setAddress(userAccountDetails.getAddress());

        return userAccountRepository.save(userAccount);
    }

    public void deleteUserAccount(Integer id) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserAccount not found with id " + id));
        userAccountRepository.delete(userAccount);
    }

    public UserAccount findByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }

    public UserAccount findByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }
}