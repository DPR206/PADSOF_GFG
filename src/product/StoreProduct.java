package product;

import order.*;
import user.RegisteredClient;

import java.util.*;
import java.time.*;

/**
 * Class name: StoreProduct
 * <p>
 * Description: It implements the store's products
 * @author Ana O.R. and Duna P.R.
 * @version 1.3
 * @see Product
 * @see Order
 * @see Review
 */
public abstract class StoreProduct extends Product {
    /** The number of available copies of this product */
    private int stock;
    /** The product's reviews */
    private final ArrayList<Review> reviews;
    /** The product's discount, if it has one */
    private Discount discount;
    /** The product's categories */
    private HashMap<String, Category> categories;
    private LocalDate addedDate = null;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Store product's constructor
     * @param price       the product's price
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param type        the product's product type
     * @param stock       the product's stock
     * @param categories  the product's categories
     * @throws IllegalArgumentException price or stock were negative
     * @throws NullPointerException     name, description or photo's path were null
     */
    StoreProduct(double price, String name, String description, String photo, ProductType type, int stock,
                 Category... categories) throws IllegalArgumentException, NullPointerException {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        
        super(price, name, description, photo, type);
        
        //try {
        //	super(price, name, description, photo, type);
        //} catch (IllegalArgumentException arg){
        	 
        //}
        //super(price, name, description, photo, type);
        this.stock = stock;
        this.reviews = new ArrayList<>();
        this.discount = null;
        this.categories = new HashMap<>();
        for (Category category : categories) {
            this.addCategory(category);
        }
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /*--------------------------------------------------- CHANGERS ---------------------------------------------------*/

    /**
     * It allows for an employee to change the product's stock as well as blocking or unblocking stock
     * @param newStock the product's new stock
     * @throws IllegalArgumentException stock was negative
     */
    public void changeStock(int newStock) throws IllegalArgumentException {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        this.stock = newStock;
    }

    /**
     * It decreases the stock a certain value
     * @param value the stock's value that should be decreased
     * @throws IllegalArgumentException value was negative
     */
    public void decreaseStock(int value) throws IllegalArgumentException {
        if (value < 0) {
            throw new IllegalArgumentException("The decreasing amount cannot be negative");
        }

        if ((stock = stock - value) < 0) {
            stock = 0;
        }
    }

    /**
     * It increases the stock a certain value
     * @param value the stock's value that should be increased
     * @throws IllegalArgumentException value was negative
     */
    public void increaseStock(int value) throws IllegalArgumentException {
        if (value < 0) {
            throw new IllegalArgumentException("The increasing amount cannot be negative");
        }

        stock = stock + value;
    }

    /**
     * It allows the system or an employee to add categories to a product
     * @param newCategories the categories to be added
     */
    public void addCategory(Category... newCategories) {
        for (Category newCategory : newCategories) {
            if (!this.categories.containsKey(newCategory.getName())) {
                this.categories.put(newCategory.getName(), newCategory);
            }
        }
    }

    /**
     * It allows an employee to remove categories from a product
     * @param categories the categories to be deleted
     */
    public void removeCategory(Category... categories) {
        for (Category category : categories) {
            this.categories.remove(category.getName());
        }
    }

    /**
     * It allows an employee to add discounts to products or categories (Discounts is in charge of making sure they
     * don't overlap)
     * @param newDiscount the new discount to be applied
     * @throws NullPointerException discount was null
     */
    public void changeDiscount(Discount newDiscount) throws NullPointerException {
        if (discount == null) {
            throw new NullPointerException("Discount cannot be null");
        }

        this.discount = newDiscount;
    }

    /*---------------------------------------------------- GETTERS ---------------------------------------------------*/

    /**
     * It returns the product's categories
     * @return the product's categories
     */
    public Category[] getCategories() {
        return this.categories.values().toArray(new Category[0]);
    }

    /**
     * It returns the product's categories in a save-file-friendly manner
     * @return a string containing the game's categories
     */
    public String getPrintCategories() {
        StringBuilder sb = new StringBuilder();

        for (Category category : this.categories.values().toArray(new Category[0])) {
            sb.append(category.toString()).append(", ");
        }

        return sb.toString();
    }

    /**
     * It returns's the product's stock
     * @return the product's store
     */
    public int getStock() {
        return this.stock;
    }
    
    
    /**
     * Obtains the date the product was added to the cart
     * 
	 * @return the addedDate, the date it was added
	 */
	public LocalDate getAddedDate() {
		return addedDate;
	}

	/**
	 * Sets the date the product is added to the cart
	 * 
	 * @param addedDate the addedDate to set
	 */
	public void setAddedDate(LocalDate addedDate) {
		this.addedDate = addedDate;
	}

    /**
     * It allows a registered client to review a product
     * @param scoring the review's score
     * @param comment the review's comment
     * @param author  the review's author
     */
    public void addReview(int scoring, String comment, RegisteredClient author) {
        reviews.add(new Review(scoring, comment, author));
    }

	
}