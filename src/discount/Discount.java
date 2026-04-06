package discount;

import product.*;
import store.Store;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * It implements the general discount
 * @author Ana O.R.
 * @version 1.5
 * @see Store
 * @see StoreProduct
 * @see Pack
 * @see Category
 */
public abstract class Discount {
    /** The global variable to determine which id should a new product have */
    static public int totalId = -1;
    /** The discount's type */
    public final DiscountType type;
    /** The discount's coverage */
    public final DiscountCoverage coverage;
    /** The discount's id */
    private final String id;
    /** The date when the discount starts */
    private LocalDateTime startDate;
    /** The date when the discount ends */
    private LocalDateTime endDate;
    /** Whether the discount is disabled (due to expiration) or not */
    private boolean disabled;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Discount with a certain id
     * @param assignedId        the discount's id
     * @param assignedType      the discount's type
     * @param assignedCoverage  the coverage
     * @param assignedStartDate the discount's start date
     * @param assignedEndDate   the discount's end date
     * @throws IllegalArgumentException the dates weren't valid
     */
    public Discount(String assignedId, DiscountType assignedType, DiscountCoverage assignedCoverage,
                    LocalDateTime assignedStartDate, LocalDateTime assignedEndDate) throws IllegalArgumentException {
        this.id = assignedId;
        this.type = assignedType;
        this.coverage = assignedCoverage;
        this.setStartDate(assignedStartDate);
        this.setEndDate(assignedEndDate);

        Store.getInstance().getDiscounts().add(this);
    }

    /**
     * Instantiates a new Discount
     * @param assignedType      the discount's type
     * @param assignedCoverage  the coverage
     * @param assignedStartDate the discount's start date
     * @param assignedEndDate   the discount's end date
     * @throws IllegalArgumentException the dates weren't valid
     */
    public Discount(DiscountType assignedType, DiscountCoverage assignedCoverage, LocalDateTime assignedStartDate,
                    LocalDateTime assignedEndDate) throws IllegalArgumentException {

        this.id = assignedType.getSymbol() + String.format("%06d", ++totalId);
        this.type = assignedType;
        this.coverage = assignedCoverage;
        this.startDate = assignedStartDate;
        this.endDate = assignedEndDate;
        this.setStartDate(assignedStartDate);
        this.setEndDate(assignedEndDate);

        Store.getInstance().getDiscounts().add(this);
    }


    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the discount's total id
     * @return the discount's total id
     */
    public static int getTotalId() {
        return totalId;
    }

    /**
     * It sets the discount's total id.
     * @param newTotalId the discount's new total id
     */
    public static void setTotalId(int newTotalId) {
        Discount.totalId = newTotalId;
    }

    /**
     * It checks whether the desired discount can be added to the store without interfering with another
     * @param testedProducts the discount to be tested's products
     * @return true if it is conflicting, false otherwise
     */
    public boolean conflictingDisc(List<StoreProduct> testedProducts) {
        List<Discount> discounts = Store.getInstance().getDiscounts();

        for (Discount discount : discounts) {
            if ((discount != this) && (discount.getDisabled())) {
                List<StoreProduct> alreadyAffectedProducts = discount.getProducts();
                if (discount.getCoverage() == DiscountCoverage.PRODUCT) {
                    ProductDiscount productDiscount = (ProductDiscount) discount;
                    if (productDiscount.getOverWholeStore()) {
                        return true;
                    }
                }

                if (!Collections.disjoint(alreadyAffectedProducts, testedProducts)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * It checks whether the desired discount can be added to the store without interfering with another
     * @param overWholeStore whether the discount to be tested applies over the whole store, must be true
     * @return true if it is conflicting, false otherwise
     * @throws IllegalArgumentException overWholeStore must be true
     */
    public boolean conflictingDisc(boolean overWholeStore) throws IllegalArgumentException {
        if (!overWholeStore) {
            throw new IllegalArgumentException("overWholeStore must be true");
        }

        List<Discount> discounts = Store.getInstance().getDiscounts();

        for (Discount discount : discounts) {
            if ((discount != this) && (discount.getDisabled())) {
                if (!discount.getProducts().isEmpty()) {
                    return true;
                }

            }
        }

        return false;
    }

    /**
     * It checks if the discount is no longer active
     */
    public void checkExpiration() {
        if (LocalDateTime.now().isBefore(this.startDate) || LocalDateTime.now().isAfter(this.endDate)) {
            this.disabled = true;
        }
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    // DUE: abstract createNotification(){}

    /**
     * It gets the discount's coverage type
     * @return the discount's coverage type
     */
    public DiscountCoverage getCoverage() {
        return coverage;
    }

    /**
     * Is gets whether the discount has expired
     * @return true if it is disabled, false otherwise
     */
    public boolean getDisabled() {
        if (!disabled) {
            this.checkExpiration();
        }
        return this.disabled;
    }

    /**
     * It sets whether the discount has expired or not
     * @param newDisabled true if it is disabled, false otherwise
     */
    public void setDisabled(boolean newDisabled) {
        this.disabled = newDisabled;
    }

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
     * It returns the discount's basic info
     * @return the discount's basic info
     */
    public abstract String getPrintInfo();

    /**
     * It gets the store products affected by this discount
     * @return the store products affected by this discount
     */
    public abstract List<StoreProduct> getProducts();

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

    /**
     * It gets the discount's discount type
     * @return the discount's discount type
     */
    public DiscountType getType() {
        return type;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Written information of a discount
     * @return the written information of a discount
     */
    @Override
    public String toString() { // DUE
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;GIFT;<SPENDING_THRESHOLD>;NUM_PRODS;
        <DEDUCTION> */
        return super.toString();
    }
}