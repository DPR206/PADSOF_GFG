/**
 * 
 */
package notification;

import java.time.*;

/**
 * It implements the notification
 * @author Duna P.R.
 * @version 1.4
 */
public abstract class Notification {
	private String title;
	private String text;
	private LocalDateTime timeMade;
	private LocalDateTime timeReceived;
	private boolean read;
	private boolean visible;
	private NotificationType type;
	
	/**
	 * Creates a new notification
	 * 
	 * @param title the title of the notification
	 * @param text the message in the notification
	 * @param timeMade the time it was made
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public Notification(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived, boolean read, boolean visible, 
						NotificationType type) {
		this.title = title;
		this.text = text;
		this.timeMade = timeMade;
		this.timeReceived = timeReceived;
		this.read = read;
		this.visible = visible;
		this.type = type;
	}
	
	/**
	 * Creates a new notification with default timeMade (this moment)
	 * 
	 * @param title the tile of the notification
	 * @param text the message in the notification
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public Notification(String title, String text, LocalDateTime timeReceived, boolean read, boolean visible, NotificationType type) {
		this(title, text, LocalDateTime.now(),timeReceived, read, visible, type);
		
	}
	
	/**
	 * Creates a new notification with default timeMade (this moment) and no content
	 * 
	 * @param timeReceived the time it was received
	 * @param read whether the user has read it or not
	 * @param visible if the notification has been erased by the user (thus no longer visible for them)
	 * @param type the type of notification
	 */
	public Notification(LocalDateTime timeReceived, boolean read, boolean visible, NotificationType type) {
		this("", "", LocalDateTime.now(),timeReceived, read, visible, type);
		
	}

	/**
	 * Obtains the title of the notification
	 * 
	 * @return the title the notification title
	 */
	String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the notification
	 * 
	 * @param title the title to set
	 */
	void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Obtains the message of the notification
	 * 
	 * @return the text the notification text
	 */
	String getText() {
		return text;
	}

	/**
	 * Sets the message of the notification
	 * 
	 * @param text the text to set
	 */
	void setText(String text) {
		this.text = text;
	}

	/**
	 * Obtains the time the notification was made
	 * 
	 * @return the timeMade the time it was made
	 */
	LocalDateTime getTimeMade() {
		return timeMade;
	}

	/**
	 * Sets the time it was made
	 * 
	 * @param timeMade the timeMade to set
	 */
	void setTimeMade(LocalDateTime timeMade) {
		this.timeMade = timeMade;
	}

	/**
	 * Obtains the time the user received it
	 * 
	 * @return the timeReceived the time it was received
	 */
	LocalDateTime getTimeReceived() {
		return timeReceived;
	}

	/**
	 * Sets the time the user received it
	 * 
	 * @param timeReceived the timeReceived to set
	 */
	void setTimeReceived(LocalDateTime timeReceived) {
		this.timeReceived = timeReceived;
	}

	/**
	 * Obtains whether the user has read the notification
	 * 
	 * @return the read true if it was read, false if else
	 */
	boolean isRead() {
		return read;
	}

	/**
	 * Marks whether the notification was read
	 * 
	 * @param read the read to set
	 */
	void setRead(boolean read) {
		this.read = read;
	}
	
	/**
	 * Obtains whether the user has erased this notification
	 * 
	 * @return the visible true if it hasn't been erased, false if else
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Marks whether the notification has been erased or not
	 * 
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Obtains the type of notification
	 * 
	 * @return the type the type of notification
	 */
	public NotificationType getType() {
		return type;
	}

	/**
	 * Sets the type of the notification
	 * 
	 * @param type the type to set
	 */
	 void setType(NotificationType type) {
		this.type = type;
	}
	
	
/*-----------------------------------------------------------------METHODS--------------------------------------------------------*/
	

	/**
	 * Calculates the the amount of time that has passed since the user received the notification
	 * 
	 * @return a string with the amount of time since the notification was received (hh/mm/ss)
	 */
	public String timeLog() {
		Duration duration = Duration.between(timeReceived, LocalDateTime.now());

		long hours = duration.toHours();
		long mins = duration.toMinutesPart(); 
		long segs = duration.toSecondsPart();

		return "Elapsed time: " + hours + "h " + mins + "m " + segs + "s";
	}
	
	/**
	 * The sign off of all the store notifications
	 * 
	 * @return a string with the store sign off
	 */
	public String signOff() {
		return "Thank you for using our services\n";
	}
}
