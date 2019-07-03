package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


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

	public Long getProcedureTypeId() {
		return ProcedureTypeId;
	}

	public void setProcedureTypeId(Long procedureTypeId) {
		ProcedureTypeId = procedureTypeId;
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(Integer avgTime) {
		this.avgTime = avgTime;
	}

	public Integer getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(Integer maxTime) {
		this.maxTime = maxTime;
	}

	public Integer getMinTime() {
		return minTime;
	}

	public void setMinTime(Integer minTime) {
		this.minTime = minTime;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public int getTool() {
		return tool;
	}

	public void setTool(int tool) {
		this.tool = tool;
	}
}
