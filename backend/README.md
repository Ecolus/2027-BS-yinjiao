# backend/ 服务端

Spring Boot 最小工程。提供 HTTP 接口接收模拟设备的数据并存库。

## 运行前提

- 装好 JDK 17（IntelliJ IDEA 一般自带，或单独装）
- 用 IntelliJ IDEA 打开本目录（`backend/`），等 Maven 自动下载依赖
- 运行 `IotBaselineApplication.java`，服务起在 http://localhost:8080

## 接口

| 方法 | 路径 | 作用 |
|---|---|---|
| POST | /api/data | 接收一条设备数据（JSON），自动写入服务端收到时间 serverTs |
| GET | /api/data/latest | 返回最新一条，App 轮询用 |
| GET | /api/data | 返回最近 100 条 |
| GET | /api/data/count | 返回已收条数 |
| GET | /h2-console | 数据库查看页（JDBC URL: jdbc:h2:mem:iotdb，用户 sa，无密码） |

## 数据字段

deviceId、seq（递增序号）、deviceTs（设备端时间戳 ms）、temperature、humidity、illuminance、serverTs（服务端收到时间 ms）。

端到端延迟 = serverTs - deviceTs，由后面的实验脚本统计。

MQTT 通道在 Baseline 跑通后再加（见 technical_route 阶段 2）。
