"""模拟 ESP8266 数据源：按固定间隔向 Spring Boot 发一条环境数据。

用法：
    pip install requests
    python simulate_device.py                  # 默认每秒一条，发往本机 8080
    python simulate_device.py --interval 5    # 每 5 秒一条
    python simulate_device.py --count 100       # 只发 100 条就停

每条消息带 device_id、seq（递增序号）、device_ts（设备端时间戳，毫秒），
这两个字段后面用来算到达率和端到端延迟。
"""
import argparse
import json
import random
import time
import urllib.request


def main():
    p = argparse.ArgumentParser()
    p.add_argument("--url", default="http://localhost:8080/api/data",
                    help="服务端地址。电脑本机跑脚本用 http://localhost:8080/api/data；"
                         "Android 模拟器里访问电脑才用 http://10.0.2.2:8080/api/data")
    p.add_argument("--interval", type=float, default=1.0, help="发送间隔秒数")
    p.add_argument("--count", type=int, default=0, help="发多少条后停止，0 表示一直发")
    p.add_argument("--device", default="esp8266_01")
    args = p.parse_args()

    seq = 0
    print(f"开始向 {args.url} 发送数据，间隔 {args.interval}s，设备 {args.device}")
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
            req = urllib.request.Request(
                args.url,
                data=json.dumps(body).encode("utf-8"),
                headers={"Content-Type": "application/json"},
                method="POST",
            )
            try:
                with urllib.request.urlopen(req, timeout=5) as resp:
                    print(f"#{seq} 发送成功 http={resp.status}")
            except Exception as e:
                print(f"#{seq} 发送失败: {e}")
            if args.count and seq >= args.count:
                print("达到指定条数，停止")
                break
            time.sleep(args.interval)
    except KeyboardInterrupt:
        print("\n手动停止，共发了", seq, "条")


if __name__ == "__main__":
    main()
