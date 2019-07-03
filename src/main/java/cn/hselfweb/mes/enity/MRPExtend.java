package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "MRPExtend")
public class MRPExtend {
    @Id
    @GeneratedValue
    private Long MRPExtendId;
    private Long MRPEBaseId;
    private Long wid;
    private int count;
    private Date startTime;
    private Date endTime;

    public Long getMRPExtendId() {
        return MRPExtendId;
    }

    public void setMRPExtendId(Long MRPExtendId) {
        this.MRPExtendId = MRPExtendId;
    }

    public Long getMRPEBaseId() {
        return MRPEBaseId;
    }

    public void setMRPEBaseId(Long MRPEBaseId) {
        this.MRPEBaseId = MRPEBaseId;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
