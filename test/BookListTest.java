import org.junit.Test;
import java.time.LocalDate;

public class BookListTest {
    public static void main(String[] args) {
        BookList bookList = new BookList();

        // Tạo danh mục sách mẫu
        CategoryEntity category = new CategoryEntity(1L, "Khoa học");

        // Tạo sách mẫu
        BookEntity book = new BookEntity(1L, "Vũ trụ kỳ bí", "Stephen Hawking", "1234567890", LocalDate.of(2020, 5, 20), 10, 10, category);

        // Thêm sách
        bookList.addBook(book);

        // Hiển thị danh sách sách
        System.out.println("Danh sách sách sau khi thêm:");
        System.out.println(bookList.getAllBooks());

        // Cập nhật sách
        BookEntity updatedBook = new BookEntity(1L, "Vũ trụ kỳ bí - Bản cập nhật", "Stephen Hawking", "1234567890", LocalDate.of(2020, 5, 20), 15, 15, category);
        bookList.updateBook(1L, updatedBook);
        System.out.println("Sau khi cập nhật:");
        System.out.println(bookList.getBookById(1L));

        // Xoá sách
        bookList.removeBookById(1L);
        System.out.println("Sau khi xoá:");
        System.out.println(bookList.getAllBooks());
    }
}