/**
 * 
 */
package notification;

import java.time.*;
/**
 * 
 */
public abstract class Notification {
	private String title;
	private String text;
	private LocalDateTime timeMade;
	private LocalDateTime timeReceived;
	private boolean read;
	
	/**
	 * @param title
	 * @param text
	 * @param timeMade
	 * @param timeReceived
	 * @param read
	 */
	public Notification(String title, String text, LocalDateTime timeMade, LocalDateTime timeReceived, boolean read) {
		this.title = title;
		this.text = text;
		this.timeMade = timeMade;
		this.timeReceived = timeReceived;
		this.read = read;
	}
	
	public Notification(String title, String text, LocalDateTime timeReceived, boolean read) {
		this(title, text, LocalDateTime.now(),timeReceived, read);
		
	}
	
	public Notification(LocalDateTime timeReceived, boolean read) {
		this("", "", LocalDateTime.now(),timeReceived, read);
		
	}

	/**
	 * @return the title
	 */
	String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the text
	 */
	String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the timeMade
	 */
	LocalDateTime getTimeMade() {
		return timeMade;
	}

	/**
	 * @param timeMade the timeMade to set
	 */
	void setTimeMade(LocalDateTime timeMade) {
		this.timeMade = timeMade;
	}

	/**
	 * @return the timeReceived
	 */
	LocalDateTime getTimeReceived() {
		return timeReceived;
	}

	/**
	 * @param timeReceived the timeReceived to set
	 */
	void setTimeReceived(LocalDateTime timeReceived) {
		this.timeReceived = timeReceived;
	}

	/**
	 * @return the read
	 */
	boolean isRead() {
		return read;
	}

	/**
	 * @param read the read to set
	 */
	void setRead(boolean read) {
		this.read = read;
	}
	
/*-----------------------------------------------------------------METHODS--------------------------------------------------------*/
	
	public String timeLog() {
		Duration duration = Duration.between(timeReceived, LocalDateTime.now());

		long hours = duration.toHours();
		long mins = duration.toMinutesPart(); 
		long segs = duration.toSecondsPart();

		return "Elapsed time: " + hours + "h " + mins + "m " + segs + "s";
	}
	
	public String signOff() {
		return "Thank you for using our services\n";
	}
}
