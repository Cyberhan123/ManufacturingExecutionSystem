package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "Staff")
@Entity
public class Staff {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String sex;
    private Long age;
    private double salaryBase;
    private double salaryWork;
    private double salaryExtra;
    private Long workTime;
    private String position;
}