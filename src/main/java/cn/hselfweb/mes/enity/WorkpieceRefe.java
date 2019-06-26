package cn.hselfweb.mes.enity;


import lombok.Data;

@Data
public class WorkpieceRefe {
		private	int id;
		private String type;
		private	int wId;
		private int ceId;
		private	double price;
		private String leadTime;
		private String uptime;
}
