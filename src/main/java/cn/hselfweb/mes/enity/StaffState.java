package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Table(name = "StaffState")
@Entity
public class StaffState {
    @Id
    @GeneratedValue
    private Long StaffStateId;
    private Long sId;
    private String state;
    private String startTime;
    private String endTime;
    private String upTime;

    public Long getStaffStateId() {
        return StaffStateId;
    }

    public void setStaffStateId(Long staffStateId) {
        StaffStateId = staffStateId;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }
}