/**
 * 
 */
package notification;

import java.time.*;
import exchange.*;
import user.RegisteredClient;

/**
 * 
 */
public class NotificationExchange extends Notification implements NotificationInterface<Offer> {
	
	
	
	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 */
	public NotificationExchange(String title, String text, LocalDateTime timeReceived, boolean read) {
		super(title, text, timeReceived, read);
	}

	/**
	 * @param title
	 * @param text
	 * @param timeMade
	 * @param timeReceived
	 * @param read
	 */
	public NotificationExchange(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read) {
		super(title, text, timeMade, timeReceived, read);
	}

	@Override
	public String FullNotification(RegisteredClient user, Offer o) {
		String text = "Tu oferta para intercambiar " + o.getOriginProducts() + "por " + o.getDestinationProducts();
		switch(o.getStatus()) {
			case OfferStatus.ACCEPTED: text += "has been accepted. " + ; break;
			case OfferStatus.REJECTED: this.setTitle("Your offer has been rejected\n"); break;
			case OfferStatus.EXPIRED: this.setTitle("Your offer has expired\n"); break;
			default: this.setTitle("Not valid\n");
		}
		
		this.setText(text);
		return this.SnippetNotification(user, o) + text +  this.signOff();
	}
	
	@Override
	public String SnippetNotification(RegisteredClient user, Offer o) {
		switch(o.getStatus()) {
			case OfferStatus.ACCEPTED: this.setTitle("Your offer has been accepted\n"); break;
			case OfferStatus.REJECTED: this.setTitle("Your offer has been rejected\n"); break;
			case OfferStatus.EXPIRED: this.setTitle("Your offer has expired\n"); break;
			default: this.setTitle("Not valid\n");
		}
		
		return this.getTitle() + this.timeLog();
	}
	
	public String timeAndPlace(String address, LocalDateTime timeAndTime) {
		
	}
}
