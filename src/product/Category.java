package product;

import store.Store;

/**
 * Class name: Category
 * <p>
 * Description: It implements the categories
 * @author Ana O.R.
 * @version 1.2
 * @see Store
 */
public class Category {
    /** The category's name */
    public String name;
    /** The store the category is part of */
    private final Store store;
    /** The category's total revenue */
    private double revenue;

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
        if (store.isCategoryInStore(name) == true) {
            this.name = "Borrar"; // DUE: Revisar esto
            this.store = store;
            this.revenue = 0;
            throw new IllegalArgumentException("Category already exists");
        }

        this.name = name;
        this.store = store;
        this.revenue = 0;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /*--------------------------------------------------- CHANGERS ---------------------------------------------------*/

    /**
     * It allows a manager to change a Category's name
     * @param newName the category's new name
     * @throws NullPointerException name was null
     */
    public void changeName(String newName) throws NullPointerException {
        if (name == null) {
            throw new NullPointerException("Name must not be null");
        }
        if (this.store.isCategoryInStore(newName) == true) {
            throw new IllegalArgumentException("Category already exists");
        }

        this.name = newName;
    }

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

    /*---------------------------------------------------- GETTERS ---------------------------------------------------*/

    /**
     * It returns the category's name
     * @return the category's name
     */
    public String getName() {
        return this.name;
    }
}