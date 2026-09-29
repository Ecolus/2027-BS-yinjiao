package com.bs.iot.device;

import jakarta.persistence.*;

@Entity
public class DeviceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private Long seq;
    private Long deviceTs;
    private double temperature;
    private double humidity;
    private double illuminance;
    private Long serverTs;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public Long getSeq() { return seq; }
    public void setSeq(Long seq) { this.seq = seq; }

    public Long getDeviceTs() { return deviceTs; }
    public void setDeviceTs(Long deviceTs) { this.deviceTs = deviceTs; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }

    public double getIlluminance() { return illuminance; }
    public void setIlluminance(double illuminance) { this.illuminance = illuminance; }

    public Long getServerTs() { return serverTs; }
    public void setServerTs(Long serverTs) { this.serverTs = serverTs; }
}
