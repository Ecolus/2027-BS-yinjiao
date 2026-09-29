# EXP-00 Baseline

## Purpose
验证 HTTP 通道端到端可用：模拟设备每秒 POST 数据到 Spring Boot，App 每秒轮询 /api/data/latest 并显示温湿度。

## Compared with
无（第一个实验，建立系统可用性基线）。

## Configuration
- 通道：HTTP/1.1 + Keep-Alive，轮询间隔 1s
- 网络：本机
- payload：固定 JSON（温湿度光照，约 120B）
- 消息带 seq 和 deviceTs

## Dataset
scripts/simulate_device.py 生成的模拟数据，共 100 条（seq 1~100）。

## Command
见 command.txt。

## Result
- 数据条数：100/100，全部 HTTP 200 到达服务端
- 端到端延迟（serverTs - deviceTs）：均值 19.6 ms，最大 84 ms，最小 2 ms
- 明细见 metrics.csv

## Conclusion
HTTP 链路（模拟设备 -> Spring Boot -> H2 库）在本机跑通，100 条数据无丢失，延迟在毫秒级，符合预期。Android 模拟器轮询 /api/data/latest 成功显示温度 23.2℃、湿度 61.3%、端到端延迟 15ms，seq=100，通道完整闭环。

## Problems
- 首次跑脚本时默认 URL 误写成 10.0.2.2（模拟器地址），本机连不上导致全部超时；改回 localhost 后正常。
- Android 布局用了 android:gap（API 31+），minSdk 26 编译报错，已改为 layout_marginBottom。
