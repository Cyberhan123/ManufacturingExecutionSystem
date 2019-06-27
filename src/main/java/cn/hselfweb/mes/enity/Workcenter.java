package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Workcenter")
public class Workcenter {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String info;
    private int replace;
    private int turnCount;
    private int turnTime;
    private int staffCount;
    private int deviceCount;
    private int useDevice;
    private String type;
    private int critical;
}
