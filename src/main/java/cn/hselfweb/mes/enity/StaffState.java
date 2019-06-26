package cn.hselfweb.mes.enity;

import lombok.Data;

import java.sql.Date;




@Data
public class StaffState {
		private	int id;
		private	int sId;	
		private	String	state;
		private	String startTime;
		private	String	endTime;
		private	String	upTime;

		}		