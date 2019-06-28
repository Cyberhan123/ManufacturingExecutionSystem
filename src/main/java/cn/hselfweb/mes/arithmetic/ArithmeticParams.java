package cn.hselfweb.mes.arithmetic;

import cn.hselfweb.mes.enity.CraftExtend;

import java.util.ArrayList;
import java.util.List;

public abstract class ArithmeticParams {
     //算法库中的算法参数的抽象类，抽象类中存放各个算法所需要的共同的参数和方法，如工作重心等
	//不能使用接口，接口中不能对参数设置get和set方法
	public  List<List<CraftExtend>> list_sum = new ArrayList<List<CraftExtend>>();// 生成特定工序包含的加工序列集合的集合    （ 每个工件的工序集未一个元素）
	public  int DeviceNumber = 0;
	public  List<List<CraftExtend>> listM_base_sum = new ArrayList<List<CraftExtend>>();// 生成设备加工序列排序集合的集合    （结果集）
	
	//get和set方法只是为了结构化
	public List<List<CraftExtend>> getList_sum() {
		return list_sum;
	}
	public void setList_sum(List<List<CraftExtend>> list_sum) {
		this.list_sum = list_sum;
	}
	public int getDeviceNumber() {
		return DeviceNumber;
	}
	public void setDeviceNumber(int deviceNumber) {
		DeviceNumber = deviceNumber;
	}
	public List<List<CraftExtend>> getListM_base_sum() {
		return listM_base_sum;
	}
	public void setListM_base_sum(List<List<CraftExtend>> listM_base_sum) {
		this.listM_base_sum = listM_base_sum;
	}
	
	
}
