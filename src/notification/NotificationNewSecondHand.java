package notification;

import java.time.*;

import user.RegisteredClient;
import product.*;

public class NotificationNewSecondHand extends Notification implements NotificationInterface<SecondHandProduct>{

	/**
	 * @param timeReceived
	 * @param read
	 */
	public NotificationNewSecondHand(LocalDateTime timeReceived, boolean read) {
		super(timeReceived, read);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 */
	public NotificationNewSecondHand(String title, String text, LocalDateTime timeReceived, boolean read) {
		super(title, text, timeReceived, read);
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated constructor stub
	}

	@Override
	public String FullNotification(RegisteredClient user, SecondHandProduct sp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String SnippetNotification(RegisteredClient user, SecondHandProduct sp) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
