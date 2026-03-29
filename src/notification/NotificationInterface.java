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
	String FullNotification(RegisteredClient user, T o);
	String SnippetNotification(RegisteredClient user, T o);
	
}