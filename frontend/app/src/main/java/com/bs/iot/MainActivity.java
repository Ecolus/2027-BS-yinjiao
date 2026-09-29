package com.bs.iot;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    // Android 模拟器访问电脑上的服务端用 10.0.2.2；真机调试时改成电脑的局域网 IP
    private static final String BASE_URL = "http://10.0.2.2:8080/api/data/latest";

    private final OkHttpClient client = new OkHttpClient();
    private final Handler handler = new Handler(Looper.getMainLooper());

    private TextView tvStatus, tvTemp, tvHum, tvDelay, tvSeq;

    private final Runnable pollTask = new Runnable() {
        @Override
        public void run() {
            fetchOnce();
            handler.postDelayed(this, 1000); // 每秒轮询一次
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

    private void fetchOnce() {
        Request request = new Request.Builder().url(BASE_URL).build();
        new Thread(() -> {
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful() || response.body() == null) {
                    updateUi("服务端无数据", "--", "--", "--", "--");
                    return;
                }
                JSONObject obj = new JSONObject(response.body().string());
                double temp = obj.getDouble("temperature");
                double hum = obj.getDouble("humidity");
                long seq = obj.getLong("seq");
                long deviceTs = obj.getLong("deviceTs");
                long serverTs = obj.getLong("serverTs");
                long delay = serverTs - deviceTs;
                updateUi("已连接",
                        String.format("%.1f ℃", temp),
                        String.format("%.1f %%", hum),
                        delay + " ms",
                        "seq=" + seq);
            } catch (IOException e) {
                updateUi("连接失败：" + e.getMessage(), "--", "--", "--", "--");
            } catch (Exception e) {
                updateUi("解析失败：" + e.getMessage(), "--", "--", "--", "--");
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
