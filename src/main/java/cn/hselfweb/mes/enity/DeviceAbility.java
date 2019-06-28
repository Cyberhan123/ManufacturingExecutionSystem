package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 设备能力
 * id，设备id，
 */
@Data
@Entity
@Table(name = "DeviceAbility")
public class DeviceAbility {
    @Id
    @GeneratedValue
    private Long deviceAbilityId;
    private Long deviceId;
    private String Name;
    private Long procedureTypeId;
    private double accuracy;
    private String priority;
}
