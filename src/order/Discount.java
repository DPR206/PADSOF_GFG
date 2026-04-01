package order;

import product.*;
import store.Store;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Class name: Discount
 * <p>
 * Description: It implements the general discount
 * @author Ana O.R.
 * @version 1.4
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

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Discount with a certain id
     * @param id        the discount's id
     * @param type      the discount's type
     * @param startDate the discount's start date
     * @param endDate   the discount's end date
     * @throws IllegalArgumentException the illegal argument exception
     */
    public Discount(String id, DiscountType type, DiscountCoverage coverage, LocalDateTime startDate,
                    LocalDateTime endDate) throws IllegalArgumentException {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date is after end date");
        }

        this.id = id;
        this.type = type;
        this.coverage = coverage;
        this.startDate = startDate;
        this.endDate = endDate;

        Store.getInstance().getDiscounts().add(this);
    }

    /**
     * Instantiates a new Discount
     * @param type      the discount's type
     * @param startDate the discount's start date
     * @param endDate   the discount's end date
     * @throws IllegalArgumentException the illegal argument exception
     */
    public Discount(DiscountType type, DiscountCoverage coverage, LocalDateTime startDate, LocalDateTime endDate)
            throws IllegalArgumentException {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date is after end date");
        }

        this.id = type.getSymbol() + String.format("%06d", ++totalId);
        this.type = type;
        this.coverage = coverage;
        this.startDate = startDate;
        this.endDate = endDate;

        Store.getInstance().getDiscounts().add(this);
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
            if (discount != this) {
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

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public DiscountCoverage getCoverage() {
        return coverage;
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

    abstract List<StoreProduct> getProducts();

    // DUE: public abstract createNotification(){}

    // DUE: public abstract obtainDisc(); <- Creo que no puedo pq no devuelven lo mismo

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
     * Gets type.
     * @return the type
     */
    public DiscountType getType() {
        return type;
    }
}