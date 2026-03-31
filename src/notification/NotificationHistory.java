/**
 * 
 */
package notification;

import java.util.*;

import user.*;

/**
 * It implements the notification history
 * 
 * @author Duna P.R.
 * @version 1.1
 * @see Notification
 */
public class NotificationHistory {

	private Set<Notification> notifications;
	private User owner;
	
	/**
	 * Creates a new notification history
	 * 
	 * @param notifications the notifications of the user
	 * @param owner the user associated to the notifications
	 */
	public NotificationHistory(Set<Notification> notifications, User owner) {
		this.notifications = notifications;
		this.owner = owner;
	}
	
	/**
	 * Creates a new empty notification history 
	 * 
	 * @param owner the user associated to the notifications
	 */
	public NotificationHistory(User owner) {
		this.notifications = Collections.emptySet();
		this.owner = owner;
	}

	/**
	 * Obtains the notifications of a user
	 * 
	 * @return the notifications the notifications of the user
	 */
	public Set<Notification> getNotifications() {
		return notifications;
	}

	/**
	 * Obtains the user associated to the notification history
	 * 
	 * @return the owner user associated to the notifications
	 */
	public User getOwner() {
		return owner;
	}
	
	/**
	 * Adds a new notification to the history
	 * 
	 * @param notification the notification to add
	 */
	public void addNotification(Notification notification) {
		this.notifications.add(notification);
	}
	
	/**
	 * Adds a new set of notifications to the history
	 * 
	 * @param notifications the notifications to add
	 */
	public void addNotifications(Set<Notification> notifications) {
		this.notifications.addAll(notifications);
	}
	
	/**
	 * Makes a notification not visible for the user
	 * 
	 * @param n the notification to "erase"
	 */
	public void eraseNotification(Notification n) {
		if(this.notifications.contains(n))
			n.setVisible(false);
	}
	
	/**
	 * Marks a notification as read
	 * 
	 * @param n the notification that has been read
	 */
	public void markAsRead(Notification n) {
		if(this.notifications.contains(n))
			n.setRead(true);
	}
}
