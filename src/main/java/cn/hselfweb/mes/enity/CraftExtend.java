package cn.hselfweb.mes.enity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 工序中间表
 * 序列版本编号，
 * id，
 * 工序id，
 * 产品id，
 */
@Entity
@Table(name = "craft_extend", schema = "mes", catalog = "")
public class CraftExtend implements Serializable, Comparable<CraftExtend> {
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

    public List<CraftExtend> getList() {
        return null;
    }

    public Long getCraftExtendId() {
        return craftExtendId;
    }

    public void setCraftExtendId(Long craftExtendId) {
        this.craftExtendId = craftExtendId;
    }

    public Long getCraftBaseId() {
        return craftBaseId;
    }

    public void setCraftBaseId(Long craftBaseId) {
        this.craftBaseId = craftBaseId;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public Long getPaId() {
        return paId;
    }

    public void setPaId(Long paId) {
        this.paId = paId;
    }

    public Long getPbId() {
        return pbId;
    }

    public void setPbId(Long pbId) {
        this.pbId = pbId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public CraftExtend getExtendCraftByAftEcId() {
        return extendCraftByAftEcId;
    }

    public void setExtendCraftByAftEcId(CraftExtend extendCraftByAftEcId) {
        this.extendCraftByAftEcId = extendCraftByAftEcId;
    }

    public CraftExtend getExtendCraftByPreEcId() {
        return extendCraftByPreEcId;
    }

    public void setExtendCraftByPreEcId(CraftExtend extendCraftByPreEcId) {
        this.extendCraftByPreEcId = extendCraftByPreEcId;
    }

    public Long getBmrpId() {
        return bmrpId;
    }

    public void setBmrpId(Long bmrpId) {
        this.bmrpId = bmrpId;
    }

    public String getWpname() {
        return wpname;
    }

    public void setWpname(String wpname) {
        this.wpname = wpname;
    }

    public Long getWpid() {
        return wpid;
    }

    public void setWpid(Long wpid) {
        this.wpid = wpid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public double getXiaolv() {
        return xiaolv;
    }

    public void setXiaolv(double xiaolv) {
        this.xiaolv = xiaolv;
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

    private int ceId;

    @Id
    @javax.persistence.Column(name = "ce_id")
    public int getCeId() {
        return ceId;
    }

    public void setCeId(int ceId) {
        this.ceId = ceId;
    }

    private Integer cbId;

    @Basic
    @Column(name = "cb_id")
    public Integer getCbId() {
        return cbId;
    }

    public void setCbId(Integer cbId) {
        this.cbId = cbId;
    }

    private Integer pId;

    @Basic
    @Column(name = "p_id")
    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    private Integer pAId;

    @Basic
    @Column(name = "p_a_id")
    public Integer getpAId() {
        return pAId;
    }

    public void setpAId(Integer pAId) {
        this.pAId = pAId;
    }

    private Integer pBId;

    @Basic
    @Column(name = "p_b_id")
    public Integer getpBId() {
        return pBId;
    }

    public void setpBId(Integer pBId) {
        this.pBId = pBId;
    }

    private Integer cePriority;

    @Basic
    @Column(name = "ce_priority")
    public Integer getCePriority() {
        return cePriority;
    }

    public void setCePriority(Integer cePriority) {
        this.cePriority = cePriority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CraftExtend that = (CraftExtend) o;
        return ceId == that.ceId &&
                Objects.equals(cbId, that.cbId) &&
                Objects.equals(pId, that.pId) &&
                Objects.equals(pAId, that.pAId) &&
                Objects.equals(pBId, that.pBId) &&
                Objects.equals(cePriority, that.cePriority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ceId, cbId, pId, pAId, pBId, cePriority);
    }
}
