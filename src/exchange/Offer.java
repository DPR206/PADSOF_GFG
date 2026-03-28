package exchange;

import product.SecondHandProduct;
import user.RegisteredClient;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class name: Offer
 * <p>
 * Description: It implements the offers
 * @author Ana O.R.
 * @version 1.2
 * @see RegisteredClient
 */
public class Offer {
    /** The maximum amount of time an offer can be active for */
    static Period maxOfferPeriod = Period.ofDays(1);
    /** The date and time when the offer was created */
    private final LocalDate creationDate;
    /** The client who made the offer */
    private final RegisteredClient origin;
    /** The client who received the offer */
    private final RegisteredClient destination;
    /** The sender's products */
    private ArrayList<SecondHandProduct> originProducts;
    /** The receiver's products */
    private ArrayList<SecondHandProduct> destinationProducts;
    /** The offer's current status */
    private OfferStatus status;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The offer's complete constructor
     * @param creationDate        the offer's creation date
     * @param origin              the client who made the offer
     * @param destination         the client who received the offer
     * @param originProducts      the sender's products
     * @param destinationProducts the receiver's products
     * @param status              the offer's status
     * @throws NullPointerException the null pointer exception
     */
    public Offer(LocalDate creationDate, RegisteredClient origin, RegisteredClient destination,
                 ArrayList<SecondHandProduct> originProducts, ArrayList<SecondHandProduct> destinationProducts,
                 OfferStatus status) throws NullPointerException {
        if (origin == null || destination == null || originProducts == null || destinationProducts == null) {
            throw new NullPointerException("The input wasn't correctly provided");
        }
        this.creationDate = creationDate;
        this.origin = origin;
        this.destination = destination;
        this.originProducts = originProducts;
        this.destinationProducts = destinationProducts;
        this.status = status;
    }

    /**
     * The offer's constructor when products are specified
     * @param origin              the client who made the offer
     * @param destination         the client who received the offer
     * @param originProducts      the sender's products
     * @param destinationProducts the receiver's products
     * @throws NullPointerException the null pointer exception
     */
    public Offer(RegisteredClient origin, RegisteredClient destination, ArrayList<SecondHandProduct> originProducts,
                 ArrayList<SecondHandProduct> destinationProducts) throws NullPointerException {
        if (origin == null || destination == null || originProducts == null || destinationProducts == null) {
            throw new NullPointerException("The input wasn't correctly provided");
        }
        this.creationDate = LocalDate.now();
        this.origin = origin;
        this.destination = destination;
        this.originProducts = originProducts;
        this.destinationProducts = destinationProducts;
        this.status = OfferStatus.PENDING;
    }

    /**
     * The offer's constructor when products aren't specified
     * @param origin      the client who made the offer
     * @param destination the client who received the offer
     * @throws NullPointerException the null pointer exception
     */
    public Offer(RegisteredClient origin, RegisteredClient destination) throws NullPointerException {
        if (origin == null || destination == null) {
            throw new NullPointerException("The input wasn't correctly provided");
        }
        this.creationDate = LocalDate.now();
        this.origin = origin;
        this.destination = destination;
        this.status = OfferStatus.PENDING;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It checks if the offer has surpassed the maximum amount of time an offer can be active for
     */
    public void checkExpiration() {
        LocalDate expirationDate = this.creationDate.plus(maxOfferPeriod);
        if (LocalDate.now().isAfter(expirationDate)) {
            this.status = OfferStatus.EXPIRED;
        }
    }

    /**
     * It allows a client to accept an incoming offer
     * @return the offer's resulting exchange
     */
    public Exchange acceptOffer() {
        this.status = OfferStatus.ACCEPTED;
        return processOffer();
    }

    /**
     * It processes the offer exchange
     * @return the new exchange
     */
    public Exchange processOffer() {
        return new Exchange(null); // DUE: Arreglar llamada a constructor
    }

    /**
     * It allows a client to reject an incoming offer
     */
    public void rejectOffer() {
        this.status = OfferStatus.REJECTED;
    }

    /**
     * It allows a client to cancel an offer they made
     */
    public void cancelOffer() {
        this.status = OfferStatus.CANCELED;
    }

    // DUE: createNotification

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the date and time when the offer was created
     * @return the date and time when the offer was created
     */
    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    /**
     * It gets the client who received the offer
     * @return the client who received the offer
     */
    public RegisteredClient getDestination() {
        return this.destination;
    }

    /**
     * It gets the destination's products
     * @return the destination's products
     */
    public ArrayList<SecondHandProduct> getDestinationProducts() {
        return this.destinationProducts;
    }

    /* origin is final thus has no setters */

    /**
     * It allows a client to add products from the offer's receiver's wallet to an offer
     * @param newProducts the origin client's products
     * @throws NullPointerException the products weren't provided
     */
    public void setDestinationProducts(SecondHandProduct... newProducts) throws NullPointerException {
        if (newProducts == null) {
            throw new NullPointerException("The products weren't provided");
        }

        Collections.addAll(this.destinationProducts, newProducts);
    }

    /* destination is final thus has no setters */

    /**
     * It gets the maximum amount of time an offer can be active for
     * @return the maximum amount of time an offer can be active for
     */
    public Period getMaxOfferPeriod() {
        return maxOfferPeriod;
    }

    /**
     * It sets the maximum amount of time an offer can be active for
     * @param newMaxOfferPeriod the new maximum amount of time an offer can be active for
     * @throws NullPointerException the new period was null
     */
    public static void setMaxOfferPeriod(Period newMaxOfferPeriod) throws NullPointerException {
        if (newMaxOfferPeriod == null) {
            throw new NullPointerException("The new period was null");
        }

        Offer.maxOfferPeriod = newMaxOfferPeriod;
    }

    /**
     * It gets the client who made the offer
     * @return the client who made the offer
     */
    public RegisteredClient getOrigin() {
        return this.origin;
    }

    /**
     * It gets the sender's products
     * @return the sender's products
     */
    public ArrayList<SecondHandProduct> getOriginProducts() {
        return this.originProducts;
    }

    /**
     * It allows a client to add products from their wallet to an offer
     * @param newProducts the origin client's products
     * @throws NullPointerException the products weren't provided
     */
    public void setOriginProducts(SecondHandProduct... newProducts) throws NullPointerException {
        if (newProducts == null) {
            throw new NullPointerException("The products weren't provided");
        }

        Collections.addAll(this.originProducts, newProducts);
    }

    /**
     * It gets the offer's offer status.
     * @return the offer's offer status.
     */
    public OfferStatus getStatus() {
        return this.status;
    }

    /**
     * It sets the offer's status
     * @param newStatus the new offer's status
     */
    public void setStatus(OfferStatus newStatus) {
        this.status = newStatus;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    @Override
    public String toString() {
        // DUE
        return "Offer{" + "creationDate=" + creationDate + ", origin=" + origin + ", destination=" + destination +
               ", originProducts=" + originProducts + ", destinationProducts=" + destinationProducts + ", status=" +
               status + '}';
    }
}