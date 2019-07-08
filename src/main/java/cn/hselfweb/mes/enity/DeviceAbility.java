package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * 设备能力
 * id，设备id，
 */

@Entity
@Table(name = "device_ability", schema = "mes", catalog = "")
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

    private int daId;

    @Id
    @javax.persistence.Column(name = "da_id")
    public int getDaId() {
        return daId;
    }

    public void setDaId(int daId) {
        this.daId = daId;
    }

    private Integer dId;

    @Basic
    @Column(name = "d_id")
    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    private String dName;

    @Basic
    @Column(name = "d_name")
    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    private Integer ptId;

    @Basic
    @Column(name = "pt_id")
    public Integer getPtId() {
        return ptId;
    }

    public void setPtId(Integer ptId) {
        this.ptId = ptId;
    }

    private Integer daAccuracy;

    @Basic
    @Column(name = "da_accuracy")
    public Integer getDaAccuracy() {
        return daAccuracy;
    }

    public void setDaAccuracy(Integer daAccuracy) {
        this.daAccuracy = daAccuracy;
    }

    private String daPriority;

    @Basic
    @Column(name = "da_priority")
    public String getDaPriority() {
        return daPriority;
    }

    public void setDaPriority(String daPriority) {
        this.daPriority = daPriority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceAbility that = (DeviceAbility) o;
        return daId == that.daId &&
                Objects.equals(dId, that.dId) &&
                Objects.equals(dName, that.dName) &&
                Objects.equals(ptId, that.ptId) &&
                Objects.equals(daAccuracy, that.daAccuracy) &&
                Objects.equals(daPriority, that.daPriority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(daId, dId, dName, ptId, daAccuracy, daPriority);
    }
}
