package notification;

import java.time.*;
import user.RegisteredClient;

/**
 * It implements the wallet
 * 
 * @author Duna P.R.
 * @version 1.0
 * 
 */
public interface NotificationMessage<T>{
	Notification createNotification(RegisteredClient user, T o);
	
	default LocalDateTime getTimeMade() {
		return LocalDateTime.now();
	}
}