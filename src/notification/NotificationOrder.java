package notification;

import java.time.LocalDateTime;

import order.*;

public class NotificationOrder extends Notification implements NotificationInterface<Order>{

	static int pickupCode = 0;
	
	/**
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationOrder(LocalDateTime timeReceived, boolean read, boolean visible) {
		super(timeReceived, read, visible);
	}

	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationOrder(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible) {
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
	public NotificationOrder(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible) {
		super(title, text, timeMade, timeReceived, read, visible);
	}

	@Override
	public String FullNotification(Order o) {
		String text = "";
		switch(o.getState()) {
		 case OrderState.PAID: text += "Your order with price " + o.getPrice() + "has been paid successfully. "
		 								+ "Once your order is ready you will a receive a notification.\n"; break;
		 case OrderState.READY_TO_PICKUP: text += "Your order is ready to pickup. Your pikcup code is: " + NotificationOrder.pickupCode;
		 									pickupCode++;
		 									break;
		 default: text = "Not valid\n";
		}
		return this.SnippetNotification(o) + text + this.signOff();
	}

	@Override
	public String SnippetNotification(Order o) {
		String title = "Your order ";
		switch(o.getState()) {
			case OrderState.PAID: title += "has been paid\n"; break;
			case OrderState.READY_TO_PICKUP: title += "is ready to pickup\n"; break;
			default: title = "Not valid\n";
		}
		this.setTitle(title);
		return this.getTitle() + this.timeLog();
	}
	
	

}
