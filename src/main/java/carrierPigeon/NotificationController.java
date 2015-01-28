package carrierPigeon;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class NotificationController {

    // Number of elements to return
    public final static Integer N = 5;

    // Auto-Increment id numbers
    private final AtomicInteger counter = new AtomicInteger(1);

    // "Database" - HashMap Int-> Max PriorityQueue of Notifications, sorted
    // by timestamp
    Map<Integer, PriorityQueue<Notification>> db = new HashMap<Integer, PriorityQueue<Notification>>();

    // GET /notifications/by_user/<user_id>
    // Return most recent N notifications (by timestamp field)
	@RequestMapping(value="/notifications/by_user/{user_id}", method=RequestMethod.GET)
	public Notification[] get_notifications(@PathVariable int user_id) {

        PriorityQueue<Notification> q = db.get(user_id);
        if (q == null) {
            q = new PriorityQueue<Notification>(N, new NotificationComparator());
            db.put(user_id, q);
        }

        // copy PriorityQueue into array and return
        PriorityQueue<Notification> tmp = new PriorityQueue<Notification>(q);
        int returnSize = N;
        if (q.size() < N) {
            returnSize = q.size();
        }
        Notification[] notifications = new Notification[returnSize];
        int i=0;

        while ( !tmp.isEmpty() && i < N) {
            notifications[i] = tmp.remove();
            i++;
        }

        return notifications;
	}

    // POST /notifications
    @RequestMapping(value="/notifications",
                    method=RequestMethod.POST,
                    headers={"Content-type=application/json"})
    @ResponseBody
    public Notification create_notification(@RequestBody Notification notification) {
        notification.setId(counter.incrementAndGet());
        int user_id = notification.getUser_id();

        PriorityQueue<Notification> q = db.get(user_id);
        if (q == null) {
            q = new PriorityQueue<Notification>(10, new NotificationComparator());
            db.put(user_id, q);
        }
        q.add(notification);
        db.put(user_id, q);

        return notification;
    }

}
