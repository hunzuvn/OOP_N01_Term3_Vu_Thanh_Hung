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
}
