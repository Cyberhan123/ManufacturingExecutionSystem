package cn.hselfweb.mes.enity;

import lombok.Data;

@Data
public class ProcedureType {
	private int id;
	private Integer did;   //
	private String name;
	private Integer avgTime;
	private Integer maxTime;
	private Integer minTime;
	private Integer startTime;
	private Integer endTime;
	private double accuracy;  		//精度
	private int tool;
}
