package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "WorkpiecePurchase")
public class WorkpiecePurchase {
    @Id
    @GeneratedValue
    private Long WorkpiecePurchaseId;
    private String num;
    private Long wrId;
    private String type;
    private String weNum;
    private Long ceId;
    private String startTime;
    private String endTime;
    private Long mrpbId;

    public Long getWorkpiecePurchaseId() {
        return WorkpiecePurchaseId;
    }

    public void setWorkpiecePurchaseId(Long workpiecePurchaseId) {
        WorkpiecePurchaseId = workpiecePurchaseId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Long getWrId() {
        return wrId;
    }

    public void setWrId(Long wrId) {
        this.wrId = wrId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeNum() {
        return weNum;
    }

    public void setWeNum(String weNum) {
        this.weNum = weNum;
    }

    public Long getCeId() {
        return ceId;
    }

    public void setCeId(Long ceId) {
        this.ceId = ceId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getMrpbId() {
        return mrpbId;
    }

    public void setMrpbId(Long mrpbId) {
        this.mrpbId = mrpbId;
    }
}
