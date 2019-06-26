package cn.hselfweb.mes.enity;

import lombok.Data;

/**
 * ProcessDispatchResult entity. @author MyEclipse Persistence Tools
 */

@Data
public class ProcessDispatchResult implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pdrId;
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