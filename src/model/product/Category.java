package model.product;

import model.store.Store;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * It implements the categories
 * @author Ana O.R. and Duna P.R.
 * @version 1.4
 * @see Store
 */
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
    /** The category's name */
    public String name;
    /** The category's total revenue */
    private double revenue;
    /** The store products that belong to this category */
    private List<StoreProduct> products = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Category's full constructor
     * @param assignedName    the new category's name
     * @param assignedRevenue the category's revenue
     * @throws IllegalArgumentException revenue was negative or category already existed
     * @throws NullPointerException     store or name were null
     */
    public Category(String assignedName, double assignedRevenue) throws IllegalArgumentException, NullPointerException {
        if (assignedName == null) {
            throw new NullPointerException("Name must not be null");
        }
        if (assignedRevenue < 0) {
            throw new NullPointerException("Revenue must not be negative");
        }

        this.name = assignedName;
        this.revenue = assignedRevenue;

        Store.getInstance().getCategories().put(this.name, this);
    }

    /**
     * Category's constructor
     * @param assignedName the new category's name
     * @throws IllegalArgumentException category already existed
     * @throws NullPointerException     store or name were null
     */
    public Category(String assignedName) throws IllegalArgumentException, NullPointerException {
        this(assignedName, 0);
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
        if (newName == null) {
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

    /**
     * Obtains the revenue of a category
     * @return the revenue the total revenue
     */
    public double getRevenue() {
        return revenue;
    }

    /**
     * Sets the revenue of a category
     * @param newRevenue the revenue to set
     */
    public void setRevenue(double newRevenue) {
        this.revenue = newRevenue;
    }


    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * It allows a category to be saved
     * @return the category's info
     */
    @Override
    public String toString() {
        return this.name + ";" + this.revenue;
    }
}