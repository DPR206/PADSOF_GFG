/**
 * 
 */
package notification;

import java.time.*;
import java.time.format.DateTimeFormatter;

import exchange.*;
import store.Parameter;
import user.RegisteredClient;

/**
 * 
 */
public class NotificationExchange extends Notification implements NotificationInterface<Offer> {
	
	private String address;
	private LocalDateTime timeAndDate;
	
	
	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 * @param address
	 * @param timeAndDate
	 */
	public NotificationExchange(String title, String text, LocalDateTime timeReceived, 
			boolean read, String address, LocalDateTime timeAndDate) {
		super(title, text, timeReceived, read);
		this.address = address;
		this.timeAndDate = timeAndDate;
	}

	/**
	 * @param title
	 * @param text
	 * @param timeMade
	 * @param timeReceived
	 * @param read
	 * @param address
	 * @param timeAndDate
	 */
	public NotificationExchange(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, String address, LocalDateTime timeAndDate) {
		super(title, text, timeMade, timeReceived, read);
		this.address = address;
		this.timeAndDate = timeAndDate;
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
		this.address = null;
		this.timeAndDate = null;
	}

	@Override
	public String FullNotification(RegisteredClient user, Offer o) {
		String text = "Tu oferta para intercambiar " + o.getOriginProducts() + "por " + o.getDestinationProducts();
		switch(o.getStatus()) {
			case OfferStatus.ACCEPTED: text += " has been accepted. " + this.timeAndPlace(); break;
			case OfferStatus.REJECTED: text += " has been rejected. You can make another offer or look for another exchange"; break;
			case OfferStatus.EXPIRED: text += " has expired after " + Parameter.getParam().getOfferTime() 
												+ " have passed since its publication. You can make the same offer or a new one altogether"; 
												break;
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
	
	private String timeAndPlace() {
		return "The exchange will take place at "+this.address 
				+ " with date and time: "+this.timeAndDate.format(DateTimeFormatter.ofPattern("dd/MM HH:mm"))
				+"\n";
	}
}
