package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProcessDispatchInitialize entity. @author MyEclipse Persistence Tools
 */


public class ProcessDispatchInitialize implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long pdiId;
    private MRPBase mrpBase;
    private CraftExtend craftextend;
    private Workpiece workpiece;
    private Staff staff;
    private Device device;

    public MRPBase getMrpBase() {
        return mrpBase;
    }

    public void setMrpBase(MRPBase mrpBase) {
        this.mrpBase = mrpBase;
    }

    public CraftExtend getCraftextend() {
        return craftextend;
    }

    public void setCraftextend(CraftExtend craftextend) {
        this.craftextend = craftextend;
    }

    public Workpiece getWorkpiece() {
        return workpiece;
    }

    public void setWorkpiece(Workpiece workpiece) {
        this.workpiece = workpiece;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    private Integer pdiTotalTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getPdiId() {
        return pdiId;
    }

    public void setPdiId(Long pdiId) {
        this.pdiId = pdiId;
    }

    public Integer getPdiTotalTime() {
        return pdiTotalTime;
    }

    public void setPdiTotalTime(Integer pdiTotalTime) {
        this.pdiTotalTime = pdiTotalTime;
    }
}