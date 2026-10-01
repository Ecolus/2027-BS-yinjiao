# 阅读卡 · LIGHT R A, 2017

## 文献信息

- 著录：LIGHT R A, 2017. Mosquitto: server and client implementation of the MQTT protocol[J]. Journal of Open Source Software.
- 状态：已核验（JOSS，DOI: 10.21105/joss.00265）
- 原文：JOSS 官网搜 "Mosquitto: server and client implementation of the MQTT protocol"（免费下载）

## 摘要要点

- 内容：介绍 Mosquitto——符合标准规范的 MQTT 协议服务端与客户端实现，采用发布/订阅模型，网络开销低，可用于微控制器等受限设备。
- 组成：Mosquitto broker（服务端）、mosquitto_pub / mosquitto_sub 命令行工具、C 语言客户端库。
- 学术用途：文中提到可用于"对比 MQTT 与 CoAP 性能"的研究，也是智能城市、环境监测系统的常用组件。
- 与本课题的关系：系统用 Mosquitto 作 MQTT broker，论文介绍"通信链路实现"时必须引用它作为工具出处。

## 我的阅读笔记（精读原文后填写）

### 研究问题
如何提供一个符合 MQTT 标准规范、轻量且可在受限设备上运行的开源 broker 和客户端实现。

### 方法
工程实现类论文，介绍 Mosquitto 的架构、组件和使用方式，包括 broker 服务端、命令行发布/订阅工具和 C 客户端库。

### 数据/环境
无性能对比实验，给出安装方式和典型部署场景（智能城市、环境监测、WSN 网关）。

### 指标
文章本身不做量化测试，强调协议合规性和资源占用低。

### 主要结论
Mosquitto 是 Eclipse 基金会维护的 MQTT 标准实现，支持发布/订阅模型和 TLS 加密，可在 Linux 网关和嵌入式平台运行，是学术研究和工业 IoT 项目中最常用的开源 broker 之一。

### 与本课题的关系
本系统 MQTT 通道直接使用 Mosquitto 作为 broker，论文"通信链路实现"章节引用它作为工具出处，并说明版本和部署方式。
