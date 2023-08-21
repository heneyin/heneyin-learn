import random

import requests

import time
import json

r = random.Random

def doAction():
    # 请求地址
    url = "https://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=8911d31dc8246d29e299f7353a3d6b88"
    # 发送get请求
    res = requests.get(url)
    res_json = res.json()

    info = res_json["info"]
    if info != 'OK':
        return

    lives = res_json["lives"]
    live = None
    if lives is None or len(lives) <= 0:
        return
    live = lives[0]
    if live is None:
        return
    timestamp = time.strftime("%Y-%m-%d %H:%M:%S")
    data = {"@timestamp": timestamp, "_extract_binary_content": True,"_reduce_whitespace": True, "_run_ml_inference": True}
    data.update(live)
    data['temperature'] = int(data['temperature']) + random.randint(-5, 5)
    data['temperature_float'] = float(data['temperature_float']) + random.randint(-3, 3)
    print("data: " + str(data))

    pr = requests.post(
        "https://49c07c708d36472faa3ba8f3b3d56bce.us-central1.gcp.cloud.es.io:443/search-beijing-weather/_doc?pipeline=ent-search-generic-ingestion", \
        headers={"Content-Type": "application/json", "Authorization": "ApiKey dUhDNl9ZWUJ4dUx4QXhLbFZNblk6XzhSUHhiX0xTazZwN25WNUpvTUQ5UQ=="}, \
        data=json.dumps(data))
    print(pr.json())

def loop_monitor():
    while True:
        doAction()
        time.sleep(60)  # 暂停5秒


if __name__ == "__main__":
    loop_monitor()
