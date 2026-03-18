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
 * @version 1.1
 * @see RegisteredClient
 */
public class Offer {
    /**
     * The constant maxOfferPeriod.
     */
    /* The maximum amount of time an offer can be active for */
    static Period maxOfferPeriod;
    /* The date and time when the offer was created */
    private final LocalDate creationDate;
    /* The client who made the offer */
    private final RegisteredClient origin;
    /* The client who received the offer */
    private final RegisteredClient destination;
    /* The sender's products */
    private SecondHandProduct[] originProducts;
    /* The receiver's products */
    private SecondHandProduct[] destinationProducts;
    /* The offer's current status */
    private OfferStatus status;

    static {
        maxOfferPeriod = Period.ofDays(30); // REVIEW: Es definido y sustituido por el gestor al principio
    }

    /**
     * The offer's constructor
     * @param origin              the client who made the offer
     * @param destination         the client who received the offer
     * @param originProducts      the sender's products
     * @param destinationProducts the receiver's products
     */
    Offer(RegisteredClient origin, RegisteredClient destination, SecondHandProduct[] originProducts,
          SecondHandProduct[] destinationProducts) {
        this.creationDate = LocalDate.now();
        this.origin = origin;
        this.destination = destination;
        this.originProducts = originProducts;
        this.destinationProducts = destinationProducts;
        this.status = OfferStatus.PENDING;
    }

    /**
     * It allows e client to make offers
     * @param origin              the client who made the offer
     * @param destination         the client who received the offer
     * @param originProducts      the sender's products
     * @param destinationProducts the receiver's products
     * @return the new offer
     * @throws NullPointerException the null pointer exception
     */
    public Offer createOffer(RegisteredClient origin, RegisteredClient destination, SecondHandProduct[] originProducts,
                             SecondHandProduct[] destinationProducts) throws NullPointerException {
        if (origin == null || destination == null || originProducts == null || destinationProducts == null) {
            throw new NullPointerException("The input wasn't correctly provided");
        }
        return new Offer(origin, destination, originProducts, destinationProducts);
    }

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

    /**
     * Process offer exchange.
     * @return the exchange
     */
// DUE:
    public Exchange processOffer() {
        return new Exchange();
    }

    // DUE: createNotification
}