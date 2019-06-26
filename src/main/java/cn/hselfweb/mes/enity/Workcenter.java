package cn.hselfweb.mes.enity;


import lombok.Data;

@Data
public class Workcenter {
		private	int id;
		private	String name;	
		private String info;
		private int replace;
		private int turnCount;
		private int turnTime;
		private int staffCount;
		private int deviceCount;
		private int useDevice;
		private String type;
		private int critical;
}
