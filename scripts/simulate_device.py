"""模拟 ESP8266 数据源：同时通过 HTTP POST 和 MQTT 发布环境数据。

用法：
    pip install paho-mqtt requests
    python simulate_device.py                  # 每秒一条，发往本机
    python simulate_device.py --interval 5
    python simulate_device.py --count 100

每条消息带 device_id、seq、device_ts（毫秒），用于算到达率和延迟。
"""
import argparse
import json
import random
import time
import urllib.request

try:
    import paho.mqtt.client as mqtt
    MQTT_AVAILABLE = True
except ImportError:
    MQTT_AVAILABLE = False


def main():
    p = argparse.ArgumentParser()
    p.add_argument("--http-url", default="http://localhost:8080/api/data")
    p.add_argument("--mqtt-host", default="localhost")
    p.add_argument("--mqtt-port", type=int, default=1883)
    p.add_argument("--interval", type=float, default=1.0)
    p.add_argument("--count", type=int, default=0)
    p.add_argument("--device", default="esp8266_01")
    args = p.parse_args()

    mqtt_client = None
    if MQTT_AVAILABLE:
        mqtt_client = mqtt.Client()
        try:
            mqtt_client.connect(args.mqtt_host, args.mqtt_port, 60)
            mqtt_client.loop_start()
            print(f"MQTT 已连接 broker {args.mqtt_host}:{args.mqtt_port}")
        except Exception as e:
            print(f"MQTT 连接失败（HTTP 通道不受影响）: {e}")
            mqtt_client = None

    seq = 0
    topic = f"env/{args.device}/data"
    print(f"开始发送，间隔 {args.interval}s，HTTP={args.http_url}，MQTT topic={topic}")
    try:
        while True:
            seq += 1
            body = {
                "deviceId": args.device,
                "seq": seq,
                "deviceTs": int(time.time() * 1000),
                "temperature": round(25 + random.uniform(-3, 3), 2),
                "humidity": round(60 + random.uniform(-8, 8), 2),
                "illuminance": round(300 + random.uniform(-100, 200), 1),
            }
            payload = json.dumps(body)

            # HTTP POST
            try:
                req = urllib.request.Request(
                    args.http_url, data=payload.encode("utf-8"),
                    headers={"Content-Type": "application/json"}, method="POST")
                with urllib.request.urlopen(req, timeout=5) as resp:
                    pass
            except Exception as e:
                print(f"#{seq} HTTP 失败: {e}")

            # MQTT 发布
            if mqtt_client is not None:
                mqtt_client.publish(topic, payload, qos=1)

            print(f"#{seq} 已发送 (HTTP+MQTT)")
            if args.count and seq >= args.count:
                print("达到指定条数，停止")
                break
            time.sleep(args.interval)
    except KeyboardInterrupt:
        print("\n手动停止，共发了", seq, "条")


if __name__ == "__main__":
    main()
