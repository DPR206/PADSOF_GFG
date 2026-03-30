package notification;

import java.time.LocalDateTime;

public class NotificationPayment extends Notification implements NotificationInterface<String>{

	//private String servicePaid;

	/**
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationPayment(LocalDateTime timeReceived, boolean read, boolean visible/*, String servicePaid*/) {
		super(timeReceived, read, visible);
		//this.servicePaid = servicePaid;
	}

	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationPayment(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible/*, String servicePaid*/) {
		super(title, text, timeReceived, read, visible);
		//this.servicePaid = servicePaid;
	}

	/**
	 * @param title
	 * @param text
	 * @param timeMade
	 * @param timeReceived
	 * @param read
	 * @param visible
	 */
	public NotificationPayment(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, boolean visible/*, String servicePaid*/) {
		super(title, text, timeMade, timeReceived, read, visible);
		//this.servicePaid = servicePaid;
	}

	/**
	 * @return the servicePaid
	 */
	/*String getServicePaid() {
		return servicePaid;
	}*/

	/**
	 * @param servicePaid the servicePaid to set
	 */
	/*void setServicePaid(String servicePaid) {
		this.servicePaid = servicePaid;
	}*/

	@Override
	public String FullNotification(String o) {
		String text = "Your payment for " + o + " was completed successfully. Once your purchase is ready you'll receive a new notification\n";
		this.setText(text);
		return this.SnippetNotification(o) + this.getText() + this.signOff();
	}

	@Override
	public String SnippetNotification(String o) {
		this.setTitle("Your payment was succesful\n");
		return this.getTitle() + this.timeLog();
	}
	
	
	
}
