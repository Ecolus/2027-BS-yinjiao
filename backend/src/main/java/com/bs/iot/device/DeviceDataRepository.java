package com.bs.iot.device;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceDataRepository extends JpaRepository<DeviceData, Long> {
    DeviceData findFirstByOrderByIdDesc();
    List<DeviceData> findTop100ByOrderByIdDesc();
    long count();
}
