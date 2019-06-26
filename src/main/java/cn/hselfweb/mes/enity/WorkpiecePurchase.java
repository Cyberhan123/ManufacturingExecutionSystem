package cn.hselfweb.mes.enity;


import lombok.Data;

@Data
public class WorkpiecePurchase {
	private int id;
	private String num;
	private int wrId;
	private String type;
	private String weNum;
	private int ceId;
	private String startTime;
	private String endTime;
	private int mrpbId;
}
