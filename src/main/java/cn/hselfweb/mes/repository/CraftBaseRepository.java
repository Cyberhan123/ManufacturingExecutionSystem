package cn.hselfweb.mes.repository;

import cn.hselfweb.mes.enity.CraftBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CraftBaseRepository extends JpaRepository<CraftBase, Long> {
    CraftBase findCraftBaseByCraftBaseId(long id);
    CraftBase findCraftBaseByWorkpieceId(Long id);
}
