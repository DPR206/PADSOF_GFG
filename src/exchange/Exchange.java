/**
 *
 */
package exchange;

import product.SecondHandProduct;
import store.Store;
import user.RegisteredClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * It implements the exchange
 * @author Duna P.R. and Ana O.R.
 * @version 1.1
 */
public class Exchange {

    /**
     * The Total id.
     */
    static public int totalId = 0;
    /** The client who made the exchange */
    private final RegisteredClient origin;
    /** The client who received the exchange */
    private final RegisteredClient destination;
    private final int id;
    private LocalDateTime date;
    private boolean exchanged;
    private HashMap<RegisteredClient, ArrayList<SecondHandProduct>> productos_propietario = new HashMap<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * An exchange's general constructor
     * @param assignedId        the id
     * @param assignedDate      the date
     * @param assignedExchanged the exchanged
     * @param assignedUser1     the user 1
     * @param assignedProducts1 the products 1
     * @param assignedUser2     the user 2
     * @param assignedProducts2 the products 2
     */
    public Exchange(int assignedId, LocalDateTime assignedDate, boolean assignedExchanged,
                    RegisteredClient assignedUser1,
                    ArrayList<SecondHandProduct> assignedProducts1, RegisteredClient assignedUser2,
                    ArrayList<SecondHandProduct> assignedProducts2) {

        this.id = assignedId;
        this.date = assignedDate;
        this.exchanged = assignedExchanged;
        this.origin = assignedUser1;
        this.productos_propietario.put(assignedUser1, assignedProducts1);
        this.destination = assignedUser2;
        this.productos_propietario.put(assignedUser2, assignedProducts2);

        Store.getInstance().addExchange(this);
    }

    /**
     * Creates a new exchange
     * @param assignedDate      the date
     * @param assignedExchanged the exchanged
     * @param assignedUser1     the user 1
     * @param assignedProducts1 the products 1
     * @param assignedProducts2     the user 2
     * @param assignedUser2 the products 2
     */
    public Exchange(LocalDateTime assignedDate, boolean assignedExchanged, RegisteredClient assignedUser1,
                    ArrayList<SecondHandProduct> assignedProducts1, RegisteredClient assignedUser2,
                    ArrayList<SecondHandProduct> assignedProducts2) {
        this.date = assignedDate;
        this.exchanged = assignedExchanged;
        this.origin = assignedUser1;
        this.productos_propietario.put(assignedUser1, assignedProducts1);
        this.destination = assignedUser2;
        this.productos_propietario.put(assignedUser2, assignedProducts2);
        this.id = totalId;
        totalId++;
        Store.getInstance().addExchange(this);
    }

    /**
     * Creates an exchange with the date
     * @param assignedDate      the date
     * @param assignedUser1     the user 1
     * @param assignedProducts1 the products 1
     * @param assignedUser2     the user 2
     * @param assignedProducts2 the products 2
     */
    public Exchange(LocalDateTime assignedDate, RegisteredClient assignedUser1,
                    ArrayList<SecondHandProduct> assignedProducts1,
                    RegisteredClient assignedUser2, ArrayList<SecondHandProduct> assignedProducts2) {
        this(assignedDate, false, assignedUser1, assignedProducts1, assignedUser2, assignedProducts2);
    }


    /*-------------------------------------------------------------SETTERS AND GETTERS----------------------------------------------------------*/

    /**
     * Obtains the total id of the exchanges
     * @return the totalId
     */
    public static int getTotalId() {
        return totalId;
    }

    /**
     * Obtains if the exchange was done
     * @return true if the exchange was done, false if not
     */
    public boolean isExchanged() {
        return exchanged;
    }

    /**
     * Sets the state of the exchange
     * @param newExchanged the exchanged to set
     */
    public void setExchanged(boolean newExchanged) {
        this.exchanged = newExchanged;
    }

    /**
     * Changes if the exchange was done
     * @param newExchanged the exchanged
     */
    public void changeExchanged(boolean newExchanged) {
        this.setExchanged(newExchanged);
        this.origin.increaseNumExchanges();
        this.destination.increaseNumExchanges();
    }

    /**
     * Changes the date of the exchange
     * @param newDate the date
     */
    public void changeDate(LocalDateTime newDate) {
        this.setDate(newDate);
    }

    /**
     * The time when the exchange took place
     * @return the date, the time of the exchange
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date of the exchange
     * @param newDate the date to set
     */
    public void setDate(LocalDateTime newDate) {
        this.date = newDate;
    }

    /*---------------------------------------------------------------METHODS--------------------------------------------------------------------*/

    /**
     * Obtains the id of a product
     * @return the id, the id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * It returns the offer's products in a save-file-friendly manner
     * @return a string containing the offer's products' id
     */
    public String getPrintProducts() {
        StringBuilder sb = new StringBuilder();

        ArrayList<SecondHandProduct> clientProducts = this.productos_propietario.get(this.origin);
        for (SecondHandProduct product : clientProducts) {
            sb.append(product.getId()).append(",");
        }

        sb.append(";");

        clientProducts = this.productos_propietario.get(this.destination);
        for (SecondHandProduct product : clientProducts) {
            sb.append(product.getId()).append(",");
        }

        return sb.toString();
    }

    /**
     * Obtains the clients and products involved in the exchange
     * @return the productos_propietario, the HashMap of the clients and their products
     */
    public HashMap<RegisteredClient, ArrayList<SecondHandProduct>> getProductos_propietario() {
        return productos_propietario;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        /* ID;DATE;EXCHANGED;ORIGIN;DESTINATION;ORIGIN_PRODS;DESTINATION_PRODS */
        return this.id + ";" + this.date + ";" + this.exchanged + ";" + this.origin + ";" + this.destination + ";" +
               this.getPrintProducts();
    }

    //Falta createNotification

}