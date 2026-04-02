package order;

import product.Category;
import product.StoreProduct;
import store.Store;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class name: Category Discount
 * <p>
 * Description: It implements the general discount for those with Category coverage
 * @author Ana O.R.
 * @version 1.0
 * @see Store
 * @see StoreProduct
 * @see Category
 */
public abstract class CategoryDiscount extends Discount {
    /** The list of categories affected by this discount */
    private List<Category> categories = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A Category discount's general constructor
     * @param id        the discount's id
     * @param type      the discount's type
     * @param coverage  the discount's coverage
     * @param startDate the discount's start date
     * @param endDate   the discount's end date
     */
    public CategoryDiscount(String id, DiscountType type, DiscountCoverage coverage, LocalDateTime startDate,
                            LocalDateTime endDate) {
        super(id, type, coverage, startDate, endDate);
    }

    /**
     * A Category discount's constructor with default id
     * @param type      the discount's type
     * @param coverage  the discount's coverage
     * @param startDate the discount's start date
     * @param endDate   the discount's end date
     */
    public CategoryDiscount(DiscountType type, DiscountCoverage coverage, LocalDateTime startDate,
                            LocalDateTime endDate) {
        super(type, coverage, startDate, endDate);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It adds categories to the discount
     * @param categories the desired categories
     * @throws IllegalArgumentException the desired discount was conflicting
     */
    public void addCategories(Category... categories) throws IllegalArgumentException {
        for (Category category : categories) {
            if (conflictingDisc(category.getProducts())) {
                throw new IllegalArgumentException("The desired discount was conflicting");
            }
            this.categories.add(category);
            for (StoreProduct product : category.getProducts()) {
                product.setDiscount(this);
            }
        }
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the discount's products
     * @return the discount's products
     */
    List<StoreProduct> getProducts() {
        List<StoreProduct> products = new ArrayList<>();
        for (Category category : this.categories) {
            products.addAll(category.getProducts());
        }

        return products;
    }
}