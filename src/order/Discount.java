package order;

import product.Category;
import product.StoreProduct;
import store.Store;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class name: Discount
 * <p>
 * Description: It implements the general discount
 * @author Ana O.R.
 * @version 1.2
 */
public abstract class Discount {
    /** The global variable to determine which id should a new product have */
    static public int totalId = -1;
    /** The discount's id */
    private final String id;
    /** The discount's type */
    private final DiscountType type;
    /** Whether the discount is applied over the whole store or not */
    private boolean overWholeStore;
    /** The date when the discount starts */
    private LocalDateTime startDate;
    /** The date when the discount ends */
    private LocalDateTime endDate;
    /** The list of products affected by this discount */
    private List<StoreProduct> products;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A discount's general constructor
     * @param id        the discount's id
     * @param startDate the date when the discount starts
     * @param endDate   the date when the discount ends
     * @param products  the products
     * @throws IllegalArgumentException the dates aren't valid or the discount is conflicting
     */
    public Discount(String id, LocalDateTime startDate, LocalDateTime endDate, DiscountType type,
                    StoreProduct... products) throws IllegalArgumentException {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date is after end date");
        }

        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        addProducts(products);
        this.overWholeStore = false;

        Store.getInstance().getDiscounts().add(this);
    }

    /**
     * A discount's constructor when applied over categories
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param categories the categories
     * @throws IllegalArgumentException the dates aren't valid or the discount is conflicting
     */
    public Discount(LocalDateTime startDate, LocalDateTime endDate, DiscountType type, Category... categories)
            throws IllegalArgumentException {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date is after end date");
        }

        this.id = type.getSymbol() + String.format("%06d", ++totalId);
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        addCategories(categories);
        this.overWholeStore = false;

        Store.getInstance().getDiscounts().add(this);
    }

    /**
     * A discount's constructor when applied over the whole store
     * @param startDate      the date when the discount starts
     * @param endDate        the date when the discount ends
     * @param overWholeStore whether the discount is applied over the whole store or not (must be true)
     * @throws IllegalArgumentException the dates aren't valid or the discount is conflicting
     */
    public Discount(LocalDateTime startDate, LocalDateTime endDate, DiscountType type, boolean overWholeStore)
            throws IllegalArgumentException {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date is after end date");
        }
        if (!overWholeStore) {
            throw new IllegalArgumentException("Must be over the whole store for it to work");
        }

        this(startDate, endDate, type, (Store.getInstance().getCategories().values().toArray(new Category[0])));
        this.overWholeStore = true;
    }

    /**
     * A discount's constructor
     * @param startDate the date when the discount starts
     * @param endDate   the date when the discount ends
     * @param products  the products
     * @throws IllegalArgumentException the illegal argument exception
     */
    public Discount(LocalDateTime startDate, LocalDateTime endDate, DiscountType type, StoreProduct... products)
            throws IllegalArgumentException {

        this(type.getSymbol() + String.format("%06d", ++totalId), startDate, endDate, type, products);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Checks whether the desired discount can be added to the store without interfering with another
     * @param testedProducts the discount to be tested's products
     * @return true if it is conflicting, false otherwise
     */
    public boolean conflictingDisc(List<StoreProduct> testedProducts) {
        List<Discount> discounts = Store.getInstance().getDiscounts();

        for (Discount discount : discounts) {
            List<StoreProduct> alreadyAffectedProducts = discount.getProducts();
            // NOTE: retainAll mantiene en alreadyAffectedProducts los productos en común, así pues, si afectaran al
            // mismo producto la lista resultante no estaría vacía
            if (discount.getOverWholeStore()) {
                return false;
            }
            alreadyAffectedProducts.retainAll(testedProducts);
            if (!alreadyAffectedProducts.isEmpty()) {
                System.out.println("Discount conflicts with Discount #" + discount.getId());
                return false;
            }
        }

        return true;
    }

    public void addProducts(StoreProduct... products) throws IllegalArgumentException {
        if (conflictingDisc(List.of(products))) {
            throw new IllegalArgumentException("Conflicting Discount");
        }

        this.products.addAll(List.of(products));
        for (StoreProduct product : products) {
            product.setDiscount(this);
        }
    }

    public void addCategories(Category... categories) {
        for (Category category : categories) {
            addProducts(category.getProducts().toArray(new StoreProduct[0]));
        }
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the discount's end date
     * @return the discount's end date
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    /**
     * It allows the manager to change a discount's end date
     * @param newEndDate the discount's new end date
     * @throws IllegalArgumentException the start date is after the end date
     */
    public void setEndDate(LocalDateTime newEndDate) throws IllegalArgumentException {
        if (this.startDate.isAfter(newEndDate)) {
            throw new IllegalArgumentException("Start date is after the end date");
        }

        this.endDate = newEndDate;
    }

    /**
     * It gets the discount's id
     * @return the discount's id
     */
    public String getId() {
        return this.id;
    }

    /**
     * It gets whether the discount is applied over the whole store or not
     * @return whether the discount is applied over the whole store or not
     */
    public boolean getOverWholeStore() {
        return this.overWholeStore;
    }

    // DUE: public abstract createNotification(){}

    // DUE: public abstract obtainDisc(); <- Creo que no puedo pq no devuelven lo mismo

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

    /**
     * It gets the discount's start date
     * @return the discount's start date
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    /**
     * It allows the manager to change a discount's start date
     * @param newStartDate the discount's new start date
     * @throws IllegalArgumentException the start date is after the end date
     */
    public void setStartDate(LocalDateTime newStartDate) throws IllegalArgumentException {
        if (newStartDate.isAfter(this.endDate)) {
            throw new IllegalArgumentException("Start date is after the end date");
        }

        this.startDate = newStartDate;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        /* TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE */
        return this.type.getSymbol() + ";" + this.id + ";" + this.startDate + ";" + this.endDate + ";" +
               this.getPrintProducts() + ";" + this.overWholeStore;
    }
}