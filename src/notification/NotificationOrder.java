package notification;

import java.time.LocalDateTime;

import order.*;

public class NotificationOrder extends Notification implements NotificationInterface<Order>{

	/**
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationOrder(LocalDateTime timeReceived, boolean read, boolean visible) {
		super(timeReceived, read, visible);
	}

	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationOrder(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible) {
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
	public NotificationOrder(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible) {
		super(title, text, timeMade, timeReceived, read, visible);
	}

	@Override
	public String FullNotification(Order o) {
		
		return null;
	}

	@Override
	public String SnippetNotification(Order o) {
		String defaultTitle = "Your order ";
		
		return null;
	}
	
	

}
