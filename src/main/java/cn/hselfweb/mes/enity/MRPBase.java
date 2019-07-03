package cn.hselfweb.mes.enity;

import java.util.Date;
import java.util.List;

import com.sun.javafx.beans.IDProperty;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "MRPBase")
public class MRPBase {
    @Id
    @GeneratedValue
    private Long MRPBaseId;
    private int type;
    private Date startTime;
    private Date endTime;
    private double price;
    private String remark;
    //private List<MRPExtend> mrpExtends;

    public Long getMRPBaseId() {
        return MRPBaseId;
    }

    public void setMRPBaseId(Long MRPBaseId) {
        this.MRPBaseId = MRPBaseId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
