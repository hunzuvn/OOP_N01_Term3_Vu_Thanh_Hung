public class CategoryListTest {
    public static void main(String[] args) {
        CategoryList categoryList = new CategoryList();

        // Tạo thể loại mẫu
        CategoryEntity category = new CategoryEntity(1L, "Science Fiction");

        // Thêm thể loại
        categoryList.addCategory(category);

        // Hiển thị danh sách
        System.out.println("Danh sách thể loại sau khi thêm:");
        System.out.println(categoryList.getAllCategories());

        // Cập nhật thể loại
        CategoryEntity updatedCategory = new CategoryEntity(1L, "Sci-Fi Classic");
        categoryList.updateCategory(1L, updatedCategory);
        System.out.println("Sau khi cập nhật:");
        System.out.println(categoryList.getCategoryById(1L));

        // Xoá thể loại
        categoryList.removeCategoryById(1L);
        System.out.println("Sau khi xoá:");
        System.out.println(categoryList.getAllCategories());
    }
}
