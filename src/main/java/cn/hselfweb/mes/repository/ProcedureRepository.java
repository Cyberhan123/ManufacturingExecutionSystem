package cn.hselfweb.mes.repository;

import cn.hselfweb.mes.enity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    Procedure findProcedureByProcedureId(Long id);
}
