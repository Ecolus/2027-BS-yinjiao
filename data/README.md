# data/ 说明

本目录存放小型样例数据。**几 GB 的数据集不要上传 GitHub**，应在 .gitignore 中排除，仅保留在本地。

- `metadata/`：数据字段说明、采集时间、设备 ID 等元信息。
- `samples/`：小型样例数据（几十条 JSON 即可，用于开发调试）。

样例数据格式：

```json
{
  "device_id": "esp8266_01",
  "timestamp": "2026-09-25T10:00:00+08:00",
  "temperature": 26.5,
  "humidity": 62.3,
  "illuminance": 320.0
}
```
