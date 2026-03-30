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
     * @param date,      the date it was exchanged
     * @param exchanged, whether the exchange was done
     * @param user1,     the registered client that owns the products "products1"
     * @param products1, the products of user1
     * @param user2,     the registered client that owns the products "products2"
     * @param products2, the products of user2
     */
    public Exchange(int id, LocalDateTime date, boolean exchanged, RegisteredClient user1,
                    ArrayList<SecondHandProduct> products1, RegisteredClient user2,
                    ArrayList<SecondHandProduct> products2) {

        this.id = id;
        this.date = date;
        this.exchanged = exchanged;
        this.origin = user1;
        this.productos_propietario.put(user1, products1);
        this.destination = user2;
        this.productos_propietario.put(user2, products2);

        Store.getInstance().addExchange(this);
    }

    /**
     * Creates a new exchange
     * @param date,      the date it was exchanged
     * @param exchanged, whether the exchange was done
     * @param user1,     the registered client that owns the products "products1"
     * @param products1, the products of user1
     * @param user2,     the registered client that owns the products "products2"
     * @param products2, the products of user2
     */
    public Exchange(LocalDateTime date, boolean exchanged, RegisteredClient user1,
                    ArrayList<SecondHandProduct> products1, RegisteredClient user2,
                    ArrayList<SecondHandProduct> products2) {
        this.date = date;
        this.exchanged = exchanged;
        this.origin = user1;
        this.productos_propietario.put(user1, products1);
        this.destination = user2;
        this.productos_propietario.put(user2, products2);
        this.id = totalId;
        totalId++;
        Store.getInstance().addExchange(this);
    }

    /**
     * Creates an exchange with the date
     * @param date,      the date it will be exchanged
     * @param user1,     the registered client that owns the products "products1"
     * @param products1, the products of user1
     * @param user2,     the registered client that owns the products "products2"
     * @param products2, the products of user2
     */
    public Exchange(LocalDateTime date, RegisteredClient user1, ArrayList<SecondHandProduct> products1,
                    RegisteredClient user2, ArrayList<SecondHandProduct> products2) {
        this(date, false, user1, products1, user2, products2);
    }


    /*-------------------------------------------------------------SETTERS AND GETTERS----------------------------------------------------------*/

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

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
     * @param exchanged the exchanged to set
     */
    public void setExchanged(boolean exchanged) {
        this.exchanged = exchanged;
    }

    /**
     * Changes if the exchange was done
     * @param exchanged, the state of exchange
     */
    public void changeExchanged(boolean exchanged) {
        this.setExchanged(exchanged);
    }

    /**
     * Changes the date of the exchange
     * @param date, the date of the exchange
     */
    public void changeDate(LocalDateTime date) {
        this.setDate(date);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * The time when the exchange took place
     * @return the date, the time of the exchange
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date of the exchange
     * @param date the date to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
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