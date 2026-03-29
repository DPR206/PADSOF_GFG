package notification;

import user.RegisteredClient;

/**
 * It implements the wallet
 * 
 * @author Duna P.R.
 * @version 1.0
 * 
 */
public interface NotificationInterface<T>{
	String FullNotification(T o);
	String SnippetNotification(T o);
	
}