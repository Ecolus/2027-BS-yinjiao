# 技术路线

## 一、系统组成

```
┌──────────────┐   MQTT / HTTP    ┌──────────────┐   H2/SQLite     ┌──────────────┐
│  数据源       │ ───────────────▶ │  服务端       │ ◀──────────────▶ │   数据库      │
│ (ESP8266 或   │                  │ (Spring Boot)│                  │              │
│  模拟器脚本)   │ ◀─────────────── │              │                  │              │
└──────────────┘   控制指令下行    └──────┬───────┘                  └──────────────┘
                                          │
                            MQTT 订阅 / HTTP 轮询
                                          │
                                          ▼
                                  ┌──────────────┐
                                  │  Android App │
                                  │  (Java)      │
                                  └──────────────┘
```

每条消息都带 `device_id`、`seq`（递增序号）、`timestamp`、温湿度光照字段，这是后面算到达率和延迟的依据。

## 二、技术栈选型

| 模块 | 技术 | 说明 |
|---|---|---|
| 移动端 | Android（Java） | 之前学过 Android + SQLite，上手快 |
| 服务端 | Spring Boot（Java） | 和移动端同语言，不用再学一套 |
| MQTT Broker | Eclipse Mosquitto | 开源、轻量，本机部署 |
| 数据源模拟 | Python 脚本 | 硬件没到位前，每秒 POST 一条数据 |
| 真实数据源 | ESP8266 + 温湿度/光照传感器 | 硬件到位后替换模拟脚本 |
| 数据库 | H2（开发期内存库）/ SQLite | 存历史数据 |
| HTTP 客户端 | OkHttp / Retrofit | Android 端轮询用，默认 Keep-Alive 连接复用 |
| MQTT 客户端 | Eclipse Paho Android | Android 端订阅用 |

注意 HTTP 这里不叫"短连接"。OkHttp/HTTP1.1 默认复用连接、有 keep-alive，主实验就固定这个真实形态；"每次新建 TCP 连接"只作为单独的参数实验。

## 三、开发顺序

1. **阶段 1（HTTP Baseline）**：Python 脚本模拟设备 → Spring Boot 提供 HTTP 接口 → App 用 Retrofit 每秒轮询 → App 显示温湿度 → 数据落库。先把这一条链路跑通，完成 EXP-00。
2. **阶段 2（MQTT 通道）**：部署 Mosquitto，脚本同时发 MQTT，App 加 MQTT 订阅，双通道并行显示。
3. **阶段 3（远程控制）**：App 下发控制指令，HTTP 和 MQTT 两条路径都做，服务端转发到设备端。
4. **阶段 4（对比实验）**：按 `docs/03-design/experiment_design.md` 跑 EXP-01~05，采数据。
5. **阶段 5（拓展）**：历史曲线、多设备、缓存补传等。

## 四、对比实验控制变量

- 上报频率 = HTTP 轮询间隔：1s / 5s / 30s
- HTTP 主实验固定：HTTP/1.1 + Keep-Alive + 固定轮询间隔
- payload：256B / 1KB / 4KB
- 网络：局域网 Wi-Fi，弱网另作 EXP-05
- MQTT QoS：QoS 0 和 QoS 1 对照
- 每档 ≥30 次，记录端到端延迟、到达率、控制时延、断网重连时间
