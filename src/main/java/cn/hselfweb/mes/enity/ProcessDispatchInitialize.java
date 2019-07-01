package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProcessDispatchInitialize entity. @author MyEclipse Persistence Tools
 */

@Data
@Table
@Entity
public class ProcessDispatchInitialize implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long pdiId;
    //private MRPBase mrpBase;
    //private CraftExtend craftextend;
    //private Workpiece workpiece;
    //private Staff staff;
    //private Device device;
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