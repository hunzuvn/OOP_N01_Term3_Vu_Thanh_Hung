import org.junit.Test;
import java.time.LocalDate;

public class BorrowListTest {
    public static void main(String[] args) {
        BorrowList borrowList = new BorrowList();

        // Tạo record mẫu
        BorrowRecordEntity record = new BorrowRecordEntity();
        record.setId(1L);
        record.setBook(new BookEntity(1L, "Clean Code", "Robert C. Martin", "Tech", 3));
        record.setUser(new UserEntity(1L, "Nguyen Van A"));
        record.setBorrowDate(LocalDate.now());
        record.setReturned(false);

        // Thêm record
        borrowList.addRecord(record);

        // In danh sách
        System.out.println("Danh sách sau khi thêm:");
        System.out.println(borrowList.getAllRecords());

        // Cập nhật
        record.setReturned(true);
        borrowList.updateRecord(1L, record);
        System.out.println("Sau khi cập nhật:");
        System.out.println(borrowList.getRecordById(1L));

        // Xoá
        borrowList.removeRecordById(1L);
        System.out.println("Sau khi xoá:");
        System.out.println(borrowList.getAllRecords());
    }
}