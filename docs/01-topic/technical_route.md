# 技术路线

## 一、系统组成

```
┌──────────────┐   MQTT / HTTP    ┌──────────────┐   MySQL/SQLite   ┌──────────────┐
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

## 二、技术栈选型

| 模块 | 技术 | 说明 |
|---|---|---|
| 移动端 | Android（Java） | 延续本人已有 Android + SQLite 经验 |
| 服务端 | Spring Boot（Java） | 与移动端语言一致，降低学习成本 |
| MQTT Broker | Eclipse Mosquitto | 开源、轻量、本地可部署 |
| 数据源 | ESP8266 + 温湿度/光照传感器 | 硬件未到位前用模拟脚本代替 |
| 数据库 | SQLite（开发期）/ MySQL（可换） | 存储历史数据 |
| HTTP 客户端 | OkHttp / Retrofit | Android 端 HTTP 轮询 |
| MQTT 客户端 | Eclipse Paho Android | Android 端 MQTT 订阅 |

## 三、开发顺序

1. **阶段 1（Baseline 链路）**：模拟数据源 → Spring Boot 接口 → App HTTP 轮询显示。先打通一条链路。
2. **阶段 2（MQTT 通道）**：部署 Mosquitto，App 增加 MQTT 订阅通道，实现双通道并行。
3. **阶段 3（远程控制）**：App 下发控制指令（HTTP + MQTT 两条路径都实现），服务端转发到设备端。
4. **阶段 4（对比实验）**：按 `docs/03-design/experiment_design.md` 跑实验、采数据。
5. **阶段 5（拓展）**：历史曲线、多设备、缓存补传等。

## 四、对比实验控制变量

- 上报频率：1s / 5s / 30s（HTTP 轮询间隔取同值）
- payload：固定一个 JSON 大小（如 256B / 1KB 两档）
- 网络环境：局域网 Wi-Fi；后续可加弱网模拟
- MQTT QoS：QoS 0 与 QoS 1 对照
- 每档实验重复 ≥30 次，记录端到端延迟、到达率、控制指令时延、断网恢复时间
