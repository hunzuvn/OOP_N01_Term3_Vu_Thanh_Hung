
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryList {

    private final List<CategoryEntity> categories = new ArrayList<>();

    public List<CategoryEntity> getAllCategories() {
        return categories;
    }

    public void addCategory(CategoryEntity category) {
        categories.add(category);
    }

    public boolean removeCategoryById(Long id) {
        return categories.removeIf(category -> category.getId().equals(id));
    }

    public boolean updateCategory(Long id, CategoryEntity updatedCategory) {
        Optional<CategoryEntity> optionalCategory = categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst();

        if (optionalCategory.isPresent()) {
            CategoryEntity category = optionalCategory.get();
            category.setName(updatedCategory.getName());
            category.setDescription(updatedCategory.getDescription());
            return true;
        } else {
            return false;
        }
    }

    public CategoryEntity getCategoryById(Long id) {
        return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
