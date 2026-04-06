package exchange;

import user.RegisteredClient;

import java.util.*;

/**
 * Description: It implements the offer history
 * @author Duna P.R. and Ana O.R.
 * @version 1.0
 * @see Offer
 */
public class OfferHistory {
    /** The offer's owner */
    private final RegisteredClient owner;
    /** The offer history's offers */
    private Set<Offer> offers;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * It creates an empty offer history
     * @param owner the user associated to the history
     * @throws NullPointerException the owner is null
     */
    public OfferHistory(RegisteredClient owner) throws NullPointerException {
        if (owner == null) {
            throw new NullPointerException("Owner is null");
        }

        this(new HashSet<>(), owner);
    }

    /**
     * The offer history's complete constructor
     * @param offers the offers of the history
     * @param owner  the user associated to the history
     * @throws NullPointerException the owner is null
     */
    public OfferHistory(Set<Offer> offers, RegisteredClient owner) throws NullPointerException {
        if (owner == null) {
            throw new NullPointerException("Owner is null");
        }

        this.offers = offers;
        this.owner = owner;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Adds a new offer to the history
     * @param newOffer the new offer made
     * @throws NullPointerException the new offer is null
     */
    public void addOffer(Offer newOffer) throws NullPointerException {
        if (newOffer == null) {
            throw new NullPointerException("The new offer is null");
        }

        offers.add(newOffer);
    }

    /**
     * Add multiple new offers to the history
     * @param newOffers the new offers
     * @throws NullPointerException The new offers' set is null
     */
    public void addOffers(Set<Offer> newOffers) throws NullPointerException {
        if (newOffers == null) {
            throw new NullPointerException("The new offers is null");
        }

        offers.addAll(newOffers);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Obtains the offers in the history
     * @return the offers made
     */
    public Set<Offer> getOffers() {
        return Collections.unmodifiableSet(offers);
    }

    /* owner is final thus has no setter */

    /**
     * It sets a new set of offers
     * @param newOffers the new offers
     * @throws NullPointerException the new offers is null
     */
    public void setOffers(Set<Offer> newOffers) throws NullPointerException {
        if (newOffers == null) {
            throw new NullPointerException("The new offers is null");
        }

        this.offers = newOffers;
    }

    /**
     * Obtains the user associated to the research history
     * @return the owner of the history
     */
    public RegisteredClient getOwner() {
        return owner;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    @Override
    public String toString() {
        // DUE
        return "OfferHistory{" + "owner=" + owner + ", offers=" + offers + '}';
    }
}