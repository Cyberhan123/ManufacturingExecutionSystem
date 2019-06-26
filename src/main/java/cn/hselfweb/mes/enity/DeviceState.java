package cn.hselfweb.mes.enity;

import lombok.Data;

import java.sql.Date;
@Data
public class DeviceState {
	private int id;
	private int dId;
	private int weId;
	private int ceId;
	private int state;
	private Date startTime;
	private Date endTime;
	private Date pStartTime;
	private Date pEndTime;
	private Date rStartTime;
	private Date rEndTime;
	private int sId;
	private Date upTime;
}
