package cn.hselfweb.mes.enity;


import lombok.Data;

@Data
public class Workpiece {
		private	int id;
		private	String name;		
		private int avgTime;
		private int maxTime;
		private int minTime;
		private double price;
		private double weight;
		private String type;
}
