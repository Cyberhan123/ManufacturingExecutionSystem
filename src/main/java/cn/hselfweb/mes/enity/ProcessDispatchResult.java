package cn.hselfweb.mes.enity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * ProcessDispatchResult entity. @author MyEclipse Persistence Tools
 */


public class ProcessDispatchResult implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long processDispatchResultId;
    private MRPBase mrpBase;
    private CraftExtend extendCraft;
    private Workpiece workpiece;
    private Staff person;
    private Device baseDevice;
    private Integer pdrStartTimeP;
    private Integer pdrEndTimeP;
    private CraftExtend pdrPreEc;
    private Integer pdrPreEcTime;
    private CraftExtend pdrAftEc;
    private Integer pdrAftEcTime;

    public MRPBase getMrpBase() {
        return mrpBase;
    }

    public void setMrpBase(MRPBase mrpBase) {
        this.mrpBase = mrpBase;
    }

    public CraftExtend getExtendCraft() {
        return extendCraft;
    }

    public void setExtendCraft(CraftExtend extendCraft) {
        this.extendCraft = extendCraft;
    }

    public Workpiece getWorkpiece() {
        return workpiece;
    }

    public void setWorkpiece(Workpiece workpiece) {
        this.workpiece = workpiece;
    }

    public Staff getPerson() {
        return person;
    }

    public void setPerson(Staff person) {
        this.person = person;
    }

    public Device getBaseDevice() {
        return baseDevice;
    }

    public void setBaseDevice(Device baseDevice) {
        this.baseDevice = baseDevice;
    }

    public CraftExtend getPdrPreEc() {
        return pdrPreEc;
    }

    public void setPdrPreEc(CraftExtend pdrPreEc) {
        this.pdrPreEc = pdrPreEc;
    }

    public CraftExtend getPdrAftEc() {
        return pdrAftEc;
    }

    public void setPdrAftEc(CraftExtend pdrAftEc) {
        this.pdrAftEc = pdrAftEc;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getProcessDispatchResultId() {
        return processDispatchResultId;
    }

    public void setProcessDispatchResultId(Long processDispatchResultId) {
        this.processDispatchResultId = processDispatchResultId;
    }

    public Integer getPdrStartTimeP() {
        return pdrStartTimeP;
    }

    public void setPdrStartTimeP(Integer pdrStartTimeP) {
        this.pdrStartTimeP = pdrStartTimeP;
    }

    public Integer getPdrEndTimeP() {
        return pdrEndTimeP;
    }

    public void setPdrEndTimeP(Integer pdrEndTimeP) {
        this.pdrEndTimeP = pdrEndTimeP;
    }

    public Integer getPdrPreEcTime() {
        return pdrPreEcTime;
    }

    public void setPdrPreEcTime(Integer pdrPreEcTime) {
        this.pdrPreEcTime = pdrPreEcTime;
    }

    public Integer getPdrAftEcTime() {
        return pdrAftEcTime;
    }

    public void setPdrAftEcTime(Integer pdrAftEcTime) {
        this.pdrAftEcTime = pdrAftEcTime;
    }
}