package notification;

import java.time.*;

import product.*;

/**
 * It implements the new second-hand product notification
 * @author Duna P.R.
 * @version 1.5
 * @see Notification
 * @see NotificationInterface
 */
public class NotificationNewSecondHand extends Notification implements NotificationInterface<SecondHandProduct>{

	/**
	 * Creates a new second-hand notification
	 * 
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationNewSecondHand(LocalDateTime timeReceived, boolean read, boolean visible, NotificationType type) {
		super(timeReceived, read, visible, type);
		
	}

	/**
	 * Creates a new second-hand notification with default timeMade (this moment)
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationNewSecondHand(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible,
									 NotificationType type) {
		super(title, text, timeReceived, read, visible, type);
		
	}

	/**
	 * Creates a new second-hand notification
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeMade the time it was made
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationNewSecondHand(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
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
	public String FullNotification(SecondHandProduct sp) {
		String text = "A new second-hand product has been uploaded: " + sp.getName() + ". With description: " + sp.getDescription() + "\n";
		return this.SnippetNotification(sp) + text + this.signOff();
	}

	@Override
	/**
	 * Creates a snippet of the notification (title and time log)
	 * 
	 * @param o the new second-hand product
	 * @return a string with the snippet of the notification
	 */
	public String SnippetNotification(SecondHandProduct sp) {
		this.setTitle("A new second-hand product has been added\n");
		return this.getTitle() + this.timeLog();
	}
	
	
}
