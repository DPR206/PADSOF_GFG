package notification;

import java.time.LocalDateTime;

import order.*;

public class NotificationDiscount extends Notification implements NotificationInterface<Discount>{

	/**
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationDiscount(LocalDateTime timeReceived, boolean read, boolean visible) {
		super(timeReceived, read, visible);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationDiscount(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible) {
		super(title, text, timeReceived, read, visible);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param text
	 * @param timeMade
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationDiscount(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible) {
		super(title, text, timeMade, timeReceived, read, visible);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String FullNotification(Discount o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String SnippetNotification(Discount o) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
