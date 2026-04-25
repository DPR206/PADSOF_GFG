package model.discount;

import model.product.Pack;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * It implements the Pack discount whose type is Volume
 * @author Ana O.R.
 * @version 1.0
 * @see Pack
 */
public class PackVolume extends PackDiscount implements VolumeDiscount, Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
    /** The spending threshold that allows the discount to take place */
    private double spendingThreshold;
    /** The amount of money the discount deducts from the order's final price */
    private double deduction;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A volume discount's general constructor with packs
     * @param id                the discount's id
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param deduction         the amount of money the discount deducts from the order's final price
     * @param packs             the discount's packs
     */
    public PackVolume(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                      double deduction, Pack... packs) {
        super(id, DiscountType.VOLUME, DiscountCoverage.PACK, startDate, endDate);
        this.addPacks(packs);
        this.spendingThreshold = spendingThreshold;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new Volume discount with packs
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param deduction         the amount of money the discount deducts from the order's final price
     * @param packs             the discount's packs
     */
    public PackVolume(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double deduction,
                      Pack... packs) {
        super(DiscountType.VOLUME, DiscountCoverage.PACK, startDate, endDate);
        this.addPacks(packs);
        this.spendingThreshold = spendingThreshold;
        this.deduction = deduction;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the volume discount's deduction
     * @return the volume discount's deduction
     */
    public double getDeduction() {
        return this.deduction;
    }

    /**
     * It allows the manager to change the volume discount's discount
     * @param newDeduction the new deduction
     */
    public void setDeduction(double newDeduction) {
        this.deduction = newDeduction;
    }

    /**
     * It returns the discount's basic info
     * @return the discount's basic info
     */
    public String getPrintInfo() {
        return "-" + this.deduction + "€ if you spend over " + this.spendingThreshold + "€ on the packs with id: " +
               this.getPrintPacks();
    }

    /**
     * It sets the volume discount's spending threshold.
     * @return the volume discount's spending threshold
     */
    public double getSpendingThreshold() {
        return spendingThreshold;
    }

    /**
     * It allows the manager to change the volume discount's spending threshold
     * @param spendingThreshold the new spending threshold
     */
    public void setSpendingThreshold(double spendingThreshold) {
        this.spendingThreshold = spendingThreshold;
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
        return super.toString() + ";"  /*percentage*/ + ";"  /*gift*/ + ";" + this.spendingThreshold +
               ";" /*numThreshold
         */ + ";" + this.deduction;
    }
}