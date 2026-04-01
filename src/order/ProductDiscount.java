package order;

import product.StoreProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class ProductDiscount extends Discount {
    /** The list of products affected by this discount */
    private List<StoreProduct> products = new ArrayList<>();
    /** Whether the discount is applied over the whole store or not */
    private boolean overWholeStore;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ProductDiscount(String id, DiscountType type, DiscountCoverage coverage, LocalDateTime startDate,
                           LocalDateTime endDate) {
        super(id, type, coverage, startDate, endDate);
    }

    public ProductDiscount(DiscountType type, DiscountCoverage coverage, LocalDateTime startDate,
                           LocalDateTime endDate) {
        super(type, coverage, startDate, endDate);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It adds products to the discount
     * @param products the desired products
     * @throws IllegalArgumentException the discount is conflicting
     */
    public void addProducts(StoreProduct... products) throws IllegalArgumentException {
        if (conflictingDisc(List.of(products))) {
            throw new IllegalArgumentException("Conflicting Discount");
        }

        this.products.addAll(List.of(products));
        for (StoreProduct product : products) {
            product.setDiscount(this);
        }
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets whether the discount is applied over the whole store or not
     * @return whether the discount is applied over the whole store or not
     */
    public boolean getOverWholeStore() {
        return this.overWholeStore;
    }

    /**
     * It returns the discount's products in a save-file-friendly manner
     * @return a string containing the discount's products' id
     */
    public String getPrintProducts() {
        StringBuilder sb = new StringBuilder();

        for (StoreProduct product : this.products) {
            sb.append(product.getId()).append(",");
        }

        return sb.toString();
    }

    /**
     * It gets the store products affected by this discount
     * @return the store products affected by this discount
     */
    public List<StoreProduct> getProducts() {
        return this.products;
    }

    /**
     * It sets the store products affected by this discount
     * @param newProducts the new list of products
     * @throws IllegalArgumentException the discount is conflicting
     */
    public void setProducts(List<StoreProduct> newProducts) throws IllegalArgumentException {
        if (conflictingDisc(products)) {
            throw new IllegalArgumentException("Conflicting Discount");
        }

        this.products = newProducts;
        for (StoreProduct newProduct : newProducts) {
            newProduct.setDiscount(this);
        }
    }
}