/**
 * 
 */
package notification;

import java.util.*;

/**
 * 
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
			    NotificationType.CART, true,
			    NotificationType.NEW_SECONDHAND_PRODUCT, true,
			    NotificationType.PAYMENT, true,
			    NotificationType.ORDER, true
		));
	}

	/**
	 * @return the interests
	 */
	public HashMap<NotificationType, Boolean> getInterests() {
		return interests;
	}

	/**
	 * @param interests the interests to set
	 */
	public void setInterests(HashMap<NotificationType, Boolean> interests) {
		this.interests = interests;
	}
	
	
}
