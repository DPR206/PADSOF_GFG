/**
 * 
 */
package notification;

import java.time.LocalDateTime;

import order.*;

/**
 * It implements the order notification for employees
 * 
 * @author Duna P.R.
 * @version 1.1
 * @see Notification
 * @see NotificationInterface
 */
public class NotificationEmployeeOrder extends Notification implements NotificationInterface<Order>{

	/**
	 * Creates a new order notification for employees with default timeMade (this moment) and no content
	 * 
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationEmployeeOrder(LocalDateTime timeReceived, boolean read, boolean visible, NotificationType type) {
		super(timeReceived, read, visible, type);
	}

	/**
	 * Creates a new order notification for employees with default timeMade (this moment)
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationEmployeeOrder(String title, String text, LocalDateTime timeReceived, boolean read,
			boolean visible, NotificationType type) {
		super(title, text, timeReceived, read, visible, type);
	}

	/**
	 * Creates a new order notification for employees
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeMade the time it was made
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationEmployeeOrder(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible, NotificationType type) {
		super(title, text, timeMade, timeReceived, read, visible, type);
	}

	@Override
	/**
	 * Creates a complete notification
	 * 
	 * @param o the order history
	 * @return a string with the notification
	 */
	public String FullNotification(Order o) {
		String text = "The order of user "+ o.getOwner().getUserName() + " contains: " + o.getP() 
						+ o.getSp() + "\n";
		this.setText(text);
		return this.SnippetNotification(o) + text + this.signOff();
	}

	@Override
	/**
	 * Creates a snippet of the notification (title and time log)
	 * 
	 * @param o the order history
	 * @return a string with the snippet of the notification
	 */
	public String SnippetNotification(Order o) {
		String title = "New order to prepare\n";
		this.setTitle(title);
		return title + this.timeLog();
	}
	
	
	
}
