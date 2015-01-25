package carrierPigeon;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class NotificationController {

	//private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value="/notifications/by_user/{user_id}", method=RequestMethod.GET)
	public List<Notification> notification(@PathVariable int user_id) {
		ArrayList<Notification> list = new ArrayList<Notification>();

		//return new Notification(1, user_id, 1241345, "hi");
		return list;
	}
}