package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProcessDispatchResult entity. @author MyEclipse Persistence Tools
 */

@Data
@Entity
@Table(name = "ProcessDispatchResult")
public class ProcessDispatchResult implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long processDispatchResultId;
//    private MRPBase mrpBase;
//    private CraftExtend extendCraft;
//    private Workpiece workpiece;
//    private Staff person;
//    private Device baseDevice;
    private Integer pdrStartTimeP;
    private Integer pdrEndTimeP;
//    private CraftExtend pdrPreEc;
    private Integer pdrPreEcTime;
//    private CraftExtend pdrAftEc;
    private Integer pdrAftEcTime;

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