package com.bs.iot.device;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DeviceDataRepository repository;

    public DataController(DeviceDataRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public DeviceData receive(@RequestBody DeviceData data) {
        data.setServerTs(System.currentTimeMillis());
        return repository.save(data);
    }

    @GetMapping("/latest")
    public DeviceData latest() {
        return repository.findFirstByOrderByIdDesc();
    }

    @GetMapping
    public List<DeviceData> recent() {
        return repository.findTop100ByOrderByIdDesc();
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }
}
