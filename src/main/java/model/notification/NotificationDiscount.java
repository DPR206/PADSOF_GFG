package model.notification;


import java.time.LocalDateTime;

import model.discount.Discount;
import model.discount.DiscountCoverage;
import model.discount.ProductDiscount;

/**
 * It implements the discount notification
 *
 * @author Duna P.R.
 * @version 1.2
 * @see Notification
 * @see NotificationInterface
 */
public class NotificationDiscount extends Notification implements NotificationInterface<Discount>{

	/**
	 * Creates a new discount notification with default timeMade (this moment) and no content
	 *
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationDiscount(LocalDateTime timeReceived, boolean read, boolean visible, NotificationType type) {
		super(timeReceived, read, visible, type);
	}

	/**
	 * Creates a new discount notification with default timeMade (this moment)
	 *
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationDiscount(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible, NotificationType type) {
		super(title, text, timeReceived, read, visible, type);
	}

	/**
	 * Creates a new discount notification
	 *
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeMade the time it was made
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public NotificationDiscount(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible, NotificationType type) {
		super(title, text, timeMade, timeReceived, read, visible, type);
	}

	@Override
	/**
	 * Creates a complete notification
	 *
	 * @param o the new discount
	 * @return a string with the notification
	 */
	public String FullNotification(Discount o) {
		String text = "This discount starts on the " + o.getStartDate() + " and ends on the " + o.getEndDate();
		if(o.getCoverage()==DiscountCoverage.PRODUCT && ((ProductDiscount) o).getOverWholeStore())
			text += ". This discount applies to all the products in store.\n";
		else if(o.getProducts().isEmpty())
			text += ". This discount doesn't apply to a specific set of products.\n";
		else
			text += ". This discount doesn't apply to a specific set of products: " + o.getProducts() + "\n";

		this.setText(text);

		return this.SnippetNotification(o) + text + this.signOff();
	}

	@Override
	/**
	 * Creates a snippet of the notification (title and time log)
	 *
	 * @param o the new discount
	 * @return a string with the snippet of the notification
	 */
	public String SnippetNotification(Discount o) {
		String title1 = "There's a new ";
		String title2 = "model/discount\n";
		switch(o.getType()) {
		 case FIXED_PERCENTAGE: title1 += "fixed percentage"; break;
		 case GIFT: title1 += "gift"; break;
		 case QUANTITY: title1 += "quantity"; break;
		 case VOLUME: title1 += "volume"; break;
		 default: title1 = "Not valid";
		}

		title1 += title2;
		this.setTitle(title1);

		return title1 + this.timeLog();
	}


}