package cn.hselfweb.mes.repository;

import cn.hselfweb.mes.enity.MRPExtend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MRPExtendRepository extends JpaRepository<MRPExtend, Long> {
    List<MRPExtend> findMRPExtendsByMRPEBaseId(Long id);
}
