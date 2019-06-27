package cn.hselfweb.mes.arithmetic.entity;

import cn.hselfweb.mes.enity.CraftExtend;
import lombok.Data;

import java.util.List;

@Data
public class FreeTimeByCraftExtend implements Comparable<FreeTimeByCraftExtend> {

    private String device;// 占用设备
    private List<CraftExtend> listdevice;//占用设备的工序排序
    private int endtime;//空闲结束时间
    private int begintime;//空闲开始时间
    private int time;//空间持续时间
    private int did;//在设备上的下一道工序的编号
    private CraftExtend pregongxu;//前一道工序
    private CraftExtend postgongxu;//后一道工序

    public int compareTo(FreeTimeByCraftExtend o) {
        if (o != null) {
            if (this.getTime() > o.getTime()) {
                return 1;
            } else if (this.getTime() == o.getTime()) {
                return 0;
            }
        }
        return -1;
    }

}
