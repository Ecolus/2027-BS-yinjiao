# frontend/ Android App

最小工程：每秒轮询后端 /api/data/latest，显示温湿度和端到端延迟。

## 运行步骤

1. 用 Android Studio 打开本目录（`frontend/`），等 Gradle Sync 完成。
2. 先在电脑上把后端跑起来（见 `backend/README.md`），并确认 `scripts/simulate_device.py` 在发数据。
3. 在 Android Studio 里选一个模拟器（或连真机），点运行。
4. App 应每秒刷新一次温度、湿度、端到端延迟。

## 地址说明

- 模拟器访问电脑后端：代码里写死 `http://10.0.2.2:8080`（Android 模拟器访问宿主机的固定地址）。
- 如果用真机：把 `MainActivity` 里的 `BASE_URL` 改成电脑的局域网 IP（手机和电脑连同一个 Wi-Fi，电脑 IP 在 `ipconfig` 里看，类似 `192.168.x.x`）。

## 这一版只做了 HTTP 通道

MQTT 订阅通道在 EXP-00 跑通后再加（见技术路线阶段 2）。
