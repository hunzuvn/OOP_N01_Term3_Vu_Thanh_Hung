package com.example.librarymanagement.service;

import com.example.librarymanagement.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserList {

    private final List<UserEntity> users = new ArrayList<>();

    public List<UserEntity> getAllUsers() {
        return users;
    }

    public void addUser(UserEntity user) {
        users.add(user);
    }

    public boolean removeUserById(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }

    public boolean updateUser(Long id, UserEntity updatedUser) {
        Optional<UserEntity> optionalUser = users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setBorrowRecords(updatedUser.getBorrowRecords());
            return true;
        } else {
            return false;
        }
    }

    public UserEntity getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
