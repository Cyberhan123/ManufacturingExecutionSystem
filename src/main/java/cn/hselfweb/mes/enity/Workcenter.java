package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Workcenter")
public class Workcenter {
    @Id
    @GeneratedValue
    private Long WorkcenterId;
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

    public Long getWorkcenterId() {
        return WorkcenterId;
    }

    public void setWorkcenterId(Long workcenterId) {
        WorkcenterId = workcenterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getReplace() {
        return replace;
    }

    public void setReplace(int replace) {
        this.replace = replace;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public int getTurnTime() {
        return turnTime;
    }

    public void setTurnTime(int turnTime) {
        this.turnTime = turnTime;
    }

    public int getStaffCount() {
        return staffCount;
    }

    public void setStaffCount(int staffCount) {
        this.staffCount = staffCount;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }

    public int getUseDevice() {
        return useDevice;
    }

    public void setUseDevice(int useDevice) {
        this.useDevice = useDevice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }
}
