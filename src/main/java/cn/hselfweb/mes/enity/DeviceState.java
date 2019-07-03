package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * 设备状态
 * id，设备id，设备所属
 */

@Entity
@Table(name = "DeviceState")
public class DeviceState {
    @Id
    @GeneratedValue
    private Long deviceStateId;
    private Long deviceId;
    private Long workpieceExtendId;
    private Long craftExtendId;
    private int state;
    private Date startTime;
    private Date endTime;
    private Date pStartTime;
    private Date pEndTime;
    private Date rStartTime;
    private Date rEndTime;
    private Long staffId;
    private Date upTime;

    public Long getDeviceStateId() {
        return deviceStateId;
    }

    public void setDeviceStateId(Long deviceStateId) {
        this.deviceStateId = deviceStateId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getWorkpieceExtendId() {
        return workpieceExtendId;
    }

    public void setWorkpieceExtendId(Long workpieceExtendId) {
        this.workpieceExtendId = workpieceExtendId;
    }

    public Long getCraftExtendId() {
        return craftExtendId;
    }

    public void setCraftExtendId(Long craftExtendId) {
        this.craftExtendId = craftExtendId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getpStartTime() {
        return pStartTime;
    }

    public void setpStartTime(Date pStartTime) {
        this.pStartTime = pStartTime;
    }

    public Date getpEndTime() {
        return pEndTime;
    }

    public void setpEndTime(Date pEndTime) {
        this.pEndTime = pEndTime;
    }

    public Date getrStartTime() {
        return rStartTime;
    }

    public void setrStartTime(Date rStartTime) {
        this.rStartTime = rStartTime;
    }

    public Date getrEndTime() {
        return rEndTime;
    }

    public void setrEndTime(Date rEndTime) {
        this.rEndTime = rEndTime;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }
}
