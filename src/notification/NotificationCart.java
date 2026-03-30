/**
 * 
 */
package notification;

import java.time.LocalDateTime;

import order.*;
import store.Parameter;

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
		String text = "The products/packs in your cart have expired after " + Parameter.getParam().getOrderTime() 
						+ " days, and they have been removed.\n";
		this.setText(text);
		return this.SnippetNotification(o) + this.getText() + this.signOff();
	}

	@Override
	public String SnippetNotification(Cart o) {
		String title = "Your cart has expired\n";
		this.setTitle(title);
		return this.getTitle() + this.timeLog();
	}

	
}
