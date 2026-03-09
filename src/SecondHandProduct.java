/**
 * Classname: SecondHandProduct Description: It implements the second hand products
 * @author Ana O.R.
 * @version 1.0
 * <p>
 * Copyright??
 */

import java.time.LocalDate; // ?: https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html

public class SecondHandProduct extends Product {
    private LocalDate valuationDate; /* Date when the product was valuated */
    private boolean paidValuation; /* Whether the valuation fee for this product has been paid */
    private boolean valuated; /* Whether the product was valuated */
    private boolean available; /* Whether the product is available to be exchanged or not (false if it hasn't been
    valuated yet), if it isn't it can only be viewed by its owner and, if it exists, the desntiny client of an ongoing
    offer */
    private RegisteredClient owner; /* The product's owner */
    private RegisteredClient receiver; /* The possible product's future owner */
    private double estimatedPrice; /* The estimated price set by the original owner */
    private ConservationStatus status; /* The product's conservation status */

    /**
     * SecondHandProduct's constructor
     * @param id             the product's id
     * @param name           the product's name
     * @param description    the product's description
     * @param photo          the product's photo's path
     * @param type           the product's product type
     * @param owner          the product's owner
     * @param estimatedPrice the product's estimated price set by the original owner
     * @param status         the product's conservation status
     */
    SecondHandProduct(int id, String name, String description, String photo, ProductType type, RegisteredClient owner,
                      double estimatedPrice, ConservationStatus status) {
        super( int id, String name, String description, String photo, ProductType type);
        this.valuationDate = null; // NOTE: valuationDate será null hasta que el producto se valore
        this.paidValuation = false;
        this.available = false;
        this.owner = owner;
        this.receiver = null; // NOTE: receiver será null hasta que se haga una oferta
        this.estimatedPrice = estimatedPrice;
        this.status = status;
    }

    /**
     * It checks whether the product's valuation fee has been paid
     */
    void valuationWasPaid() {
        this.paidValuation = true;
    }

    /**
     * It allows a store employee to valuate the product
     * @param valuation the product's price set by the employee
     */
    void valuate(double valuation) {
        super.changePrice(valuation); // TODO: Revisar si esto es legal
    }

    /**
     * It makes the product only visible to those involved in the product's offer
     * @param receiver the offer's receiver
     */
    void makeOffer(RegisteredClient receiver) {
        this.receiver = receiver;
        this.available = false; // NOTE: Bloqueo de stock
    }

    /**
     * It resets the product's visibility to that prior to any offers
     */
    void cancelOffer() {
        this.receiver = null;
        this.available = true;
    }

    /**
     * It allows the product to be exchanged
     */
    void exchange() {
        this.owner = this.receiver;
        this.receiver = null;
        this.available = true;
    }

    // TODO: Change -> int id
    // TODO: Change -> String name
    // TODO: Change -> String description
    // TODO: Change -> String photo
    // TODO: Change -> ProductType type
    // TODO: Change -> RegisteredClient owner
    // TODO: Change -> double estimatedPrice

    /**
     * It allows a store employee, or the manager, to change the product's conservation status
     * @param newStatus the product's new status
     */
    void changeConservationStatus(ConservationStatus newStatus) {
        this.status = newStatus;
    }
    // TODO: Change -> valuationDate

    /**
     * It changes the product's availability and sets its valuationDate if it's available
     * @param newAvailability the product's new availability
     */
    void changeAvailability(boolean newAvailability) {
        this.available = newAvailability;
        if (newAvailability == true) {
            this.valuationDate = now();
        }
    }
    // TODO: Change -> owner
    // TODO: Change -> receiver
    // TODO: Change -> estimatedPrice
    // TODO: Change -> status
}