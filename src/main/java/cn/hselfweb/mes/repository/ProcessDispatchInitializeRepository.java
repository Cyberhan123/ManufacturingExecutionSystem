package cn.hselfweb.mes.repository;

import cn.hselfweb.mes.enity.ProcessDispatchResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessDispatchInitializeRepository extends JpaRepository<ProcessDispatchResult, Long> {
}
