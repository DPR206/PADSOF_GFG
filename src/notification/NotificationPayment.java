package notification;

import java.time.LocalDateTime;

/**
 * It implements the payment notification
 * 
 * @author Duna P.R.
 * @version 1.2
 * @see Notification
 * @see NotificationInterface
 */
public class NotificationPayment extends Notification implements NotificationInterface<String>{

	/**
	 * Creates a new payment notification with default timeMade (this moment) and no content
	 * 
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 */
	public NotificationPayment(LocalDateTime timeReceived, boolean read, boolean visible) {
		super(timeReceived, read, visible);
	}

	/**
	 * Creates a new payment notification with default timeMade (this moment)
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 */
	public NotificationPayment(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible) {
		super(title, text, timeReceived, read, visible);
	}

	/**
	 * Creates a new payment notification
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeMade the time it was made
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 */
	public NotificationPayment(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible) {
		super(title, text, timeMade, timeReceived, read, visible);
	}

	@Override
	/**
	 * Creates a complete notification
	 * 
	 * @param o the new discount
	 * @return a string with the notification
	 */
	public String FullNotification(String o) {
		String text = "Your payment for " + o + " was completed successfully. Once your purchase is ready you'll receive a new notification\n";
		this.setText(text);
		return this.SnippetNotification(o) + this.getText() + this.signOff();
	}

	@Override
	/**
	 * Creates a snippet of the notification (title and time log)
	 * 
	 * @param o the new discount
	 * @return a string with the snippet of the notification
	 */
	public String SnippetNotification(String o) {
		this.setTitle("Your payment was succesful\n");
		return this.getTitle() + this.timeLog();
	}
	
	
	
}
