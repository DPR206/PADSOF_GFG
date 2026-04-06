/**
 * 
 */
package notification;

import java.util.*;

import user.*;

/**
 * It implements the notification settings
 * 
 * @author Duna P.R.
 * @version 1.1
 * @see Notification
 * @see NotificationHistory
 */
public class NotificationSettings {
	
	private HashMap<NotificationType, Boolean> interests;

	/**
	 * Creates a new setting for the notifications
	 * 
	 * @param interests the interests of the user
	 */
	public NotificationSettings(HashMap<NotificationType, Boolean> interests) {
		this.interests = interests;
	}

	/**
	 * Creates a new setting
	 */
	public NotificationSettings() {
		this.interests = new HashMap<NotificationType, Boolean>(Map.of(
				NotificationType.EXCHANGE, true,
			    NotificationType.DISCOUNT, true,
			    NotificationType.PRODUCT_CART, true,
			    NotificationType.PACK_CART, true,
			    NotificationType.NEW_SECONDHAND_PRODUCT, true,
			    NotificationType.PAYMENT, true,
			    NotificationType.ORDER, true,
			    NotificationType.EMPLOYEE_EXCHANGE, false,
			    NotificationType.EMPLOYEE_ORDER, false,
			    NotificationType.EMPLOYEE_VALUATION, false
		));
	}
	
	/**
	 * Creates a new settings based on the type of user
	 * 
	 * @param owner the user associated to the settings
	 */
	public NotificationSettings(User owner) {
		
		if(owner instanceof RegisteredClient)
			this.interests = new HashMap<NotificationType, Boolean>(Map.of(
					NotificationType.EXCHANGE, true,
				    NotificationType.DISCOUNT, true,
				    NotificationType.PRODUCT_CART, true,
				    NotificationType.PACK_CART, true,
				    NotificationType.NEW_SECONDHAND_PRODUCT, true,
				    NotificationType.PAYMENT, true,
				    NotificationType.ORDER, true,
				    NotificationType.EMPLOYEE_EXCHANGE, false,
				    NotificationType.EMPLOYEE_ORDER, false,
				    NotificationType.EMPLOYEE_VALUATION, false
			));
		else if(owner instanceof Employee employee)
			switch(employee.getPerm()) {
			case Permission.EXCHANGE: 
				this.interests = new HashMap<NotificationType, Boolean>(Map.of(
						NotificationType.EXCHANGE, false,
					    NotificationType.DISCOUNT, false,
					    NotificationType.PRODUCT_CART, false,
					    NotificationType.PACK_CART, false,
					    NotificationType.NEW_SECONDHAND_PRODUCT, false,
					    NotificationType.PAYMENT, false,
					    NotificationType.ORDER, false,
					    NotificationType.EMPLOYEE_EXCHANGE, true,
					    NotificationType.EMPLOYEE_ORDER, false,
					    NotificationType.EMPLOYEE_VALUATION, true
				)); break;
			case Permission.ORDER:
				this.interests = new HashMap<NotificationType, Boolean>(Map.of(
						NotificationType.EXCHANGE, false,
					    NotificationType.DISCOUNT, false,
					    NotificationType.PRODUCT_CART, false,
					    NotificationType.PACK_CART, false,
					    NotificationType.NEW_SECONDHAND_PRODUCT, false,
					    NotificationType.PAYMENT, false,
					    NotificationType.ORDER, false,
					    NotificationType.EMPLOYEE_EXCHANGE, false,
					    NotificationType.EMPLOYEE_ORDER, true,
					    NotificationType.EMPLOYEE_VALUATION, false
				)); break;
			default:
				this.interests = new HashMap<NotificationType, Boolean>(Map.of(
						NotificationType.EXCHANGE, false,
					    NotificationType.DISCOUNT, false,
					    NotificationType.PRODUCT_CART, false,
					    NotificationType.PACK_CART, false,
					    NotificationType.NEW_SECONDHAND_PRODUCT, false,
					    NotificationType.PAYMENT, false,
					    NotificationType.ORDER, false,
					    NotificationType.EMPLOYEE_EXCHANGE, false,
					    NotificationType.EMPLOYEE_ORDER, false,
					    NotificationType.EMPLOYEE_VALUATION, false
				)); break;
			}
	}

	/**
	 * Obtains the interests in notifications
	 * 
	 * @return the interests
	 */
	public HashMap<NotificationType, Boolean> getInterests() {
		return interests;
	}

	/**
	 * Sets a new HashMap of notification interests
	 * 
	 * @param interests the interests to set
	 */
	public void setInterests(HashMap<NotificationType, Boolean> interests) {
		this.interests = interests;
	}
	
	/**
	 * Changes the interest in a specific notification type
	 * 
	 * @param type the type of notification 
	 * @param interest true if the user is interested, false if else
	 */
	public void changeInterest(NotificationType type, Boolean interest) {
		if(type != NotificationType.PAYMENT && NotificationType.ORDER != type && NotificationType.EMPLOYEE_EXCHANGE != type
		   && NotificationType.EMPLOYEE_ORDER != type && NotificationType.EMPLOYEE_VALUATION != type)
			this.interests.put(type, interest);
	}
	
}