
import java.time.LocalDate;
import java.util.List;

public class BookEntity {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private LocalDate publishDate;
    private int totalCopies;
    private int availableCopies;
    private CategoryEntity category;
    private List<BorrowRecordEntity> borrowRecords;

    public BookEntity() {}

    public BookEntity(String title, String author, String isbn, LocalDate publishDate, int totalCopies, int availableCopies, CategoryEntity category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<BorrowRecordEntity> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(List<BorrowRecordEntity> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }
}