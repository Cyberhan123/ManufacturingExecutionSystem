package cn.hselfweb.mes.enity;

import java.util.Date;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.Data;

@Data
public class MRPBase {
		private	int id;
		private int type;
		private Date startTime;
		private Date endTime;
		private	double price;
		private String remark;
		private List<MRPExtend> mrpExtends;
}
