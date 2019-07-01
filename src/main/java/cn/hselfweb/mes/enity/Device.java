package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Device")
public class Device {
    @Id
    @GeneratedValue
    private Long deviceId;
    private String name;
    private String model;
    private String specification;
    private String rename;
    private Integer time;
    private Long wcId;
    private String remark;

}
