package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ProcedureType")
public class ProcedureType {
	@Id
	@GeneratedValue
	private Long ProcedureTypeId;
	private Long did;   //
	private String name;
	private Integer avgTime;
	private Integer maxTime;
	private Integer minTime;
	private Integer startTime;
	private Integer endTime;
	private double accuracy;  		//精度
	private int tool;
}
