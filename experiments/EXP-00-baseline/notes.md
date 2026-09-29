# EXP-00 Baseline

## Purpose
验证 HTTP 通道端到端可用：模拟设备每秒 POST 数据到 Spring Boot，App 每秒轮询 /api/data/latest 并显示温湿度。

## Compared with
无（第一个实验，建立系统可用性基线）。

## Configuration
- 通道：HTTP/1.1 + Keep-Alive，轮询间隔 1s
- 网络：本机 / 局域网
- payload：固定 JSON（温湿度光照）
- 消息带 seq 和 deviceTs

## Dataset
scripts/simulate_device.py 生成的模拟数据，目标 >=100 条。

## Command
见 command.txt。

## Result
跑完后由 scripts/export_metrics.py 生成 metrics.csv，并在这里填均值/最大延迟。

## Conclusion
（跑完后填：链路是否打通？App 是否稳定刷新？延迟大概多少？）

## Problems
（跑完后填：遇到的报错、连接失败等）
