package cn.hselfweb.mes.enity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * 工序中间表
 * 序列版本编号，
 * id，
 * 工序id，
 * 产品id，
 */
@Data
@Entity
@Table(name = "CraftExtend")
public class CraftExtend implements java.io.Serializable, Comparable<CraftExtend> {
    //private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long craftExtendId;
    private Long craftBaseId;
    private Long procedureId;
    private Long paId;
    private Long pbId;
    private int priority;
    private CraftExtend extendCraftByAftEcId;
    private CraftExtend extendCraftByPreEcId;

    //===================算法所需=====================
    private Long bmrpId;
    private String wpname;//所属工件的名称
    private Long wpid;//所属工件的编号
    private String name;// 工序名称
    private String gname;// 工序名称
    private int time;// 加工时间
    private String device;// 占用设备
    //TODO:需要设置多对多
    //private List<CraftExtend> list;//设备所在的工序序列
    private int gid;//工件中的第几道工序
    private int did;//在设备上位于第几道工序
    private double xiaolv;// 效率函数
    private int endtime;//本工序在设备上的结束时间
    private int begintime;//本工序在设备上开始时间

    //TODO:需要编写比较函数
    @Override
    public int compareTo(CraftExtend o) {
        return 0;
    }

    //TODO:要写设置链表
    public void setList(List<CraftExtend> craftExtends) {
    }
}
