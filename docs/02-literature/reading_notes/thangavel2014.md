# 阅读卡 · THANGAVEL D, et al., 2014

## 文献信息

- 著录：THANGAVEL D, MA X, VALERA A, et al., 2014. Performance evaluation of MQTT and CoAP via a common middleware[C]//2014 IEEE Ninth International Conference on Intelligent Sensors, Sensor Networks and Information Processing (ISSNIP). Singapore: IEEE.
- 状态：已核验（ISSNIP 2014，DOI: 10.1109/ISSNIP.2014.6827678）
- 原文：IEEE Xplore 搜题名 "Performance evaluation of MQTT and CoAP via a common middleware"

## 摘要要点

- 目标：通过一个统一中间件，在相同条件下评估 MQTT 与 CoAP 的性能。
- 方法：实现支持两种协议的公共中间件，在统一编程接口下做实验，测端到端延迟与带宽消耗。
- 关键发现：低丢包率时 MQTT 延迟低于 CoAP；高丢包率时 MQTT 延迟反而更高；消息较小时 CoAP 为保证可靠性产生的额外流量比 MQTT 少。
- 与本课题的关系：这是"统一环境对比两种协议"的经典做法，支撑核心实验设计——在同一 App、同一服务端、同一数据源下对比 MQTT 与 HTTP，并控制频率、payload、QoS、丢包率等变量。

## 我的阅读笔记（精读原文后填写）

### 研究问题
在统一中间件、相同实验条件下，MQTT 与 CoAP 的端到端延迟和带宽消耗有何差异，丢包率如何影响二者表现。

### 方法
实现一个同时支持 MQTT 和 CoAP 的公共中间件，保证上层接口一致，在受控网络环境下做对比实验，改变丢包率和消息大小两个变量。

### 数据/环境
自建测试床，包含传感器节点、中间件服务器和客户端，通过工具注入不同丢包率。

### 指标
端到端延迟、带宽消耗、消息可靠性（重传产生的额外流量）。

### 主要结论
低丢包率时 MQTT 的延迟低于 CoAP；高丢包率时 MQTT 因为 TCP 重传反而延迟上升；小消息场景下 CoAP 为保证可靠性产生的额外流量比 MQTT 少。结论说明协议优劣依赖网络条件，不能脱离场景下判断。

### 与本课题的关系
"统一环境对比两种协议"这一实验设计思路的直接参照，本课题也在同一 App、同一服务端、同一数据源下对比 MQTT 与 HTTP，并控制频率、payload、丢包等变量。
