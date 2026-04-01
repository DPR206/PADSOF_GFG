/**
 * 
 */
package notification;

import java.time.LocalDateTime;

import order.*;

/**
 * 
 */
public class NotificationEmployeeOrder extends Notification implements NotificationInterface<OrderHistory>{

	/**
	 * @param timeReceived
	 * @param read
	 * @param visible
	 * @param type
	 */
	public NotificationEmployeeOrder(LocalDateTime timeReceived, boolean read, boolean visible, NotificationType type) {
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
	public NotificationEmployeeOrder(String title, String text, LocalDateTime timeReceived, boolean read,
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
	public NotificationEmployeeOrder(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible, NotificationType type) {
		super(title, text, timeMade, timeReceived, read, visible, type);
	}

	@Override
	public String FullNotification(OrderHistory o) {
		String text = "The order of user "+ o.getOwner().getUserName() + " contains: " + o.getOrders().getLast().getP() 
						+ o.getOrders().getLast().getSp() + "\n";
		this.setText(text);
		return this.SnippetNotification(o) + text + this.signOff();
	}

	@Override
	public String SnippetNotification(OrderHistory o) {
		String title = "New order to prepare\n";
		this.setTitle(title);
		return title + this.timeLog();
	}
	
	
	
}
