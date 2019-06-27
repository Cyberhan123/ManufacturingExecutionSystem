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
    private Long id;
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
}