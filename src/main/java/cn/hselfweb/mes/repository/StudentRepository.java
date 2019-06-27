package cn.hselfweb.mes.repository;

import cn.hselfweb.mes.enity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
