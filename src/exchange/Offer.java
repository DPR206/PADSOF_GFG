package exchange;

import product.SecondHandProduct;
import user.RegisteredClient;

import java.time.*;
import java.util.*;

/**
 * Class name: Offer
 * <p>
 * Description: It implements the offers
 * @author Ana O.R.
 * @version 1.5
 * @see RegisteredClient
 */
public class Offer {
    /** The general id counter */
    static int totalId = 0;
    /** The maximum amount of time an offer can be active for */
    static Period maxOfferPeriod = Period.ofDays(1);
    /** The date and time when the offer was created */
    private final LocalDate creationDate;
    /** The client who made the offer */
    private final RegisteredClient origin;
    /** The client who received the offer */
    private final RegisteredClient destination;
    /** The offer's id */
    private final String id;
    /** The sender's and receiver's products */
    private HashMap<RegisteredClient, ArrayList<SecondHandProduct>> products = new HashMap<>();
    /** The offer's current status */
    private OfferStatus status;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

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

        this(LocalDate.now(), origin, destination, originProducts, destinationProducts, OfferStatus.PENDING);
    }

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

        this(origin, destination);
        this.products.put(origin, originProducts);
        this.products.put(destination, destinationProducts);
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

        this.id = String.format("%06d", ++totalId);
        this.creationDate = LocalDate.now();
        this.origin = origin;
        this.destination = destination;
        this.status = OfferStatus.PENDING;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the total id
     * @return the total id
     */
    public static int getTotalId() {
        return totalId;
    }

    /**
     * It sets the total id
     * @param newTotalId the new total id
     */
    public static void setTotalId(int newTotalId) {
        Offer.totalId = newTotalId;
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
        return processOffer();
    }

    /**
     * It processes the offer exchange
     * @return the new exchange
     */
    public Exchange processOffer() {
        return new Exchange(LocalDateTime.now(), this.origin, this.products.get(this.origin), this.destination,
                this.products.get(this.destination));
    }

    // DUE: createNotification

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
        return this.products.get(this.destination);
    }

    /**
     * It allows a client to add products from the offer's receiver's wallet to an offer
     * @param newDestinationProducts the destination client's products
     * @throws NullPointerException the products weren't provided
     */
    public void setDestinationProducts(SecondHandProduct... newDestinationProducts) throws NullPointerException {
        if (newDestinationProducts == null) {
            throw new NullPointerException("The products weren't provided");
        }

        this.products.put(this.destination,
                (ArrayList<SecondHandProduct>) Arrays.stream(newDestinationProducts).toList());
    }

    /**
     * It sets the destination client's products.
     * @param newDestinationProducts the new destination client's products
     */
    public void setDestinationProducts(ArrayList<SecondHandProduct> newDestinationProducts) {
        this.products.put(this.destination, newDestinationProducts);
    }

    /* origin is final thus has no setters */

    /**
     * It gets the offer's id
     * @return the offer's id
     */
    public String getId() {
        return id;
    }

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

    /* destination is final thus has no setters */

    /**
     * It gets the sender's products
     * @return the sender's products
     */
    public ArrayList<SecondHandProduct> getOriginProducts() {
        return this.products.get(this.origin);
    }

    /**
     * It sets the origin client's products.
     * @param newOriginProducts the new origin client's products
     */
    public void setOriginProducts(ArrayList<SecondHandProduct> newOriginProducts) {
        this.products.put(this.origin, newOriginProducts);
    }

    /**
     * It allows a client to add products from their wallet to an offer
     * @param newOriginProducts the origin client's products
     * @throws NullPointerException the products weren't provided
     */
    public void setOriginProducts(SecondHandProduct... newOriginProducts) throws NullPointerException {
        if (newOriginProducts == null) {
            throw new NullPointerException("The products weren't provided");
        }

        this.products.put(this.destination, (ArrayList<SecondHandProduct>) Arrays.stream(newOriginProducts).toList());
    }

    /**
     * It gets all the offer's products
     * @return all the offer's products
     */
    public HashMap<RegisteredClient, ArrayList<SecondHandProduct>> getProducts() {
        return products;
    }

    /**
     * It sets all the offer's products.
     * @param newProducts all the new offer's products
     */
    public void setProducts(HashMap<RegisteredClient, ArrayList<SecondHandProduct>> newProducts) {
        this.products = newProducts;
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
               ", originProducts=" + this.products.get(this.origin).toString() + ", destinationProducts=" +
               this.products.get(this.destination).toString() + ", status=" + status + '}';
    }
}