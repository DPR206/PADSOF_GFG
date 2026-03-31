package notification;

import java.time.LocalDateTime;

public class NotificationPayment extends Notification implements NotificationInterface<String>{

	/**
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationPayment(LocalDateTime timeReceived, boolean read, boolean visible) {
		super(timeReceived, read, visible);
	}

	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationPayment(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible) {
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
	public NotificationPayment(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible) {
		super(title, text, timeMade, timeReceived, read, visible);
	}

	@Override
	public String FullNotification(String o) {
		String text = "Your payment for " + o + " was completed successfully. Once your purchase is ready you'll receive a new notification\n";
		this.setText(text);
		return this.SnippetNotification(o) + this.getText() + this.signOff();
	}

	@Override
	public String SnippetNotification(String o) {
		this.setTitle("Your payment was succesful\n");
		return this.getTitle() + this.timeLog();
	}
	
	
	
}
