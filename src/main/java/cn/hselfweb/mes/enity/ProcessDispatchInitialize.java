package cn.hselfweb.mes.enity;

import lombok.Data;

/**
 * ProcessDispatchInitialize entity. @author MyEclipse Persistence Tools
 */

@Data
public class ProcessDispatchInitialize implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pdiId;
	private MRPBase mrpBase;
	private CraftExtend craftextend;
	private Workpiece workpiece;
	private Staff staff;
	private Device device;
	private Integer pdiTotalTime;
}