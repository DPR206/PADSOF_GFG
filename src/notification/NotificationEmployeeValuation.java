/**
 * 
 */
package notification;

import java.time.LocalDateTime;

import product.*;

/**
 * It implements the valuation notification for employees
 * 
 * @author Duna P.R.
 * @version 1.1
 * @see Notification
 * @see NotificationInterface
 */
public class NotificationEmployeeValuation extends Notification implements NotificationInterface<SecondHandProduct>{

	/**
	 * Creates a new exchange notification for employees with default timeMade (this moment) and no content
	 * 
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationEmployeeValuation(LocalDateTime timeReceived, boolean read, boolean visible,
			NotificationType type) {
		super(timeReceived, read, visible, type);
	}

	/**
	 * Creates a new exchange notification for employees with default timeMade (this moment)
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationEmployeeValuation(String title, String text, LocalDateTime timeReceived, boolean read,
			boolean visible, NotificationType type) {
		super(title, text, timeReceived, read, visible, type);
	}

	/**
	 * Creates a new exchange notification for employees
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeMade the time it was made
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationEmployeeValuation(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible, NotificationType type) {
		super(title, text, timeMade, timeReceived, read, visible, type);
	}

	@Override
	/**
	 * Creates a complete notification
	 * 
	 * @param o the new second-hand product
	 * @return a string with the notification
	 */
	public String FullNotification(SecondHandProduct o) {
		String text = "The new second-hand product is: " + o.getName() + " with description: " + o.getDescription();
		this.setText(text);
		return this.SnippetNotification(o) + text + this.signOff();
	}

	@Override
	/**
	 * Creates a snippet of the notification (title and time log)
	 * 
	 * @param o the new second-hand product
	 * @return a string with the snippet of the notification
	 */
	public String SnippetNotification(SecondHandProduct o) {
		String title = "New product available for valuation\n";
		this.setTitle(title);
		return title + this.timeLog();
	}

	
}
