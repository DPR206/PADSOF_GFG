package order;

import product.Pack;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PackQuantity extends PackDiscount implements QuantityDiscount {
    /** The amount of products, or packs, in a cart from which the discount can take place */
    private int udsThreshold;
    /** The amount of money the discount deducts from the order's final price */
    private double deduction;
    /** The list of packs affected by this discount */
    private List<Pack> packs = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A quantity discount's general constructor with packs
     * @param id           the discount's id
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param udsThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param packs        the discount's packs
     */
    public PackQuantity(String id, LocalDateTime startDate, LocalDateTime endDate, int udsThreshold, double deduction,
                        Pack... packs) {
        super(DiscountType.QUANTITY, DiscountCoverage.PACK, startDate, endDate);
        this.addPacks(packs);
        this.udsThreshold = udsThreshold;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new quantity discount with packs
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param udsThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param packs        the discount's packs
     */
    public PackQuantity(LocalDateTime startDate, LocalDateTime endDate, int udsThreshold, double deduction,
                        Pack... packs) {
        super(DiscountType.QUANTITY, DiscountCoverage.PACK, startDate, endDate);
        this.addPacks(packs);
        this.udsThreshold = udsThreshold;
        this.deduction = deduction;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: public createNotification(){}

    // DUE: public obtainDisc();
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
    public int getUdsThreshold() {
        return this.udsThreshold;
    }

    /**
     * It allows the manager to change the quantity discount's amount of products in a cart from which the discount can
     * take place
     * @param udsThreshold the new amount of products in a cart from which the discount can take place
     */
    public void setUdsThreshold(int udsThreshold) {
        this.udsThreshold = udsThreshold;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;GIFT;SPENDING_THRESHOLD;<NUM_PRODS;DEDUCTION> */
        return super.toString() + ";"  /*percentage*/ + ";"  /*gift*/ + ";" /*spendingThreshold*/ + ";" +
               this.udsThreshold + ";" + this.deduction;
    }
}