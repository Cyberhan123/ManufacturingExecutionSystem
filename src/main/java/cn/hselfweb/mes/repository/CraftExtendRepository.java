package cn.hselfweb.mes.repository;

import cn.hselfweb.mes.enity.CraftExtend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CraftExtendRepository extends JpaRepository<CraftExtend, Long> {
    List<CraftExtend> findCraftExtendsByCraftBaseId(Long id);
CraftExtend findCraftExtendsByPbId(Long id);
}
