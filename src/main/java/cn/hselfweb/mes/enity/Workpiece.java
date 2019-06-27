package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "Workpiece")
@Entity
public class Workpiece {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int avgTime;
    private int maxTime;
    private int minTime;
    private double price;
    private double weight;
    private String type;
}
