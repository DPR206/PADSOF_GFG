/**
 * 
 */
package notification;

import java.time.LocalDateTime;

import order.*;

/**
 * 
 */
public class NotificationCart extends Notification implements NotificationInterface<Cart>{

	/**
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationCart(LocalDateTime timeReceived, boolean read, boolean visible) {
		super(timeReceived, read, visible);
	}

	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationCart(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible) {
		super(title, text, timeReceived, read, visible);
	}

	/**
	 * @param title
	 * @param text
	 * @param timeMade
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationCart(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived, boolean read,
			boolean visible) {
		super(title, text, timeMade, timeReceived, read, visible);
	}

	@Override
	public String FullNotification(Cart o) {
		return null;
	}

	@Override
	public String SnippetNotification(Cart o) {
		return null;
	}

	
}
