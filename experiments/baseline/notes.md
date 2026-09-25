# Experiment ID: EXP-00

## Purpose
验证 数据源 → 通信 → 服务端 → Android App 全链路可用；同时跑通 MQTT 与 HTTP 两条通道。

## Compared with
无（Baseline 基线，后续所有实验都与本实验的系统状态对比）。

## Configuration
见 [config.yaml](config.yaml)。

## Dataset
模拟数据源生成的温湿度 JSON，每条约 256B。

## Random seed
（如 simulator 中有随机抖动，启动时记录 seed）

## Command
见 [command.txt](command.txt)。

## Result
见 [metrics.csv](metrics.csv)。

## Conclusion
（Baseline 跑通后填写：链路是否打通、是否丢数据、延迟大致范围）

## Problems
（记录跑通过程中遇到的报错与解决方法）
