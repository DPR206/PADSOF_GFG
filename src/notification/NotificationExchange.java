/**
 * 
 */
package notification;

import java.time.*;
import java.time.format.DateTimeFormatter;

import exchange.*;
import store.Parameter;

/**
 * It implements the exchange notification
 * @author Duna P.R.
 * @version 1.5
 * @see Notification
 * @see NotificationInterface
 */
public class NotificationExchange extends Notification implements NotificationInterface<Offer> {
	
	private String address;
	private LocalDateTime timeAndDate;
	
	
	/**
	 * Creates a new exchange notification with default timeMade (this moment)
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param address the address of the place the exchange will take place at
	 * @param timeAndDate the time and date of the exchange
	 */
	public NotificationExchange(String title, String text, LocalDateTime timeReceived, 
			boolean read, boolean visible, String address, LocalDateTime timeAndDate) {
		super(title, text, timeReceived, read, visible);
		this.address = address;
		this.timeAndDate = timeAndDate;
	}

	/**
	 * Creates a new exchange notification
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeMade the time it was made
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param address the address of the place the exchange will take place at
	 * @param timeAndDate the time and date of the exchange
	 */
	public NotificationExchange(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible, String address, LocalDateTime timeAndDate) {
		super(title, text, timeMade, timeReceived, read, visible);
		this.address = address;
		this.timeAndDate = timeAndDate;
	}
	
	/**
	 * Creates a new exchange notification with no address or timeAndDate
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeMade the time it was made
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 */
	public NotificationExchange(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible) {
		super(title, text, timeMade, timeReceived, read, visible);
		this.address = null;
		this.timeAndDate = null;
	}
	
	

	/**
	 * Creates a new exchange notification without address or timeAndDate
	 * 
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 */
	public NotificationExchange(LocalDateTime timeReceived, boolean read, boolean visible) {
		super(timeReceived, read, visible);
		this.address = null;
		this.timeAndDate = null;
	}

	@Override
	/**
	 * Creates a complete notification
	 * 
	 * @param o the new offer made
	 * @return a string with the notification
	 */
	public String FullNotification(Offer o) {
		String text = "Tu oferta para intercambiar " + o.getOriginProducts() + "por " + o.getDestinationProducts();
		switch(o.getStatus()) {
			case OfferStatus.ACCEPTED: text += " has been accepted. " + this.timeAndPlace(); break;
			case OfferStatus.REJECTED: text += " has been rejected. You can make another offer or look for another exchange"; break;
			case OfferStatus.EXPIRED: text += " has expired after " + Parameter.getParam().getOfferTime() 
												+ " have passed since its publication. You can make the same offer or a new one altogether"; 
												break;
			default: text = "Not valid\n";
		}
		
		this.setText(text);
		return this.SnippetNotification(o) + text +  this.signOff();
	}
	
	@Override
	/**
	 * Creates a snippet of the notification (title and time log)
	 * 
	 * @param o the new offer made
	 * @return a string with the snippet of the notification
	 */
	public String SnippetNotification(Offer o) {
		switch(o.getStatus()) {
			case OfferStatus.ACCEPTED: this.setTitle("Your offer has been accepted\n"); break;
			case OfferStatus.REJECTED: this.setTitle("Your offer has been rejected\n"); break;
			case OfferStatus.EXPIRED: this.setTitle("Your offer has expired\n"); break;
			default: this.setTitle("Not valid\n");
		}
		
		return this.getTitle() + this.timeLog();
	}
	
	/**
	 * Creates the string with information of address, time and date
	 * 
	 * @return a string with the time and place of the exchange as well as the address
	 */
	private String timeAndPlace() {
		return "The exchange will take place at "+this.address 
				+ " with date and time: "+this.timeAndDate.format(DateTimeFormatter.ofPattern("dd/MM HH:mm"))
				+"\n";
	}
}
