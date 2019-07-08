package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;


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

    public void setWcId(Integer wcId) {
        this.wcId = wcId;
    }

    private String remark;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getRename() {
        return rename;
    }

    public void setRename(String rename) {
        this.rename = rename;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Basic
    @Column(name = "wc_id")
    public Long getWcId() {
        return wcId;
    }

    public void setWcId(Long wcId) {
        this.wcId = wcId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private int dId;

    @Id
    @javax.persistence.Column(name = "d_id")
    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
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

    private String dModel;

    @Basic
    @Column(name = "d_model")
    public String getdModel() {
        return dModel;
    }

    public void setdModel(String dModel) {
        this.dModel = dModel;
    }

    private String dSpecification;

    @Basic
    @Column(name = "d_specification")
    public String getdSpecification() {
        return dSpecification;
    }

    public void setdSpecification(String dSpecification) {
        this.dSpecification = dSpecification;
    }

    private String dRename;

    @Basic
    @Column(name = "d_rename")
    public String getdRename() {
        return dRename;
    }

    public void setdRename(String dRename) {
        this.dRename = dRename;
    }

    private Integer dTime;

    @Basic
    @Column(name = "d_time")
    public Integer getdTime() {
        return dTime;
    }

    public void setdTime(Integer dTime) {
        this.dTime = dTime;
    }

    private String dRemark;

    @Basic
    @Column(name = "d_remark")
    public String getdRemark() {
        return dRemark;
    }

    public void setdRemark(String dRemark) {
        this.dRemark = dRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return dId == device.dId &&
                Objects.equals(wcId, device.wcId) &&
                Objects.equals(dName, device.dName) &&
                Objects.equals(dModel, device.dModel) &&
                Objects.equals(dSpecification, device.dSpecification) &&
                Objects.equals(dRename, device.dRename) &&
                Objects.equals(dTime, device.dTime) &&
                Objects.equals(dRemark, device.dRemark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dId, dName, dModel, dSpecification, dRename, dTime, wcId, dRemark);
    }
}
