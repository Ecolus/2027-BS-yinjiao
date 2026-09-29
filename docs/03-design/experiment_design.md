# 实验设计文档

回答老师说的"实验设计四问"：和谁比、测什么、改什么变量、什么结果算有效。

## 一、研究问题与比较口径

本论文比较的不是"MQTT 协议和 HTTP 协议谁更快"，而是**两种通信机制在本系统移动物联网场景下的性能差异**：

- MQTT：发布/订阅，服务端推送（push）
- HTTP：客户端轮询（pull），主实验固定为 HTTP/1.1 + Keep-Alive + 固定轮询间隔

要说明一点：这两种方案同时改变了两个因素——通信协议本身，以及交互模式（push vs pull）。所以如果测出来 MQTT 延迟更低，不能全部归因于"MQTT 协议本身更高效"，其中一部分来自 HTTP 要等下一次轮询。因此结论的写法是：

> "MQTT 发布订阅机制与 HTTP 轮询机制在本系统移动物联网场景下的性能差异"

而不是"证明 MQTT 协议性能优于 HTTP 协议"。这是学术表述边界，写论文时守住。

## 二、测哪些指标

每条消息都带 `seq`（递增序号）和 `timestamp`（设备端产生时刻），这是计算到达率和延迟的基础。

| 指标 | 定义 | 测量方式 |
|---|---|---|
| 端到端延迟 | 数据产生到 App 显示的时间差（ms） | 设备端打 timestamp，App 收到再打一个，取差值 |
| 消息到达率 | 实际收到条数 / 应收到条数 | 按 seq 统计：服务端发出数 vs App 收到数 |
| 控制指令响应时延 | App 下发指令到设备收到的时间（ms） | App 发指令打时间戳，设备收到打时间戳 |
| 断网重连时间 | 从断网到链路自动恢复的时间（s） | 人为开关 Wi-Fi / 飞行模式，记录恢复时刻 |
| 恢复后是否正常通信 | 重连后链路能否继续收发 | 重连后观察后续消息是否正常到达 |
| 断网期间数据是否自然丢失 | 恢复后断网期间的数据有没有到 | 对比断网时段设备发出的 seq 与 App 最终收到的 seq |

说明：最后两条属于最低要求，测的是"没有缓存补传时的自然表现"。缓存补传是拓展目标，另作一组实验，不和最低任务混在一起。

## 三、实验分组与控制变量

主实验开始前固定：上报频率与 HTTP 轮询间隔取同一数值；payload 大小固定；局域网 Wi-Fi；服务端处理逻辑一致；MQTT 固定 QoS；HTTP 客户端固定开启 Keep-Alive（连接复用）。

| 编号 | 名称 | 改变的变量 | 固定项 | 重复 |
|---|---|---|---|---|
| EXP-00 | baseline | 无（验证系统可用） | 局域网，1s，keep-alive | 跑通即可，≥100 条记录 |
| EXP-01 | frequency | 上报/轮询间隔 1s / 5s / 30s | QoS 1，固定 payload，keep-alive | 每档 ≥30 次 |
| EXP-02 | qos | MQTT QoS 0 vs QoS 1 | 1s，局域网 | ≥30 次 |
| EXP-03 | payload | 256B vs 1KB vs 4KB | 1s，QoS 1，局域网 | ≥30 次 |
| EXP-04 | disconnection | 断网 10s / 30s / 60s | 1s，QoS 1 | 每档 ≥5 次 |
| EXP-05 | weak-network | 限制带宽 / 加延迟 | 1s，QoS 1 | ≥30 次 |
| EXP-06（可选） | http-connection-reuse | HTTP 每次新建 TCP vs Keep-Alive 复用 | 1s，轮询间隔同值 | ≥30 次 |

EXP-06 是参数实验，不进主结论。主实验的 HTTP 一律用 Keep-Alive，因为真实移动 App 的 OkHttp/HTTP1.1 默认就是复用连接，这样才贴近实际。

## 四、断网实验的两个层次（对应老师意见）

最低要求（必做，EXP-04）：
- 两种机制在断网—恢复后的重连时间；
- 恢复后是否继续正常通信；
- 断网期间数据是否自然丢失（不做补传，如实记录丢了多少）。

拓展要求（选做，单独一组）：
- 给 App 加本地缓存 / 补传机制，再做一次断网实验；
- 研究缓存补传能否降低数据损失、补传带来多少额外延迟。

## 五、什么结果算有效

- 不是笼统说"MQTT 更快"。
- 要落到具体数字：在 X 频率、Y payload、Z 网络下，MQTT 平均延迟比 HTTP 低多少（给均值和最大值）；弱网、断网下两者分别什么表现；轮询间隔拉长时 HTTP 的延迟怎么变化。
- 最后给出选型建议：什么场景适合 push，什么场景用轮询就够。
- 手机耗电只作拓展指标（Android 系统级统计误差大），不作核心结论。

## 六、实验目录

每个实验一个文件夹，统一放 config.yaml、command.txt、notes.md、metrics.csv：

```
experiments/
├── EXP-00-baseline/
├── EXP-01-frequency/
├── EXP-02-qos/
├── EXP-03-payload/
├── EXP-04-disconnection/
├── EXP-05-weak-network/
└── EXP-06-http-reuse/
```

## 七、单次实验记录模板

```
# Experiment ID: EXP-01
## Purpose
## Compared with
## Configuration（频率/QoS/payload/网络/HTTP是否keep-alive）
## Dataset
## Command
## Result（见 metrics.csv）
## Conclusion
## Problems
```
