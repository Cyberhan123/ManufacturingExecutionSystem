package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Device")
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String model;
    private String specification;
    private String rename;
    private Integer time;
    private Long wcId;
    private String remark;
    //算法所需

    private List<CraftExtend> listgx = new ArrayList<CraftExtend>();//工序队列
    private List<CraftExtend> listalreadygx = new ArrayList<CraftExtend>();//工序队列。GA算法中使用
    // Constructors
    private int beginTime;//动态调度时设备的开始加工时间
}
