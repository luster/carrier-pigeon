from threading import Thread
import threading
import string
import random
import requests
import json
import time

x = 0

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
    global x
    x += 1
    #print r.text

@async
def get_notifications(user_id):
    URL = 'http://localhost:8080/notifications/by_user/' + str(user_id)

    r = requests.get(URL)
    global x
    x += 1
    #print r.text

start = time.time()
for i in xrange(20000):
    create_notification()
    y = threading.active_count()
    if y > 10:
        print y

print str(time.time() - start) + ' seconds'
print str(x) + ' requests'