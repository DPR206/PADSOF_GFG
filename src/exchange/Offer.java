package exchange;

import notification.*;
import product.SecondHandProduct;
import store.Parameter;
import store.Store;
import user.RegisteredClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * It implements the offers
 * @author Ana O.R.
 * @version 1.7
 * @see RegisteredClient
 * @see SecondHandProduct
 */
public class Offer {
    /** The global variable to determine which id should a new offer have */
    static public int totalId = -1;
    /** The offer's id */
    private final int id;
    /** The date and time when the offer was created */
    private final LocalDate creationDate;
    /** The client who made the offer */
    private final RegisteredClient origin;
    /** The client who received the offer */
    private final RegisteredClient destination;
    /** The sender's and receiver's products */
    private HashMap<RegisteredClient, ArrayList<SecondHandProduct>> products = new HashMap<>();
    /** The offer's current status */
    private OfferStatus status;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * An offer's general constructor
     * @param id                  the offer's id
     * @param creationDate        the offer's creation date
     * @param origin              the client who made the offer
     * @param destination         the client who received the offer
     * @param originProducts      the sender's products
     * @param destinationProducts the receiver's products
     * @param status              the offer's status
     * @throws NullPointerException the null pointer exception
     */
    public Offer(int id, LocalDate creationDate, RegisteredClient origin, RegisteredClient destination,
                 ArrayList<SecondHandProduct> originProducts, ArrayList<SecondHandProduct> destinationProducts,
                 OfferStatus status) throws NullPointerException {
        if (origin == null || destination == null) {
            throw new NullPointerException("The input wasn't correctly provided");
        }

        this.id = id;
        this.creationDate = creationDate;
        this.origin = origin;
        this.destination = destination;
        this.products.put(origin, originProducts);
        this.products.put(destination, destinationProducts);
        this.status = status;

        Store.getInstance().getOffers().add(this);
        this.origin.getOfferHistory().addOffer(this);
        this.destination.getOfferHistory().addOffer(this);
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
        this(++totalId, LocalDate.now(), origin, destination, originProducts, destinationProducts, OfferStatus.PENDING);
    }

    /**
     * The offer's complete constructor (without id)
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
        this(++totalId, creationDate, origin, destination, originProducts, destinationProducts, status);
    }

    /**
     * The offer's constructor when products aren't specified
     * @param origin      the client who made the offer
     * @param destination the client who received the offer
     * @throws NullPointerException the null pointer exception
     */
    public Offer(RegisteredClient origin, RegisteredClient destination) throws NullPointerException {
        this(++totalId, LocalDate.now(), origin, destination, null, null, OfferStatus.PENDING);
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
        totalId = newTotalId;
    }

    /**
     * It checks if the offer has surpassed the maximum amount of time an offer can be active for
     */
    public void checkExpiration() {
        LocalDate expirationDate = this.creationDate.plus(Parameter.getParam().getOfferTime());
        if (LocalDate.now().isAfter(expirationDate)) {
            this.status = OfferStatus.EXPIRED;
            NotificationExchange notification =
                    new NotificationExchange(LocalDateTime.now(), false, true, NotificationType.EXCHANGE);
            notification.FullNotification(this);
            this.origin.getNotificationHistory().addNotification(notification);
        }
    }

    /**
     * It allows a client to accept an incoming offer
     * @return the offer's resulting exchange
     */
    public Exchange acceptOffer() {
        if (this.status == OfferStatus.PENDING) {
            this.status = OfferStatus.ACCEPTED;
            return processOffer();
        }
        return null;
    }

    /**
     * It processes the offer exchange
     * @return the new exchange
     */
    public Exchange processOffer() {
        NotificationExchange notification =
                new NotificationExchange(LocalDateTime.now(), false, true, NotificationType.EXCHANGE,
                        Parameter.getParam().getStoreAddress(),
                        LocalDateTime.now().plus(Parameter.getParam().getExchangeTime()).withHour(17).withMinute(0)
                                     .withSecond(0).withNano(0));
        notification.FullNotification(this);
        this.origin.getNotificationHistory().addNotification(notification);
        this.destination.getNotificationHistory().addNotification(notification);

        NotificationEmployeeExchange notification2 =
                new NotificationEmployeeExchange(LocalDateTime.now(), false, true, NotificationType.EMPLOYEE_EXCHANGE);
        Exchange newExchange =
                new Exchange(LocalDateTime.now(), this.origin, this.products.get(this.origin), this.destination,
                        this.products.get(this.destination));
        notification2.FullNotification(newExchange);
        Store.getInstance().sendNotificationEmployees(notification2);

        return newExchange;
    }

    /**
     * It allows a client to reject an incoming offer
     */
    public void rejectOffer() {
        if (this.status == OfferStatus.PENDING) {
            this.status = OfferStatus.REJECTED;
            NotificationExchange notification =
                    new NotificationExchange(LocalDateTime.now(), false, true, NotificationType.EXCHANGE);
            notification.FullNotification(this);
            this.origin.getNotificationHistory().addNotification(notification);
        }
    }

    /**
     * It allows a client to cancel an offer they made
     */
    public void cancelOffer() {
        if (this.status == OfferStatus.PENDING) {
            this.status = OfferStatus.CANCELED;
        }
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

    /**
     * It gets the offer's id
     * @return the offer's id
     */
    public int getId() {
        return this.id;
    }

    /* origin is final thus has no setters */

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
        return this.products.get(this.origin);
    }

    /* destination is final thus has no setters */

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
     * It returns the offer's products in a save-file-friendly manner
     * @return a string containing the offer's products' id
     */
    public String getPrintProducts() {
        StringBuilder sb = new StringBuilder();

        ArrayList<SecondHandProduct> clientProducts = this.products.get(this.origin);
        for (SecondHandProduct product : clientProducts) {
            sb.append(product.getId()).append(",");
        }

        sb.append(";");

        clientProducts = this.products.get(this.destination);
        for (SecondHandProduct product : clientProducts) {
            sb.append(product.getId()).append(",");
        }

        return sb.toString();
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
        /* ID;CREATION_DATE;ORIGIN;DESTINATION;ORIGIN_PRODUCTS;DESTINATION_PRODUCTS;STATUS */
        return this.id + ";" + this.creationDate + ";" + this.origin + ";" + this.destination + ";" +
               this.getPrintProducts() + ";" + this.status;
    }
}