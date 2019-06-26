package cn.hselfweb.mes.enity;

import lombok.Data;

@Data
public class MRPDelay {
	
	private int id;
	private int mrpbId;
	private double base;
	private float factor;
	private double max;
}
