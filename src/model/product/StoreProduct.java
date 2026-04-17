package model.product;

import model.app.Pager;
import model.discount.*;
import model.order.Order;
import model.store.Store;
import model.user.RegisteredClient;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * It implements the store's products
 * @author Ana O.R. and Duna P.R.
 * @version 1.3
 * @see Product
 * @see Order
 * @see Review
 */
public abstract class StoreProduct extends Product {
    /** The product's reviews */
    private HashMap<RegisteredClient, Review> reviews = new HashMap<>(); // NOTE: No es final
    /** The product's average punctuation */
    private double averagePunctuation;
    /** The number of available copies of this product */
    private int stock;
    /** The product's discount, if it has one */
    private Discount discount = null;
    /** The product's categories */
    private HashMap<String, Category> categories = new HashMap<>();
    /** The date when the product was added to the cart */
    private LocalDate addedDate;
    private int sales;
    private HashMap<Month, Integer> salesByMonth;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A store product's general constructor
     * @param id                 the product's id
     * @param price              the product's price
     * @param name               the product's name
     * @param description        the product's description
     * @param photo              the product's photo's path
     * @param averagePunctuation the product's average punctuation
     * @param addedDate          the product's added date
     * @param type               the product's product type
     * @param stock              the product's stock
     * @param categories         the product's categories
     * @throws IllegalArgumentException price or stock were negative
     * @throws NullPointerException     name, description or photo's path were null
     */
    StoreProduct(String id, double price, String name, String description, String photo, double averagePunctuation,
                 LocalDate addedDate, ProductType type, int stock, Category... categories)
            throws IllegalArgumentException, NullPointerException {

        super(id, price, name, description, photo, type);

        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        this.stock = stock;
        this.averagePunctuation = averagePunctuation;
        for (Category category : categories) {
            this.categories.put(category.getName(), category);
            Store.getInstance().getCategoryFromName(category.getName()).addProduct(this);
        }
        this.sales = 0;
        this.salesByMonth = new HashMap<Month, Integer>();
        for (Month month : Month.values()) {
            this.salesByMonth.put(month, 0);
        }
        Store.getInstance().addStoreProduct(this);
    }

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

        super(price, name, description, photo, type);

        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        this.stock = stock;
        this.averagePunctuation = 0;
        for (Category category : categories) {
            this.addCategory(category);
            Store.getInstance().getCategoryFromName(category.getName()).addProduct(this);
        }
        this.sales = 0;
        this.salesByMonth = new HashMap<Month, Integer>();
        for (Month month : Month.values()) {
            this.salesByMonth.put(month, 0);
        }
        Store.getInstance().addStoreProduct(this);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It allows the system or an employee to add categories to a product
     * @param newCategories the categories to be added
     */
    public void addCategory(Category... newCategories) {
        for (Category newCategory : newCategories) {
            if (!this.categories.containsKey(newCategory.getName())) {
                this.categories.put(newCategory.getName(), newCategory);
                Store.getInstance().getCategoryFromName(newCategory.getName()).addProduct(this);
            }
        }
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
            throw new IllegalArgumentException("The increasing stock amount cannot be negative");
        }

