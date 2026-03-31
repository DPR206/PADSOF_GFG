/**
 * 
 */
package notification;

import java.time.LocalDateTime;

import order.*;
import store.Parameter;

/**
 * It implements the cart notification
 * @author Duna P.R.
 * @version 1.3
 * @see Notification
 * @see NotificationInterface
 */
public class NotificationCart extends Notification implements NotificationInterface<Cart>{

	/**
	 * Creates a new cart notification with default timeMade (this moment) and no content
	 * 
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationCart(LocalDateTime timeReceived, boolean read, boolean visible, NotificationType type) {
		super(timeReceived, read, visible, type);
	}

	/**
	 * Creates a new cart notification with default timeMade (this moment)
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationCart(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible, NotificationType type) {
		super(title, text, timeReceived, read, visible, type);
	}

	/**
	 * Creates a new cart notification
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeMade the time it was made
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationCart(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived, boolean read,
			boolean visible, NotificationType type) {
		super(title, text, timeMade, timeReceived, read, visible, type);
	}

	@Override
	/**
	 * Creates a complete notification
	 * 
	 * @param o the cart of the user
	 * @return a string with the notification
	 */
	public String FullNotification(Cart o) {
		String text = "The products/packs in your cart have expired after " + Parameter.getParam().getOrderTime() 
						+ " days, and they have been removed.\n";
		this.setText(text);
		return this.SnippetNotification(o) + this.getText() + this.signOff();
	}

	@Override
	/**
	 * Creates a snippet of the notification (title and time log)
	 * 
	 * @param o the cart of the user
	 * @return a string with the snippet of the notification
	 */
	public String SnippetNotification(Cart o) {
		String title = "Your cart has expired\n";
		this.setTitle(title);
		return this.getTitle() + this.timeLog();
	}

	
}
