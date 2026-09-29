"""从后端拉取最近 100 条数据，生成 EXP-00 的 metrics.csv，并打印延迟统计。

用法：
    python export_metrics.py                 # 从本机后端拉，写到当前目录 metrics.csv
    python export_metrics.py --out ..\\experiments\\EXP-00-baseline\\metrics.csv

端到端延迟 = serverTs - deviceTs（毫秒）。
"""
import argparse
import csv
import json
import urllib.request
from statistics import mean


def main():
    p = argparse.ArgumentParser()
    p.add_argument("--url", default="http://localhost:8080/api/data")
    p.add_argument("--out", default="metrics.csv")
    args = p.parse_args()

    with urllib.request.urlopen(args.url, timeout=10) as resp:
        rows = json.loads(resp.read().decode("utf-8"))

    delays = []
    with open(args.out, "w", newline="", encoding="utf-8") as f:
        w = csv.writer(f)
        w.writerow(["deviceId", "seq", "deviceTs", "serverTs", "delayMs",
                    "temperature", "humidity", "illuminance"])
        for r in reversed(rows):  # 按时间正序写
            delay = r["serverTs"] - r["deviceTs"]
            delays.append(delay)
            w.writerow([r["deviceId"], r["seq"], r["deviceTs"], r["serverTs"],
                        delay, r["temperature"], r["humidity"], r["illuminance"]])

    print(f"共导出 {len(delays)} 条 -> {args.out}")
    if delays:
        print(f"端到端延迟：均值 {mean(delays):.1f} ms，最大 {max(delays)} ms，最小 {min(delays)} ms")


if __name__ == "__main__":
    main()
