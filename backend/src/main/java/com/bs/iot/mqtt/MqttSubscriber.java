package com.bs.iot.mqtt;

import com.bs.iot.device.DeviceData;
import com.bs.iot.device.DeviceDataRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriber {

    private final DeviceDataRepository repository;
    private MqttClient client;

    public MqttSubscriber(DeviceDataRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void start() {
        try {
            client = new MqttClient("tcp://localhost:1883", "iot-backend-" + System.currentTimeMillis(),
                    new MqttDefaultPersistence());
            MqttConnectOptions opts = new MqttConnectOptions();
            opts.setAutomaticReconnect(true);
            opts.setCleanSession(true);
            client.connect(opts);
            client.subscribe("env/+/data", (topic, message) -> {
                try {
                    JSONObject obj = new JSONObject(new String(message.getPayload()));
                    DeviceData data = new DeviceData();
                    data.setDeviceId(obj.optString("deviceId", "unknown"));
                    data.setSeq(obj.optLong("seq", 0));
                    data.setDeviceTs(obj.optLong("deviceTs", 0));
                    data.setTemperature(obj.optDouble("temperature", 0));
                    data.setHumidity(obj.optDouble("humidity", 0));
                    data.setIlluminance(obj.optDouble("illuminance", 0));
                    data.setServerTs(System.currentTimeMillis());
                    repository.save(data);
                    System.out.println("[MQTT] 收到 " + topic + " seq=" + data.getSeq());
                } catch (Exception e) {
                    System.out.println("[MQTT] 解析失败: " + e.getMessage());
                }
            });
            System.out.println("[MQTT] 已连接 broker，订阅 env/+/data");
        } catch (Exception e) {
            System.out.println("[MQTT] 启动失败（broker 没起也不影响 HTTP 通道）: " + e.getMessage());
        }
    }

    @PreDestroy
    public void stop() {
        try {
            if (client != null && client.isConnected()) {
                client.disconnect();
            }
        } catch (Exception ignored) {
        }
    }
}
