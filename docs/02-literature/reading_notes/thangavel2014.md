# 阅读卡 · THANGAVEL D, et al., 2014

## 文献信息

- 著录：THANGAVEL D, MA X, VALERA A, et al., 2014. Performance evaluation of MQTT and CoAP via a common middleware[C]//2014 IEEE Ninth International Conference on Intelligent Sensors, Sensor Networks and Information Processing (ISSNIP). Singapore: IEEE.
- 状态：✅ 已核验（ISSNIP 2014，5 位作者）
- 下载/原文：IEEE Xplore 搜题名 "Performance evaluation of MQTT and CoAP via a common middleware"

## 摘要要点（导读）

- 目标：通过一个统一中间件，在相同条件下评估 MQTT 与 CoAP 的性能。
- 方法：设计实现支持两种协议的公共中间件（可扩展支持未来协议），在统一编程接口下做实验，测端到端延迟与带宽消耗。
- 关键发现：低丢包率时 MQTT 延迟低于 CoAP；高丢包率时 MQTT 延迟反而更高；消息较小时 CoAP 为保证可靠性的额外流量低于 MQTT。
- 与你的课题的关系：这是"统一环境对比两种协议"的经典方法，正好支撑你的核心实验设计——你也需要在同一 App、同一服务端、同一数据源下对比 MQTT 与 HTTP，并控制变量（频率、payload、QoS、丢包率）。

## 我的阅读笔记（精读原文后填写）

### 研究问题

### 方法

### 数据/环境

### 指标

### 主要结论

### 与我课题的关系
