from threading import Thread
import string
import random
import requests
import json

def async(f):
    def wrapper(*args, **kwargs):
        thr = Thread(target=f, args=args, kwargs=kwargs)
        thr.start()
    return wrapper

@async
def create_notification():
    URL = 'http://localhost:8080/notifications'

    data = {
        "user_id": random.randint(0,100),
        "timestamp": random.randint(1,100000),
        "message": ''.join(random.choice(string.ascii_uppercase + string.digits) for _ in range(50))
    }

    r = requests.post(URL, data=json.dumps(data), headers={'content-type': 'application/json'})
    print r.text

@async
def get_notifications(user_id):
    URL = 'http://localhost:8080/notifications/by_user/' + str(user_id)

    r = requests.get(URL)
    print r.text

for i in xrange(5000):
    create_notification()
    get_notifications(random.randint(0,100))