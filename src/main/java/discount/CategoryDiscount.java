package discount;

import product.Category;
import product.StoreProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * It implements the general discount for those with Category coverage
 * @author Ana O.R.
 * @version 1.0
 * @see Category
 * @see StoreProduct
 */
public abstract class CategoryDiscount extends Discount {
    /** The list of categories affected by this discount */
    private List<Category> categories = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A Category discount's general constructor
     * @param assignedId        the discount's id
     * @param assignedType      the discount's type
     * @param assignedCoverage  the discount's coverage
     * @param assignedStartDate the discount's start date
     * @param assignedEndDate   the discount's end date
     */
    public CategoryDiscount(String assignedId, DiscountType assignedType, DiscountCoverage assignedCoverage,
                            LocalDateTime assignedStartDate, LocalDateTime assignedEndDate) {
        super(assignedId, assignedType, assignedCoverage, assignedStartDate, assignedEndDate);
    }

    /**
     * A Category discount's constructor with default id
     * @param assignedType      the discount's type
     * @param assignedCoverage  the discount's coverage
     * @param assignedStartDate the discount's start date
     * @param endDate           the discount's end date
     */
    public CategoryDiscount(DiscountType assignedType, DiscountCoverage assignedCoverage,
                            LocalDateTime assignedStartDate, LocalDateTime endDate) {
        super(assignedType, assignedCoverage, assignedStartDate, endDate);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It adds categories to the discount
     * @param newCategories the desired categories
     * @throws IllegalArgumentException the desired discount was conflicting
     */
    public void addCategories(Category... newCategories) throws IllegalArgumentException {
        for (Category category : newCategories) {
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
     * It gets the discount's categories
     * @return the discount's categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * It sets the discount's categories
     * @param newCategories the discount's new categories
     */
    public void setCategories(List<Category> newCategories) {
        this.categories = newCategories;
    }

    /**
     * It returns the discount's categories in a save-file-friendly manner
     * @return a string containing the game's categories
     */
    public String getPrintCategories() {
        StringBuilder sb = new StringBuilder();

        for (Category category : this.categories) {
            sb.append(category.getName()).append(",");
        }

        return sb.toString();
    }

    /**
     * It gets the discount's products
     * @return the discount's products
     */
    public List<StoreProduct> getProducts() {
        List<StoreProduct> products = new ArrayList<>();
        for (Category category : this.categories) {
            products.addAll(category.getProducts());
        }

        return products;
    }
}