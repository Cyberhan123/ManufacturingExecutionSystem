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

    public Long getDeviceAbilityId() {
        return deviceAbilityId;
    }

    public void setDeviceAbilityId(Long deviceAbilityId) {
        this.deviceAbilityId = deviceAbilityId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getProcedureTypeId() {
        return procedureTypeId;
    }

    public void setProcedureTypeId(Long procedureTypeId) {
        this.procedureTypeId = procedureTypeId;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
