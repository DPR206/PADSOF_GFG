package model.discount;

import model.product.Pack;

import java.time.LocalDateTime;

/**
 * It implements the Pack discount whose type is Quantity
 * @author Ana O.R.
 * @version 1.0
 * @see Pack
 */
public class PackQuantity extends PackDiscount implements QuantityDiscount {
    /** The amount of products, or packs, in a cart from which the discount can take place */
    private int numThreshold;
    /** The amount of money the discount deducts from the order's final price */
    private double deduction;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A quantity discount's general constructor with packs
     * @param id           the discount's id
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param numThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param packs        the discount's packs
     */
    public PackQuantity(String id, LocalDateTime startDate, LocalDateTime endDate, int numThreshold, double deduction,
                        Pack... packs) {
        super(id, DiscountType.QUANTITY, DiscountCoverage.PACK, startDate, endDate);
        this.addPacks(packs);
        this.numThreshold = numThreshold;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new quantity discount with packs
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param numThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param packs        the discount's packs
     */
    public PackQuantity(LocalDateTime startDate, LocalDateTime endDate, int numThreshold, double deduction,
                        Pack... packs) {
        super(DiscountType.QUANTITY, DiscountCoverage.PACK, startDate, endDate);
        this.addPacks(packs);
        this.numThreshold = numThreshold;
        this.deduction = deduction;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    /**
     * It gets the quantity discount's discount
     * @return the quantity discount's discount
     */
    public double getDeduction() {
        return this.deduction;
    }

    /**
     * It allows the manager to change the quantity discount's discount
     * @param newDeduction the new discount
     */
    public void setDeduction(double newDeduction) {
        this.deduction = newDeduction;
    }

    /**
     * It gets the quantity discount's amount of products in a cart from which the discount can take place
     * @return the amount of products in a cart from which the discount can take place
     */
    public int getNumThreshold() {
        return this.numThreshold;
    }

    /**
     * It allows the manager to change the quantity discount's amount of products in a cart from which the discount can
     * take place
     * @param newNumThreshold the new amount of products in a cart from which the discount can take place
     */
    public void setNumThreshold(int newNumThreshold) {
        this.numThreshold = newNumThreshold;
    }

    /**
     * It returns the discount's basic info
     * @return the discount's basic info
     */
    public String getPrintInfo() {
        return "-" + this.deduction + "€ if you buy more than " + this.numThreshold + " units of the packs with id: " +
               this.getPrintPacks();
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Written information of a discount
     * @return the written information of a discount
     */
    @Override
    public String toString() { // DUE
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;GIFT;SPENDING_THRESHOLD;<NUM_PRODS;DEDUCTION> */
        return super.toString() + ";"  /*percentage*/ + ";"  /*gift*/ + ";" /*spendingThreshold*/ + ";" +
               this.numThreshold + ";" + this.deduction;
    }
}