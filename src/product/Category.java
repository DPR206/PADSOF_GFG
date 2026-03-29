package product;

import store.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Class name: Category
 * <p>
 * Description: It implements the categories
 * @author Ana O.R.
 * @version 1.3
 * @see Store
 */
public class Category {
    /** The category's name */
    public String name;
    /** The category's total revenue */
    private double revenue;
    /** The store products that belong to this category */
    private List<StoreProduct> products = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Category's constructor
     * @param store the store the category is part of
     * @param name  the new category's name
     * @throws IllegalArgumentException category already existed
     * @throws NullPointerException     store or name were null
     */
    public Category(Store store, String name) throws IllegalArgumentException, NullPointerException {
        if (store == null || name == null) {
            throw new NullPointerException("Name must not be null");
        }

        if (store.isCategoryInStore(name)) {
            throw new IllegalArgumentException("Category already exists");
        }

        this.name = name;
        this.revenue = 0;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It increases the category's revenue when a product belonging to it is sold
     * @param profit the sold product's profit
     * @throws IllegalArgumentException profit was negative
     */
    public void increaseRevenue(double profit) throws IllegalArgumentException {
        if (profit < 0) {
            throw new IllegalArgumentException("Profit must not be negative");
        }

        this.revenue = this.revenue + profit;
    }

    /**
     * It allows for product's categories to be changed
     * @param product the product to be added
     */
    public void addProduct(StoreProduct product) {
        this.products.add(product);
    }

    /**
     * It allows for product's categories to be changed
     * @param product the product to be removed
     */
    public void removeProduct(StoreProduct product) {
        this.products.remove(product);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It returns the category's name
     * @return the category's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * It allows a manager to change a Category's name
     * @param newName the category's new name
     * @throws NullPointerException name was null
     */
    public void setName(String newName) throws NullPointerException {
        if (name == null) {
            throw new NullPointerException("Name must not be null");
        }
        if (Store.getInstance().isCategoryInStore(newName)) {
            throw new IllegalArgumentException("Category already exists");
        }

        this.name = newName;
    }

    /**
     * It gets the category's products
     * @return the category's products
     */
    public List<StoreProduct> getProducts() {
        return this.products;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        // DUE
        return "Category{" + ", name='" + name + '\'' + ", revenue=" + revenue + '}';
    }
}