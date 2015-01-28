package carrierPigeon;

import java.util.Comparator;

public class NotificationComparator implements Comparator<Notification> {
    public int compare(Notification x, Notification y) {
        return y.getTimestamp() - x.getTimestamp();
    }
}