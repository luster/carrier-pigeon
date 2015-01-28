package carrierPigeon;

public class Notification {

	private int id;
	private int user_id;
	private int timestamp;
	private String message;

	public Notification() {}

	public Notification(int id, int user_id, int timestamp, String message) {
		this.id = id;
		this.user_id = user_id;
		this.timestamp = timestamp;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public int getUser_id() {
		return user_id;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setId(int id) {
		this.id = id;
	}

}
