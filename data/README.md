# data/ 说明

本目录只放小型样例数据。大文件（比如几 GB 的数据集）不要传 GitHub，记得在 .gitignore 里排除，只保留在本地。

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
