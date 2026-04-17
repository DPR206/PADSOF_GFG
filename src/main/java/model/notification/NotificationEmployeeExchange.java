/**
 *
 */
package model.notification;

import java.time.LocalDateTime;

import model.exchange.Exchange;

/**
 * It implements the exchange notification for employees
 *
 * @author Duna P.R.
 * @version 1.1
 * @see Notification
 * @see NotificationInterface
 */
public class NotificationEmployeeExchange extends Notification implements NotificationInterface<Exchange>{

	/**
	 * Creates a new exchange notification for employees with default timeMade (this moment) and no content
	 *
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationEmployeeExchange(LocalDateTime timeReceived, boolean read, boolean visible,
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
	public NotificationEmployeeExchange(String title, String text, LocalDateTime timeReceived, boolean read,
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
	public NotificationEmployeeExchange(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible, NotificationType type) {
		super(title, text, timeMade, timeReceived, read, visible, type);
	}

	@Override
	/**
	 * Creates a complete notification
	 *
	 * @param o the new exchange
	 * @return a string with the notification
	 */
	public String FullNotification(Exchange o) {
		String text = "A new exchange has been made. It consists of: " + o.getProductos_propietario() + "\n";
		this.setText(text);
		return this.SnippetNotification(o) + text + this.signOff();
	}

	@Override
	/**
	 * Creates a snippet of the notification (title and time log)
	 *
	 * @param o the new exchange
	 * @return a string with the snippet of the notification
	 */
	public String SnippetNotification(Exchange o) {
		String title = "A new exchange has been made\n";
		this.setTitle(title);
		return title + this.timeLog();
	}


}