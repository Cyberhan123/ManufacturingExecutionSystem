package cn.hselfweb.mes.repository;

import cn.hselfweb.mes.enity.Device;
import cn.hselfweb.mes.enity.Procedure;
import cn.hselfweb.mes.enity.ProcedureType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedureTypeRepository extends JpaRepository<ProcedureType, Long> {
    Device findDeviceByProcedureTypeId(Long id);

    ProcedureType findProcedureTypeByProcedureTypeId(Long id);
}
