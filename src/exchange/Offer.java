package exchange;

import product.SecondHandProduct;
import user.RegisteredClient;

import java.time.LocalDate;
import java.time.Period;

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
    private SecondHandProduct[] originProducts;
    /** The receiver's products */
    private SecondHandProduct[] destinationProducts;
    /** The offer's current status */
    private OfferStatus status;

    /*------------------------------------------------- CONSTRUCTORS -------------------------------------------------*/

    /**
     * The offer's constructor
     * @param origin              the client who made the offer
     * @param destination         the client who received the offer
     * @param originProducts      the sender's products
     * @param destinationProducts the receiver's products
     * @throws NullPointerException the null pointer exception
     */
    public Offer(RegisteredClient origin, RegisteredClient destination, SecondHandProduct[] originProducts,
                 SecondHandProduct[] destinationProducts) throws NullPointerException {
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
     * Process offer exchange.
     * @return the exchange
     */
    // DUE:
    public Exchange processOffer() {
        return new Exchange();
    }

    // DUE: createNotification

    /* ------------------------------------------------- LOS CHANGES ------------------------------------------------ */

    /**
     * It allows a client to accept an incoming offer
     * @return the offer's resulting exchange
     */
    public Exchange acceptOffer() {
        this.status = OfferStatus.ACCEPTED;
        return processOffer(); // DUE
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

    /**
     * It allows a client to add products from their wallet to an offer
     * @param products the origin client's products
     * @throws NullPointerException the null pointer exception
     */
    public void chooseMyProducts(SecondHandProduct... products) throws NullPointerException {
        if (products == null) {
            throw new NullPointerException("The products weren't provided");
        }
        this.originProducts = products;
    }

    /**
     * It allows a client to add products from the offer's receiver's wallet to an offer
     * @param products the origin client's products
     * @throws NullPointerException the null pointer exception
     */
    public void chooseTheirProducts(SecondHandProduct... products) throws NullPointerException {
        if (products == null) {
            throw new NullPointerException("The products weren't provided");
        }
        this.destinationProducts = products;
    }

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

    /**
     * Gets the maximum amount of time an offer can be active for
     * @return the maximum amount of time an offer can be active for
     */
    public Period getMaxOfferPeriod() {
        return maxOfferPeriod;
    }

    /**
     * Gets the date and time when the offer was created
     * @return the date and time when the offer was created
     */
    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    /**
     * Gets the client who made the offer
     * @return the client who made the offer
     */
    public RegisteredClient getOrigin() {
        return this.origin;
    }

    /**
     * Gets the client who received the offer
     * @return the client who received the offer
     */
    public RegisteredClient getDestination() {
        return this.destination;
    }

    /**
     * Gets the sender's products
     * @return the sender's products
     */
    public SecondHandProduct[] getOriginProducts() {
        return this.originProducts;
    }

    /**
     * Gets the destination's products
     * @return the destination's products
     */
    public SecondHandProduct[] getDestinationProducts() {
        return this.destinationProducts;
    }

    /**
     * Gets the offer's offer status.
     * @return the offer's offer status.
     */
    public OfferStatus getStatus() {
        return this.status;
    }
}