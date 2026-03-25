package exchange;

import user.RegisteredClient;

import java.util.Collections;
import java.util.Set;

/**
 * Class name: OfferHistory
 * <p>
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
     * Creates an offer history
     * @param offers the offers of the history
     * @param owner  the user associated to the history
     * @throws NullPointerException the offers' set or owner is null
     */
    public OfferHistory(Set<Offer> offers, RegisteredClient owner) throws NullPointerException {
        if (offers == null || owner == null) {
            throw new NullPointerException("Offers or owner is null");
        }
        this.offers = offers;
        this.owner = owner;
    }

    /**
     * Creates an empty offer history
     * @param owner the user associated to the history
     * @throws NullPointerException owner is null
     */
    public OfferHistory(RegisteredClient owner) throws NullPointerException {
        if (owner == null) {
            throw new NullPointerException("Owner is null");
        }
        this(Collections.emptySet(), owner);
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

    /*--------------------------------------------------- CHANGERS ---------------------------------------------------*/

    /*---------------------------------------------------- GETTERS ---------------------------------------------------*/

    /**
     * Obtains the offers in the history
     * @return the offers made
     */
    public Set<Offer> getOffers() {
        return Collections.unmodifiableSet(offers);
    }

    /**
     * Obtains the user associated to the research history
     * @return the owner of the history
     */
    public RegisteredClient getOwner() {
        return owner;
    }
}