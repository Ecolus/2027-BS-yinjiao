package com.bs.iot;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    // 模拟器访问电脑后端用 10.0.2.2
    private static final String HTTP_URL = "http://10.0.2.2:8080/api/data/latest";
    private static final String MQTT_URI = "tcp://10.0.2.2:1883";
    private static final String MQTT_TOPIC = "env/esp8266_01/data";

    private final OkHttpClient client = new OkHttpClient();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private MqttAndroidClient mqttClient;

    private TextView tvStatus, tvTemp, tvHum, tvDelay, tvSeq;

    private final Runnable pollTask = new Runnable() {
        @Override
        public void run() {
            fetchHttp();
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        tvTemp = findViewById(R.id.tvTemp);
        tvHum = findViewById(R.id.tvHum);
        tvDelay = findViewById(R.id.tvDelay);
        tvSeq = findViewById(R.id.tvSeq);

        connectMqtt();
    }

    private void connectMqtt() {
        mqttClient = new MqttAndroidClient(this, MQTT_URI, "app-" + System.currentTimeMillis());
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                handler.post(() -> tvStatus.setText("MQTT 断开，重连中..."));
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                try {
                    JSONObject obj = new JSONObject(new String(message.getPayload()));
                    long delay = System.currentTimeMillis() - obj.getLong("deviceTs");
                    updateUi("MQTT 推送",
                            String.format("%.1f ℃", obj.getDouble("temperature")),
                            String.format("%.1f %%", obj.getDouble("humidity")),
                            delay + " ms",
                            "seq=" + obj.getLong("seq"));
                } catch (Exception e) {
                    // 忽略解析错误
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

        MqttConnectOptions opts = new MqttConnectOptions();
        opts.setAutomaticReconnect(true);
        opts.setCleanSession(true);
        try {
            mqttClient.connect(opts, null, new org.eclipse.paho.client.mqttv3.IMqttActionListener() {
                @Override
                public void onSuccess(org.eclipse.paho.client.mqttv3.IMqttToken asyncActionToken) {
                    try {
                        mqttClient.subscribe(MQTT_TOPIC, 1);
                        handler.post(() -> tvStatus.setText("MQTT 已连接"));
                    } catch (Exception ignored) {
                    }
                }

                @Override
                public void onFailure(org.eclipse.paho.client.mqttv3.IMqttToken asyncActionToken, Throwable exception) {
                    handler.post(() -> tvStatus.setText("MQTT 连接失败，仅 HTTP 轮询"));
                }
            });
        } catch (Exception e) {
            tvStatus.setText("MQTT 启动失败，仅 HTTP 轮询");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(pollTask);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(pollTask);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (mqttClient != null) mqttClient.disconnect();
        } catch (Exception ignored) {
        }
    }

    private void fetchHttp() {
        Request request = new Request.Builder().url(HTTP_URL).build();
        new Thread(() -> {
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful() || response.body() == null) return;
                JSONObject obj = new JSONObject(response.body().string());
                long delay = System.currentTimeMillis() - obj.getLong("deviceTs");
                // 只有 MQTT 没推过更新时，HTTP 轮询才刷新（避免覆盖 MQTT 的实时值）
                updateUi("HTTP 轮询",
                        String.format("%.1f ℃", obj.getDouble("temperature")),
                        String.format("%.1f %%", obj.getDouble("humidity")),
                        delay + " ms",
                        "seq=" + obj.getLong("seq"));
            } catch (Exception ignored) {
            }
        }).start();
    }

    private void updateUi(String status, String temp, String hum, String delay, String seq) {
        handler.post(() -> {
            tvStatus.setText(status);
            tvTemp.setText("温度：" + temp);
            tvHum.setText("湿度：" + hum);
            tvDelay.setText("端到端延迟：" + delay);
            tvSeq.setText(seq);
        });
    }
}
