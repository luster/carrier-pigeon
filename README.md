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

1. Consider replacing HashMap<user_id, PriorityQueue<Notification>> with SQLite or another database, depending on needs for scaling.
2. Enable the ability to "read" a notification and only display unread notifications.
3. Enable the ability to update a notification's message.

# Limitations

TODO
