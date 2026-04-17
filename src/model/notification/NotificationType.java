package model.notification;

/**
 * It defines the types of notifications
 * @author Duna P.R.
 * @version 1.4.
 */
public enum NotificationType{
	/**Notifications related to exchanges and offers*/
    EXCHANGE,
    /**Notifications related to discounts*/
    DISCOUNT,
    /**Notifications related to expired products in the cart*/
    PRODUCT_CART,
    /**Notifications related to expired packs in the cart*/
    PACK_CART,
    /**Notifications related to new second-hand products*/
    NEW_SECONDHAND_PRODUCT,
    /**Notifications related to payment*/
    PAYMENT,
    /**Notifications related to orders*/
    ORDER,
    /**Notifications related to employees who manage orders*/
    EMPLOYEE_ORDER,
    /**Notifications related to employees who manage valuations*/
    EMPLOYEE_VALUATION,
    /**Notifications related to employees who manage exchanges*/
    EMPLOYEE_EXCHANGE
}