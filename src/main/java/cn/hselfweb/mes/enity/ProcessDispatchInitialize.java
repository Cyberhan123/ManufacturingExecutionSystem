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
}