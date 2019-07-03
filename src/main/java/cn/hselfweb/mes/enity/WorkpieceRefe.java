package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WorkpieceRefe")
public class WorkpieceRefe {
    @Id
    @GeneratedValue
    private Long WorkpieceRefeId;
    private String type;
    private Long wId;
    private Long ceId;
    private double price;
    private String leadTime;
    private String uptime;

    public Long getWorkpieceRefeId() {
        return WorkpieceRefeId;
    }

    public void setWorkpieceRefeId(Long workpieceRefeId) {
        WorkpieceRefeId = workpieceRefeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getwId() {
        return wId;
    }

    public void setwId(Long wId) {
        this.wId = wId;
    }

    public Long getCeId() {
        return ceId;
    }

    public void setCeId(Long ceId) {
        this.ceId = ceId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }
}
