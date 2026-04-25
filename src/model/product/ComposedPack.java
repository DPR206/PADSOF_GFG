/**
 *
 */
package model.product;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;

/**
 * It implements the composed packs
 * @author Duna P.R.
 * @version 1.0
 * @see Pack
 */
public class ComposedPack extends Pack implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */

    private HashSet<Pack> packs;

    /*------------------------------------------------------CONSTRUCTORS-----------------------------------------------------------------------*/

    /**
     * Creates a new composed pack
     *
     * @param id the pack's id
     * @param price the pack's price
     * @param newPacks the pack's it contains
     * @param date the date it was added to the cart
     */
    public ComposedPack(int id, double price, HashSet<Pack> newPacks, LocalDate date) {
        super(id, price, date);
        this.packs = newPacks;
    }

    /**
     * Creates a new composed pack with default id
     *
     * @param newPacks the pack's it contains
     * @param price the pack's price
     * @param date the date it was added to the cart
     */
    public ComposedPack(HashSet<Pack> newPacks, double price, LocalDate date) {
        super(price, date);
        packs = newPacks;
    }

    /**
     * Creates a new composed pack with default id and date
     *
     * @param price, price of the pack
     * @param newPacks, the packs the pack contains
     */
    public ComposedPack(double price, HashSet<Pack> newPacks) {
        this(newPacks, price, null);
    }

    /*------------------------------------------------GETTERS AND SETTERS-----------------------------------------------------------------------*/

    /**
     * Returns the packs within the ComposedPack
     *
     * @return the packs, the packs it contains
     */
    public HashSet<Pack> getPacks() {
    	return this.packs;
    }

    /**
     * Changes the packs within the composed pack
     *
     * @param newPacks the packs to set
     */
    public void setPacks(HashSet<Pack> newPacks) {
        this.packs = newPacks;
    }

    /*---------------------------------------------------METHODS--------------------------------------------------------------------------------*/

    /**
     * Adds a new pack to the composed pack
     *
     * @param pack, the pack to add
     */
    public void addPack(Pack pack) {
        packs.add(pack);
        pack.decreaseStock();
    }

    /**
     * Removes a pack from the composed pack
     *
     * @param pack, the pack to remove
     */
    public void removePack(Pack pack) {
        packs.remove(pack);
        pack.decreaseStock();
    }


    /**
     * Adds various packs to the composed pack
     *
     * @param newPacks, the packs to add
     */
    public void addPacks(HashSet<Pack> newPacks) {
        packs.addAll(newPacks);
        for (Pack p : newPacks) {
            p.increaseStock();
        }
    }

    /**
     * Remove various packs from the composed pack
     *
     * @param deletePacks, the packs to delete
     */
    public void removePacks(HashSet<Pack> deletePacks) {
        packs.removeAll(deletePacks);
        for (Pack p : deletePacks) {
            p.decreaseStock();
        }
    }
}