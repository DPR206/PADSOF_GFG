package notification;

/**
 * It implements the notification interface
 * 
 * @author Duna P.R.
 * @version 1.0
 * 
 */
public interface NotificationInterface<T>{
	
	/**
	 * Creates a full notification
	 * 
	 * @param o object the notification is about
	 * @return a string with the complete notification
	 */
	String FullNotification(T o);
	
	/**
	 *  Creates a snippet of the notification (title and time log)
	 *  
	 * @param o object the notification is about
	 * @return string with a snippet of the notification
	 */
	String SnippetNotification(T o);
	
}