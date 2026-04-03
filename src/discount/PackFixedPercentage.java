package discount;

import product.Pack;

import java.time.LocalDateTime;

/**
 * It implements the Pack discount whose type is Fixed Percentage
 * @author Ana O.R.
 * @version 1.0
 * @see Pack
 */
public class PackFixedPercentage extends PackDiscount implements FixedPercentageDiscount {
    /** The percentage deducted from a product's price */
    private double percentage;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A fixed percentage discount's general constructor with packs
     * @param id         the discount's id
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param packs      the discount's pack
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public PackFixedPercentage(String id, LocalDateTime startDate, LocalDateTime endDate, double percentage,
                               Pack... packs) throws IllegalArgumentException {
        super(id, DiscountType.FIXED_PERCENTAGE, DiscountCoverage.PACK, startDate, endDate);
        this.addPacks(packs);
        this.setPercentage(percentage);
    }

    /**
     * Instantiates a new fixed percentage discount with packs
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param packs      the discount's packs
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public PackFixedPercentage(LocalDateTime startDate, LocalDateTime endDate, double percentage, Pack... packs)
            throws IllegalArgumentException {
        super(DiscountType.FIXED_PERCENTAGE, DiscountCoverage.PACK, startDate, endDate);
        this.addPacks(packs);
        this.setPercentage(percentage);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the fixed percentage discount's percentage.
     * @return the fixed percentage discount's percentage
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * It allows the manager to change the fixed percentage discount's percentage
     * @param newPercentage the new percentage
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public void setPercentage(double newPercentage) throws IllegalArgumentException {
        if (newPercentage < 0 || newPercentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }

        this.percentage = newPercentage;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Written information of a discount
     * @return the written information of a discount
     */
    @Override
    public String toString() { // DUE
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];<PERCENTAGE>;GIFT;SPENDING_THRESHOLD;NUM_PRODS;DEDUCTION */
        return super.toString() + ";" + this.percentage + ";"  /*gift*/ + ";" /*spendingThreshold*/ + ";" /*numThreshold
         */ + ";" /*deduction*/;
    }
}