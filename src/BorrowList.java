
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BorrowList {

    private final List<BorrowRecordEntity> records = new ArrayList<>();

    public List<BorrowRecordEntity> getAllRecords() {
        return records;
    }

    public void addRecord(BorrowRecordEntity record) {
        records.add(record);
    }

    public boolean removeRecordById(Long id) {
        return records.removeIf(record -> record.getId().equals(id));
    }

    public boolean updateRecord(Long id, BorrowRecordEntity updatedRecord) {
        Optional<BorrowRecordEntity> optionalRecord = records.stream()
                .filter(record -> record.getId().equals(id))
                .findFirst();

        if (optionalRecord.isPresent()) {
            BorrowRecordEntity record = optionalRecord.get();
            record.setUser(updatedRecord.getUser());
            record.setBook(updatedRecord.getBook());
            record.setBorrowDate(updatedRecord.getBorrowDate());
            record.setReturnDate(updatedRecord.getReturnDate());
            record.setReturned(updatedRecord.isReturned());
            return true;
        } else {
            return false;
        }
    }

    public BorrowRecordEntity getRecordById(Long id) {
        return records.stream()
                .filter(record -> record.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
