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
	 * 
	 */
	public NotificationSettings() {
		this.interests = new HashMap();
	}
	
	
}
