/**
 *
 */
package notification;

import user.User;

import java.util.*;

/**
 * It implements the notification history
 *
 * @author Duna P.R.
 * @version 1.1
 * @see Notification
 * @see NotificationSettings
 */
public class NotificationHistory {

	private Set<Notification> notifications;
	private User owner;
	private NotificationSettings settings;

	/**
	 * Creates a new notification history
	 *
	 * @param notifications the notifications of the user
	 * @param owner the user associated to the notifications
	 * @param settings the notification settings
	 */
	public NotificationHistory(Set<Notification> notifications, User owner, NotificationSettings settings) {
		this.notifications = new LinkedHashSet<>(notifications);
		this.owner = owner;
		this.settings = settings;
	}

	/**
	 * Creates a new empty notification history
	 *
	 * @param owner the user associated to the notifications
	 * @param settings the notification settings
	 */
	public NotificationHistory(User owner, NotificationSettings settings) {
		this.notifications = new LinkedHashSet<>();
		this.owner = owner;
		this.settings = settings;
	}

	/**
	 * Creates a new notification history
	 *
	 * @param notifications the notifications of the user
	 * @param owner the user associated to the notifications
	 */
	public NotificationHistory(Set<Notification> notifications, User owner) {
		this.notifications = new LinkedHashSet<>(notifications);
		this.owner = owner;
		this.settings = new NotificationSettings(owner);
	}



	/**
	 * Creates a new empty notification history
	 *
	 * @param owner the user associated to the notifications
	 */
	public NotificationHistory(User owner) {
		this.notifications = new LinkedHashSet<>(notifications);
		this.owner = owner;
		this.settings = new NotificationSettings(owner);
	}

	/**
	 * Obtains the notifications of a user
	 *
	 * @return the notifications of the user
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
	 * Obtains the notification settings
	 *
	 * @return the settings of the notifications
	 */
	public NotificationSettings getSettings() {
		return settings;
	}

	/**
	 * Adds a new notification to the history
	 *
	 * @param notification the notification to add
	 */
	public void addNotification(Notification notification) {
		if(this.settings.getInterests().get(notification.getType()))
			this.notifications.add(notification);
	}

	/**
	 * Adds a new set of notifications to the history
	 *
	 * @param notifications the notifications to add
	 */
	public void addNotifications(Set<Notification> notifications) {
		for(Notification n : notifications) {
			if(this.settings.getInterests().get(n.getType()))
				this.notifications.add(n);
		}
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

	/**
	 * Obtains a list of notifications based on how recent they are
	 *
	 * @return list of notifications
	 */
	public List<Notification> getNotificationsSorted(){
		List<Notification> sortedList = new ArrayList<>(this.notifications);
	    sortedList.sort(Comparator.comparing(Notification::getTimeMade).reversed());
	    return sortedList;
	}

}