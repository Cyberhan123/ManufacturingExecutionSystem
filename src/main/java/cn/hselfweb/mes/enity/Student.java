package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String sno;
    private String sname;
    private int sage;
    private String smajor;
}