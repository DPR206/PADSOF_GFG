package notification;

import java.time.LocalDateTime;

public class NotificationPayment extends Notification implements NotificationInterface{

	private String servicePaid;

	/**
	 * @param timeReceived
	 * @param read
	 * @param servicePaid
	 */
	public NotificationPayment(LocalDateTime timeReceived, boolean read, String servicePaid) {
		super(timeReceived, read);
		this.servicePaid = servicePaid;
	}

	/**
	 * @param title
	 * @param text
	 * @param timeReceived
	 * @param read
	 * @param servicePaid
	 */
	public NotificationPayment(String title, String text, LocalDateTime timeReceived, boolean read, String servicePaid) {
		super(title, text, timeReceived, read);
		this.servicePaid = servicePaid;
	}

	/**
	 * @param title
	 * @param text
	 * @param timeMade
	 * @param timeReceived
	 * @param read
	 * @param servicePaid
	 */
	public NotificationPayment(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived,
			boolean read, String servicePaid) {
		super(title, text, timeMade, timeReceived, read);
		this.servicePaid = servicePaid;
	}

	/**
	 * @return the servicePaid
	 */
	String getServicePaid() {
		return servicePaid;
	}

	/**
	 * @param servicePaid the servicePaid to set
	 */
	void setServicePaid(String servicePaid) {
		this.servicePaid = servicePaid;
	}

	@Override
	public String FullNotification(Object o) {
		
		return this.SnippetNotification(o) + this.signOff();
	}

	@Override
	public String SnippetNotification(Object o) {
		this.setTitle("Your payment was succesful\n");
		return this.getTitle() + this.timeLog();
	}
	
	
	
}
