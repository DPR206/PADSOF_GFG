package order;

import product.Category;
import product.StoreProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class CategoryDiscount extends Discount {
    /** The list of categories affected by this discount */
    private List<Category> categories = new ArrayList<>();
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public CategoryDiscount(String id, DiscountType type, DiscountCoverage coverage, LocalDateTime startDate,
                            LocalDateTime endDate) {
        super(id, type, coverage, startDate, endDate);
    }
    public CategoryDiscount(DiscountType type, DiscountCoverage coverage, LocalDateTime startDate,
                            LocalDateTime endDate) {
        super(type, coverage, startDate, endDate);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    /**
     * It adds categories to the discount
     * @param categories the desired categories
     */
    public void addCategories(Category... categories) {
        for (Category category : categories) {
            if (conflictingDisc(category.getProducts())) {
                throw new IllegalArgumentException("Conflicting Discount");
            }
            this.categories.add(category);
            for (StoreProduct product : category.getProducts()) {
                product.setDiscount(this);
            }
        }
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    List<StoreProduct> getProducts() {
        List<StoreProduct> products = new ArrayList<>();
        for (Category category : this.categories) {
            products.addAll(category.getProducts());
        }

        return products;
    }
}