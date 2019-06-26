package cn.hselfweb.mes.enity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Device{
	private int id;
	private String name;
	private String model;
	private String specification;
	private String rename;
	private Integer time;
	private int wcId;
	private String remark;
	//算法所需
	private List<CraftExtend> listgx = new ArrayList<CraftExtend>();//工序队列
    private List<CraftExtend> listalreadygx = new ArrayList<CraftExtend>();//工序队列。GA算法中使用
	// Constructors
    private int beginTime;//动态调度时设备的开始加工时间
}
