package cn.hselfweb.mes.enity;

import lombok.Data;

@Data
public class DeviceAbility{
	private int id;
	private int dId;
	private String dName;
	private int ptId;
	private double accuracy;
	private String priority;
}
