package cn.hselfweb.mes.repository;

import cn.hselfweb.mes.enity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findDeviceByDeviceId(Long id);
}
