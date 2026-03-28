package user;

import order.Order;
import order.OrderState;

/**
 * Class name: OrderPermission
 * <p>
 * Description: It implements the permission that allows an employee to manage Orders
 * @author Ana O.R.
 * @version 1.3
 * @see Order
 */
public class OrderPermission {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor for an order permission
     */
    public OrderPermission() {
        // NOTE: Very complex constructor I know
    }

    /*--------------------------------------------------- MISC ----------------------------------------------------*/

    /**
     * It allows an employee to change an order's status
     * @param order  the desired order
     * @param status the order's status
     * @throws IllegalArgumentException order was null
     */
    public void manageOrder(Order order, OrderState status) throws IllegalArgumentException {
        if (order == null) {
            throw new IllegalArgumentException("Order is null");
        }

        order.setStatus(status);
    }

    // DUE: Realizar búsquedas
}