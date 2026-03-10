/**
 * Classname: OrderPermission
 * <p>
 * Description: It implements the permission that allows an employee to manage Orders
 * @author Ana O.R.
 * @version 1.0
 * @see Order
 * <p>
 * Copyright??
 */
public class OrderPermission {
    // NOTE: No tiene atributos propios

    /**
     * It allows an employee to change an order's status
     * @param o      the desired order
     * @param status the order's status
     */
    public void manageOrder(Order o, OrderState status) {
        o.changeStatus(status);
    }
}