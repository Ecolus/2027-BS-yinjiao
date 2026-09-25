# 系统架构

## 一、总体架构

- **设备层**：ESP8266（或模拟器脚本）采集温湿度、光照等环境数据。
- **接入层**：
  - MQTT：数据通过 Mosquitto broker 发布/订阅。
  - HTTP：服务端提供 REST 接口，App 轮询。
- **服务层**：Spring Boot 提供数据接收、存储、查询、控制指令转发。
- **数据层**：SQLite / MySQL 存储历史数据。
- **应用层**：Android App，双通道路由（MQTT 实时订阅 + HTTP 轮询兜底），实时显示、历史曲线、远程控制。

## 二、模块划分

- `firmware/`：ESP8266 Arduino / ESP-IDF 代码。
- `backend/`：Spring Boot 工程。
- `frontend/`：Android 工程。
- `hardware/`：接线图、传感器选型说明。

## 三、数据流

1. 设备端按固定频率采集数据 → JSON 格式。
2. 数据通过 MQTT 发布到 broker（topic：`env/<device_id>/data`），同时 POST 到服务端 HTTP 接口。
3. 服务端双写：MQTT 收到的数据落库；HTTP 接口收到的数据落库。
4. App：
   - MQTT 订阅 `env/+/data` 实时接收。
   - HTTP 定时轮询 `/api/data/latest?device_id=xxx`。
5. 控制指令：App 通过 HTTP POST `/api/command` 或 MQTT 发布 `env/<device_id>/cmd`，服务端转发到设备。
