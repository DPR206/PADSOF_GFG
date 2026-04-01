/**
 * 
 */
package notification;

import java.time.LocalDateTime;

import product.*;

/**
 * It implements the valuation notification for employees
 * 
 * @author Duna P.R.
 * @version 1.1
 * @see Notification
 * @see NotificationInterface
 */
public class NotificationEmployeeValuation extends Notification implements NotificationInterface<SecondHandProduct>{

	/**
	 * @param timeReceived
	 * @param read
	 * @param visible
	 * @param type
	 */
	public NotificationEmployeeValuation(LocalDateTime timeReceived, boolean read, boolean visible,
			NotificationType type) {
		super(timeReceived, read, visible, type);
	}

	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 * @param visible
	 * @param type
	 */
	public NotificationEmployeeValuation(String title, String text, LocalDateTime timeReceived, boolean read,
			boolean visible, NotificationType type) {
		super(title, text, timeReceived, read, visible, type);
	}

	/**
	 * @param title
	 * @param text
	 * @param timeMade
	 * @param timeReceived
	 * @param read
	 * @param visible
	 * @param type
	 */
	public NotificationEmployeeValuation(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible, NotificationType type) {
		super(title, text, timeMade, timeReceived, read, visible, type);
	}

	@Override
	public String FullNotification(SecondHandProduct o) {
		return null;
	}

	@Override
	public String SnippetNotification(SecondHandProduct o) {
		return null;
	}

	
}
