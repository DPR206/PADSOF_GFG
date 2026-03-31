package notification;

import java.time.LocalDateTime;

import order.*;

public class NotificationDiscount extends Notification implements NotificationInterface<Discount>{

	/**
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationDiscount(LocalDateTime timeReceived, boolean read, boolean visible) {
		super(timeReceived, read, visible);
	}

	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationDiscount(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible) {
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
	public NotificationDiscount(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible) {
		super(title, text, timeMade, timeReceived, read, visible);
	}

	@Override
	public String FullNotification(Discount o) {
		String text = "This discount starts on the " + o.getStartDate() + " and ends on the " + o.getEndDate();
		if(o.getOverWholeStore())
			text += ". This discount applies to all the products in store.\n";
		else if(o.getProducts().isEmpty())
			text += ". This discount doesn't apply to a specific set of products.\n";
		else
			text += o.getProducts();
		
		this.setText(text);
		
		return this.SnippetNotification(o) + text + this.signOff();
	}

	@Override
	public String SnippetNotification(Discount o) {
		String title1 = "There's a new ";
		String title2 = " discount\n";
		switch(o.getType()) {
		 case DiscountType.FIXED_PERCENTAGE: title1 += "fixed percentage"; break;
		 case DiscountType.GIFT: title1 += "gift"; break;
		 case DiscountType.QUANTITY: title1 += "quantity"; break;
		 case DiscountType.VOLUME: title1 += "volume"; break;
		 default: title1 = "Not valid";
		}
		
		title1 += title2;
		this.setTitle(title1);
		
		return title1 + this.timeLog();
	}

	
}
