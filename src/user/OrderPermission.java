package user;

import order.Order;
import order.OrderState;

/**
 * Class name: OrderPermission
 * <p>
 * Description: It implements the permission that allows an employee to manage Orders
 * @author Ana O.R.
 * @version 1.1
 * @see Order
 */
public class OrderPermission {
    // NOTE: No tiene atributos propios

    public OrderPermission() {
    }

    /**
     * It allows an employee to change an order's status
     * @param o      the desired order
     * @param status the order's status
     */
    public void manageOrder(Order o, OrderState status) {
        o.changeStatus(status);
    }
}