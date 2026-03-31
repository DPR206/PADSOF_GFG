package notification;

import java.time.*;

import product.*;

public class NotificationNewSecondHand extends Notification implements NotificationInterface<SecondHandProduct>{

	/**
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationNewSecondHand(LocalDateTime timeReceived, boolean read, boolean visible) {
		super(timeReceived, read, visible);
		
	}

	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationNewSecondHand(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible) {
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
	public NotificationNewSecondHand(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible) {
		super(title, text, timeMade, timeReceived, read, visible);
		
	}

	@Override
	public String FullNotification(SecondHandProduct sp) {
		String text = "A new second-hand product has been uploaded: " + sp.getName() + ". With description: " + sp.getDescription() + "\n";
		return this.SnippetNotification(sp) + text + this.signOff();
	}

	@Override
	public String SnippetNotification(SecondHandProduct sp) {
		this.setTitle("A new second-hand product has been added\n");
		return this.getTitle() + this.timeLog();
	}
	
	
}