        stock = stock + value;
    }

    /**
     * It allows an employee to remove categories from a product
     * @param categories the categories to be deleted
     */
    public void removeCategory(Category... categories) {
        for (Category category : categories) {
            this.categories.remove(category.getName());
            Store.getInstance().getCategoryFromName(category.getName()).addProduct(this);
        }
    }

    /**
     * It allows a registered client to review a product
     * @param scoring the review's score
     * @param comment the review's comment
     * @param author  the review's author
     */
    public void addReview(int scoring, String comment, RegisteredClient author) {
        reviews.put(author, new Review(scoring, comment, author));
        this.averagePunctuation =
                (((this.reviews.size() - 1) * this.averagePunctuation) + scoring) / this.reviews.size();
    }

    /**
     * It allows a registered client to review a product
     * @param author the review's author
     * @param review the desired review
     */
    public void addReview(RegisteredClient author, Review review) {
        reviews.put(author, review);
        this.averagePunctuation =
                (((this.reviews.size() - 1) * this.averagePunctuation) + review.getScoring()) / this.reviews.size();
    }

    /**
     * Increases the number of sales by one
     * @param date the date of the sale
     */
    public void increaseSales(LocalDate date) {
        this.salesByMonth.computeIfPresent(date.getMonth(), (month, currentValue) -> currentValue + 1);
        this.sales++;
    }

    /**
     * Increases the number of sales by a certain number
     * @param i    the number of sales made (and to increase)
     * @param date the date of the sale
     */
    public void increaseSales(int i, LocalDate date) {
        this.salesByMonth.computeIfPresent(date.getMonth(), (month, currentValue) -> currentValue + i);
        this.sales += i;
    }

    /**
     * It prints the product's info when seen individually
     */
    @Override
    public void bigPrintInfo() {
        /* super;REVIEW_IDS;AVG_PUNCT;STOCK;CATEGORIES;ADDED_DATE */
        super.bigPrintInfo();
        System.out.println("Stock: " + this.stock);
        System.out.println("Categories: " + this.getPrintCategories());
        System.out.println("Average Punctuation: " + this.averagePunctuation + " stars");
        System.out.println(this.reviews.size() + " reviews");
    }

    /**
     * It prints the product's info when managed
     */
    @Override
    public void printAllInfo() {
        super.printAllInfo();
        System.out.println("Stock: " + this.stock);
        System.out.println("Categories: " + this.getPrintCategories());
    }

    /**
     * It prints the product's review's basic info
     * @param pageNum the desired page's number
     */
    public void printReviews(int pageNum) { // wrap this?
        Pager.getInstance().printReviewListPage(getReviewsList(), pageNum);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Obtains the date the product was added to the cart
     * @return the addedDate, the date it was added
     */
    public LocalDate getAddedDate() {
        return addedDate;
    }

    /**
     * Sets the date the product is added to the cart
     * @param newAddedDate the new addedDate to set
     */
    public void setAddedDate(LocalDate newAddedDate) {
        this.addedDate = newAddedDate;
    } // NOTE: Cart debería tener una copia del producto para poder hacer esto

    /**
     * It gets the store product's average punctuation.
     * @return the store product's average punctuation
     */
    public double getAveragePunctuation() {
        return averagePunctuation;
    }

    /**
     * It sets the store product's average punctuation.
     * @param newAveragePunctuation the store product's new average punctuation
     */
    public void setAveragePunctuation(double newAveragePunctuation) {
        this.averagePunctuation = newAveragePunctuation;
    }

    /**
     * It returns the product's categories
     * @return the product's categories
     */
    public Category[] getCategories() {
        return this.categories.values().toArray(new Category[0]);
    }

    /**
     * It sets the store product's categories
     * @param newCategories the store product's new categories
     */
    public void setCategories(HashMap<String, Category> newCategories) {
        this.categories = newCategories;
    }

    /**
     * Gets discount.
     * @return the discount
     */
    public Discount getDiscount() {
        return discount;
    }

    /**
     * It allows an employee to add discounts to products or categories (Discounts is in charge of making sure they
     * don't overlap)
     * @param newDiscount the new discount to be applied
     * @throws NullPointerException discount was null
     */
    public void setDiscount(Discount newDiscount) throws NullPointerException {
        if (newDiscount == null) {
            throw new NullPointerException("Discount cannot be null");
        }

        this.discount = newDiscount;
    }

    /**
     * It gets the product's price with any fixed percentage discounts applied
     * @return the product's price with any fixed percentage discounts applied
     */
    public double getDiscountedPrice() {

        if (discount == null) {
            return this.getPrice();
        }
        if (this.discount.getType() == DiscountType.FIXED_PERCENTAGE) {
            if (this.discount.getCoverage() == DiscountCoverage.PRODUCT) {
                return this.getPrice() - this.getPrice() * ((ProductFixedPercentage) this.discount).getPercentage();
            } else {
                return this.getPrice() - this.getPrice() * ((CategoryFixedPercentage) this.discount).getPercentage();
            }
        }
        return this.getPrice();
    }

    /**
     * It returns the product's categories in a save-file-friendly manner
     * @return a string containing the game's categories
     */
    public String getPrintCategories() {
        StringBuilder sb = new StringBuilder();

        for (Category category : this.categories.values().toArray(new Category[0])) {
            sb.append(category.getName()).append(",");
        }

        return sb.toString();
    }

    /**
     * It returns the product's reviews in a save-file-friendly manner
     * @return a string containing the game's reviews
     */
    public String getPrintReviews() {
        StringBuilder sb = new StringBuilder();
        Set<RegisteredClient> keys = this.reviews.keySet();

        for (RegisteredClient key : keys) {
            sb.append(reviews.get(key).getId()).append(",");
        }

        return sb.toString();
    }

    /**
     * It gets the store product's reviews
     * @return the store product's reviews
     */
    public HashMap<RegisteredClient, Review> getReviews() {
        return this.reviews;
    }

    /**
     * It gets the product's reviews in a list
     * @return the product's reviews in a list
     */
    public List<Review> getReviewsList() {
        return new ArrayList<>(this.reviews.values());
    }

    /**
     * Obtains the number of items sold of a product
     * @return the sells the num
     */
    public int getSales() {
        return sales;
    }

    /**
     * Sets the number of sales of a product
     * @param sales the sales to set
     */
    public void setSales(int sales) {
        this.sales = sales;
    }

    /**
     * Obtains the sales per month
     * @return the salesByMonth a HashMap with the sales per month
     */
    public HashMap<Month, Integer> getSalesByMonth() {
        return salesByMonth;
    }

    /**
     * Sets the number of sales per month
     * @param salesByMonth the salesByMonth to set
     */
    public void setSalesByMonth(HashMap<Month, Integer> salesByMonth) {
        this.salesByMonth = salesByMonth;
    }

    /**
     * It returns's the product's stock
     * @return the product's store
     */
    public int getStock() {
        return this.stock;
    }

    /**
     * It allows for an employee to change the product's stock as well as blocking or unblocking stock
     * @param newStock the product's new stock
     * @throws IllegalArgumentException stock was negative
     */
    public void setStock(int newStock) throws IllegalArgumentException {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        this.stock = newStock;
    }



    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Written information of a product
     * @return String, information of a product
     */
    @Override
    public String toString() {
        /* super;REVIEW_IDS;AVG_PUNCT;STOCK;CATEGORIES;ADDED_DATE */
        return super.toString() + ";" + this.getPrintReviews() + ";" + this.averagePunctuation + ";" + this.stock +
               ";" + this.getPrintCategories() + this.addedDate;
    }
}