# 阅读卡 · AL-FUQAHA A, et al., 2015

## 文献信息

- 著录：AL-FUQAHA A, GUIZANI M, MOHAMMADI M, et al., 2015. Internet of Things: a survey on enabling technologies, protocols, and applications[J]. IEEE Communications Surveys & Tutorials.
- 状态：已核验（IEEE COMST；DOI 待补）
- 原文：IEEE Xplore 搜题名 "Internet of Things: A Survey on Enabling Technologies, Protocols and Applications"

## 摘要要点

- 目标：IoT 技术全景综述——使能技术、协议与应用问题。
- 方法：水平概述 IoT 分层（感知/网络/应用），梳理各层协议如何配合，给出应用案例（智能电网、智能交通、智慧城市等），并讨论 IoT 与大数据、云/雾计算的关系。
- 与本课题的关系：绪论"研究背景"和"相关工作"章节的基础文献；其中对 MQTT、CoAP、HTTP 等应用层协议的归类可以支撑协议介绍部分。

## 我的阅读笔记（精读原文后填写）

### 研究问题
IoT 系统由哪些使能技术、协议和应用构成，感知层、网络层、应用层之间如何配合，现有方案在隐私、互操作和大规模部署上有哪些挑战。

### 方法
综述类研究，按 IoT 分层架构梳理各层代表性技术与协议，结合智能电网、智能交通、智慧城市等典型应用案例归纳，不做新的实测实验。

### 数据/环境
无新实验数据，基于已有文献和标准规范（如 IEEE 802.15.4、6LoWPAN、MQTT、CoAP 等）进行归纳整理。

### 指标
未做量化性能对比，按场景定性讨论协议适用范围、安全风险和部署成本。

### 主要结论
IoT 不存在统一的协议栈，应用层需要根据设备能力、网络条件和业务模型选择协议；MQTT/CoAP 适合受限设备和低带宽场景，HTTP 适合通用 Web 集成；隐私保护、设备互操作和与云/雾计算的协同是主要开放问题。

### 与本课题的关系
论文绪论"研究背景"和"相关工作"章节的框架来源，用于引出移动端环境监测场景和 MQTT/HTTP 两条通道的选题动机。
