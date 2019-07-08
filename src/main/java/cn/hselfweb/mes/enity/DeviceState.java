package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * 设备状态
 * id，设备id，设备所属
 */

@Entity
@Table(name = "device_state", schema = "mes", catalog = "")
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

    private int dsId;

    @Id
    @javax.persistence.Column(name = "ds_id")
    public int getDsId() {
        return dsId;
    }

    public void setDsId(int dsId) {
        this.dsId = dsId;
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

    private Integer weId;

    @Basic
    @Column(name = "we_id")
    public Integer getWeId() {
        return weId;
    }

    public void setWeId(Integer weId) {
        this.weId = weId;
    }

    private Integer ceId;

    @Basic
    @Column(name = "ce_id")
    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
    }

    private Integer dsState;

    @Basic
    @Column(name = "ds_state")
    public Integer getDsState() {
        return dsState;
    }

    public void setDsState(Integer dsState) {
        this.dsState = dsState;
    }

    private Date dsStartTime;

    @Basic
    @Column(name = "ds_start_time")
    public Date getDsStartTime() {
        return dsStartTime;
    }

    public void setDsStartTime(Date dsStartTime) {
        this.dsStartTime = dsStartTime;
    }

    private Date dsEndTime;

    @Basic
    @Column(name = "ds_end_time")
    public Date getDsEndTime() {
        return dsEndTime;
    }

    public void setDsEndTime(Date dsEndTime) {
        this.dsEndTime = dsEndTime;
    }

    private Date dsPStartTime;

    @Basic
    @Column(name = "ds_p_start_time")
    public Date getDsPStartTime() {
        return dsPStartTime;
    }

    public void setDsPStartTime(Date dsPStartTime) {
        this.dsPStartTime = dsPStartTime;
    }

    private Date dsPEndTime;

    @Basic
    @Column(name = "ds_p_end_time")
    public Date getDsPEndTime() {
        return dsPEndTime;
    }

    public void setDsPEndTime(Date dsPEndTime) {
        this.dsPEndTime = dsPEndTime;
    }

    private Date dsRStartTime;

    @Basic
    @Column(name = "ds_r_start_time")
    public Date getDsRStartTime() {
        return dsRStartTime;
    }

    public void setDsRStartTime(Date dsRStartTime) {
        this.dsRStartTime = dsRStartTime;
    }

    private Date dsREndTime;

    @Basic
    @Column(name = "ds_r_end_time")
    public Date getDsREndTime() {
        return dsREndTime;
    }

    public void setDsREndTime(Date dsREndTime) {
        this.dsREndTime = dsREndTime;
    }

    private Date dsUpTime;

    @Basic
    @Column(name = "ds_up_time")
    public Date getDsUpTime() {
        return dsUpTime;
    }

    public void setDsUpTime(Date dsUpTime) {
        this.dsUpTime = dsUpTime;
    }

    private Integer sId;

    @Basic
    @Column(name = "s_id")
    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceState that = (DeviceState) o;
        return dsId == that.dsId &&
                Objects.equals(dId, that.dId) &&
                Objects.equals(weId, that.weId) &&
                Objects.equals(ceId, that.ceId) &&
                Objects.equals(dsState, that.dsState) &&
                Objects.equals(dsStartTime, that.dsStartTime) &&
                Objects.equals(dsEndTime, that.dsEndTime) &&
                Objects.equals(dsPStartTime, that.dsPStartTime) &&
                Objects.equals(dsPEndTime, that.dsPEndTime) &&
                Objects.equals(dsRStartTime, that.dsRStartTime) &&
                Objects.equals(dsREndTime, that.dsREndTime) &&
                Objects.equals(dsUpTime, that.dsUpTime) &&
                Objects.equals(sId, that.sId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dsId, dId, weId, ceId, dsState, dsStartTime, dsEndTime, dsPStartTime, dsPEndTime, dsRStartTime, dsREndTime, dsUpTime, sId);
    }
}
