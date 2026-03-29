package notification;

import java.time.*;

import product.*;

public class NotificationNewSecondHand extends Notification implements NotificationInterface<SecondHandProduct>{

	/**
	 * @param timeReceived
	 * @param read
	 */
	public NotificationNewSecondHand(LocalDateTime timeReceived, boolean read) {
		super(timeReceived, read);
		
	}

	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 */
	public NotificationNewSecondHand(String title, String text, LocalDateTime timeReceived, boolean read) {
		super(title, text, timeReceived, read);
		
	}

	/**
	 * @param title
	 * @param text
	 * @param timeMade
	 * @param timeReceived
	 * @param read
	 */
	public NotificationNewSecondHand(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read) {
		super(title, text, timeMade, timeReceived, read);
		
	}

	@Override
	public String FullNotification(SecondHandProduct sp) {
		String text = "Se ha subido un nuevo producto de segunda mano: " + sp.getName();
		return this.SnippetNotification(sp) + text + this.signOff();
	}

	@Override
	public String SnippetNotification(SecondHandProduct sp) {
		this.setTitle("Se ha añadido un nuevo producto de segunda mano\n");
		return this.getTitle() + this.timeLog();
	}
	
	
}
