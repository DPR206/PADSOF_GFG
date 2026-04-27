package model.discount;

import model.product.Pack;
import model.product.StoreProduct;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * It implements the general discount for those with Pack coverage
 * @author Ana O.R.
 * @version 1.0
 * @see Pack
 * @see StoreProduct
 */
public abstract class PackDiscount extends Discount implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
    /** The list of categories affected by this discount */
    private List<Pack> packs = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A Pack discount's general constructor
     * @param id        the discount's id
     * @param type      the discount's type
     * @param coverage  the discount's coverage
     * @param startDate the discount's start date
     * @param endDate   the discount's end date
     */
    public PackDiscount(String id, DiscountType type, DiscountCoverage coverage, LocalDateTime startDate,
                        LocalDateTime endDate) {
        super(id, type, coverage, startDate, endDate);
    }

    /**
     * A Pack discount's constructor with default id
     * @param type      the discount's type
     * @param coverage  the discount's coverage
     * @param startDate the discount's start date
     * @param endDate   the discount's end date
     */
    public PackDiscount(DiscountType type, DiscountCoverage coverage, LocalDateTime startDate, LocalDateTime endDate) {
        super(type, coverage, startDate, endDate);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It adds packs to the discount
     * @param packs the desired packs
     */
    public void addPacks(Pack... packs) {
        for (Pack pack : packs) {
            if (conflictingDisc(pack.getProducts())) {
                throw new IllegalArgumentException("Conflicting Discount");
            }
            this.packs.add(pack);
            for (StoreProduct product : pack.getProducts()) {
                product.setDiscount(this);
            }
            pack.setDiscount(this);
        }
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the packs affected by this discount
     * @return the packs affected by this discount
     */
    public List<Pack> getPacks() {
        return packs;
    }

    /**
     * It sets the packs affected by this discount
     * @param newPacks the new packs affected by this discount
     */
    public void setPacks(List<Pack> newPacks) {
        this.packs = newPacks;
    }

    /**
     * It returns the discount's categories in a save-file-friendly manner
     * @return a string containing the game's categories
     */
    public String getPrintPacks() {
        StringBuilder sb = new StringBuilder();

        for (Pack pack : this.packs) {
            sb.append(pack.getId()).append(",");
        }

        return sb.toString();
    }

    /**
     * It gets the discount's products
     * @return the discount's products
     */
    public List<StoreProduct> getProducts() {
        List<StoreProduct> products = new ArrayList<>();
        for (Pack pack : this.packs) {
            products.addAll(pack.getProducts());
        }

        return products;
    }
}