package cn.hselfweb.mes.repository;

import cn.hselfweb.mes.enity.Workpiece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkpieceRepository extends JpaRepository<Workpiece, Long> {
    Workpiece findWorkpieceByWorkpieceId(Long id);
}
