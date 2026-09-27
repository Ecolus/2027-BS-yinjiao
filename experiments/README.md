# experiments/ 实验目录说明

每个实验一个文件夹：

- `baseline/`：Baseline 基线实验，验证系统可用性。
- `exp01_xxx/`：主实验 1（如 MQTT vs HTTP 频率对比）。
- `exp02_xxx/`：参数实验（QoS / payload）。
- `exp03_xxx/`：异常实验（断网 / 弱网）。

每个实验文件夹里统一放：

- `config.yaml`：本次实验的配置（频率、QoS、payload、网络环境等）。
- `command.txt`：复现本次实验要跑的启动命令。
- `notes.md`：按实验记录模板写 Purpose / Configuration / Result / Conclusion / Problems。
- `metrics.csv`：量化指标的原始数据。
