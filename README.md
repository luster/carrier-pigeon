# carrier-pigeon
Basic Notification API in Java Spring Framework

# Getting started
1. Make sure you have Maven installed.
2. Clone the repo, then
3. `mvn spring-boot:run`

Then make API calls to http://localhost:8080/URI in your browser.

# POST /notifications
---
## JSON data
```json
{ 
  "user_id": "int",
  "timestamp": "int",
  "message": "String"
}
```
## Response
```json
{
  "id": "int",
  "user_id": "int",
  "timestamp": "int",
  "message": "String"
}
```

# GET /notifications/by_user/{user_id}
---
Returns up to N of the most recent notifications, where most recent is determined by the greater timestamp number.
## Example response
GET /notifications/by_user/4
```json
[
  {
    "id": 3,
    "user_id": 4,
    "timestamp": 365365,
    "message": "Sup?"
  },
  {
    "id": 17,
    "user_id": 4,
    "timestamp": "456",
    "message": "Can't believe the Seahawks made it to the superbowl again :o"
  }
]
```

# TODO

1. Consider replacing `HashMap<user_id, PriorityQueue<Notification>>` with SQLite or another database, depending on needs for scaling.
2. Enable the ability to "read" a notification and only display unread notifications.
3. Enable the ability to update a notification's message.

# Limitations

1. Right now, the database is not persisted after the server is stopped since it is stored internally as a HashMap. This can be mitigated by replacing the "database" with a real database, perhaps a NoSQL database since the data is not too complex or relational.
2. Under a simple threaded load test, creating a notification and randomly reading another user's notifications produced 11 active threads on average at a time. It handled 10000 requests in 20 seconds, or roughly 500 requests per second. If too many requests were made, the server would refuse further connections. This could be mitigated by changing the database to a NoSQL database and putting duplicate servers behind a load balancer.
