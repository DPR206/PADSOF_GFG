// NOTA: Revisar esto
/**
 * Classname: OrderPermission
 * Description: It implements the permission that allows an employee to manage Orders
 *
 * @author Ana O.R.
 * @version 1.0
 *
 * @see Order
 *
 * Copyright??
 */
public class OrderPermission {
    // NOTA: No tiene atributos propios
    /**
     * It allows an employee to change an order's status
     * @param o the desired order
     * @param status the order's status
     */
    void manageOrder(Order o, OrderState status) {
        o.status = status;
    }
}