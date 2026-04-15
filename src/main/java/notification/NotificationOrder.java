package notification;

import order.Order;

import java.time.LocalDateTime;

/**
 * It implements the order notification
 *
 * @author Duna P.R.
 * @version 1.2
 * @see Notification
 * @see NotificationInterface
 */
public class NotificationOrder extends Notification implements NotificationInterface<Order>{

	static int pickupCode = 0;

	/**
	 * Creates a new order notification with default timeMade (this moment) and no content
	 *
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationOrder(LocalDateTime timeReceived, boolean read, boolean visible, NotificationType type) {
		super(timeReceived, read, visible, type);
	}

	/**
	 * Creates a new order notification with default timeMade (this moment)
	 *
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationOrder(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible, NotificationType type) {
		super(title, text, timeReceived, read, visible, type);
	}

	/**
	 * Creates a new order notification
	 *
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeMade the time it was made
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationOrder(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible, NotificationType type) {
		super(title, text, timeMade, timeReceived, read, visible, type);
	}

	@Override
	/**
	 * Creates a complete notification
	 *
	 * @param o the new order
	 * @return a string with the notification
	 */
	public String FullNotification(Order o) {
		String text = "";
		switch(o.getState()) {
		 case PAID: text += "Your order with price " + o.getPrice() + "has been paid successfully. "
		 								+ "Once your order is ready you will a receive a notification.\n"; break;
		 case READY_TO_PICKUP: text += "Your order is ready to pickup. Your pickup code is: " + NotificationOrder.pickupCode
				 						+ ". Don't forget to bring your dni as well.\n";
		 									pickupCode++;
		 									break;
		 default: text = "Not valid\n";
		}
		return this.SnippetNotification(o) + text + this.signOff();
	}

	@Override
	/**
	 * Creates a snippet of the notification (title and time log)
	 *
	 * @param o the new order
	 * @return a string with the snippet of the notification
	 */
	public String SnippetNotification(Order o) {
		String title = "Your order ";
		switch(o.getState()) {
			case PAID: title += "has been paid\n"; break;
			case READY_TO_PICKUP: title += "is ready to pickup\n"; break;
			default: title = "Not valid\n";
		}
		this.setTitle(title);
		return this.getTitle() + this.timeLog();
	}



}