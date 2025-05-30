package com.example.librarymanagement.test;

import com.example.librarymanagement.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {

    @Test
    public void testUserEntitySettersAndGetters() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPhone("123456789");

        assertEquals(1L, upackage com.example.librarymanagement.test;

import com.example.librarymanagement.entity.UserEntity;
import com.example.librarymanagement.service.UserList;

public class UserListTest {
    public static void main(String[] args) {
        UserList userList = new UserList();

        // Tạo user mẫu
        UserEntity user = new UserEntity(1L, "Le Van B");

        // Thêm user
        userList.addUser(user);

        // Hiển thị danh sách user
        System.out.println("Danh sách người dùng sau khi thêm:");
        System.out.println(userList.getAllUsers());

        // Cập nhật user
        UserEntity updatedUser = new UserEntity(1L, "Le Van B - Updated");
        userList.updateUser(1L, updatedUser);
        System.out.println("Sau khi cập nhật:");
        System.out.println(userList.getUserById(1L));

        // Xoá user
        userList.removeUserById(1L);
        System.out.println("Sau khi xoá:");
        System.out.println(userList.getAllUsers());
    }
}ser.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("123456789", user.getPhone());
    }
}
