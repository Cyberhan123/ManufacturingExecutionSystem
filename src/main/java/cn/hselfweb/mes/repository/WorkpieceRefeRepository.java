package cn.hselfweb.mes.repository;

import cn.hselfweb.mes.enity.Workpiece;
import cn.hselfweb.mes.enity.WorkpieceRefe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkpieceRefeRepository extends JpaRepository<WorkpieceRefe, Long> {
    Workpiece findWorkpieceRefeByWorkpieceRefeId(Long id);

    Workpiece findWorkpieceRefesByWId(Long id);
}

