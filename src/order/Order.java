<<<<<<< HEAD
package order;
=======
import order.OrderState;
import product.Pack;
import product.StoreProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

>>>>>>> refs/remotes/origin/main

import product.*;

import java.time.*;
import java.util.*;

/**
 * Class name: Order
 * <p>
 * Description: It implements the order paid by the client
 * @author Sofía C.L.
 * @version 1.0
 * @see RegisteredClient
 */
public class Order {
    private static int changedId = 0;

    private int id;
    private double price;
    private LocalDateTime pickedUpDate;

    private List<StoreProduct> sp = new ArrayList<>();
    private List<Pack> p = new ArrayList<>();
    private OrderState state;

    /**
     * Creates an order for when it hasn't been picked up
     * @param id,    the unique number of the order
     * @param price, total price paid of the order
     */
    public Order(int id, double price, OrderState state, List<StoreProduct> sp, List<Pack> p) {
        this.id = id;
        this.price = price;
        this.pickedUpDate = null;
        this.state = state;
        this.sp = sp;
        this.p = p;
    }

    /**
     * Creates an order for when it hasn't been picked up
     * @param price, total price paid of the order
	 */
    public Order(double price, OrderState state, List<StoreProduct> sp, List<Pack> p){
        this(Order.changedId, price, state, sp, p);
        Order.changedId++;
    }

    /**
     * Changes the state of the Order
     * @param state, the state to which we have to change it
     */
    public void changeStatus(OrderState state) {
        this.state = state;
    }

    /**
     * Sets the date of the order once it has been picked up
     * @param pickedUp, the date when the Order has been picked up
     */
    public void setPickedUpDate(LocalDateTime pickedUp) {
        this.pickedUpDate = pickedUp;
    }
}