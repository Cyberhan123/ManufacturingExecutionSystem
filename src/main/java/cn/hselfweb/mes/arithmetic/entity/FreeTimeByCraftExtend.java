package cn.hselfweb.mes.arithmetic.entity;

import cn.hselfweb.mes.enity.CraftExtend;

import java.util.List;

public class FreeTimeByCraftExtend implements Comparable<FreeTimeByCraftExtend>{
	
	private String device;// 占用设备
	private List<CraftExtend> listdevice;//占用设备的工序排序
	private int endtime;//空闲结束时间
	private int begintime;//空闲开始时间
	private int time;//空间持续时间
	private int did;//在设备上的下一道工序的编号
	private CraftExtend pregongxu;//前一道工序
	private CraftExtend postgongxu;//后一道工序
	
	public List<CraftExtend> getListdevice() {
		return listdevice;
	}
	public void setListdevice(List<CraftExtend> listdevice) {
		this.listdevice = listdevice;
	}

	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public int getEndtime() {
		return endtime;
	}
	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}
	public int getBegintime() {
		return begintime;
	}
	public void setBegintime(int begintime) {
		this.begintime = begintime;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public CraftExtend getPregongxu() {
		return pregongxu;
	}
	public void setPregongxu(CraftExtend pregongxu) {
		this.pregongxu = pregongxu;
	}
	public CraftExtend getPostgongxu() {
		return postgongxu;
	}
	public void setPostgongxu(CraftExtend postgongxu) {
		this.postgongxu = postgongxu;
	}

	public int compareTo(FreeTimeByCraftExtend o) {
		if(o!=null){
            if(this.getTime()>o.getTime()){
               return 1;
            }else if(this.getTime()==o.getTime()){
               return 0;
            }
       }
        return -1;
	}

}
