package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "DeviceAbility")

public class DeviceAbility {
    @Id
    @GeneratedValue
    private Long id;
    private Long dId;
    private String dName;
    private Long ptId;
    private double accuracy;
    private String priority;
}
