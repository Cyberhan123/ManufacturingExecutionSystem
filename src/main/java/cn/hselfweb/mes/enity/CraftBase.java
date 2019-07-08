package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 基本工艺表
 * 编号，工艺名称，工作车间，耗费时间
 */

@Entity
@Table(name = "craft_base", schema = "mes", catalog = "")
public class CraftBase {
    @Id
    @GeneratedValue
    private Long craftBaseId;
    private String name;
    private Long workpieceId;
    private Date time;

    public Long getCraftBaseId() {
        return craftBaseId;
    }

    public void setCraftBaseId(Long craftBaseId) {
        this.craftBaseId = craftBaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWorkpieceId() {
        return workpieceId;
    }

    public void setWorkpieceId(Long workpieceId) {
        this.workpieceId = workpieceId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    private int cbId;

    @Id
    @javax.persistence.Column(name = "cb_id")
    public int getCbId() {
        return cbId;
    }

    public void setCbId(int cbId) {
        this.cbId = cbId;
    }

    private String cbName;

    @Basic
    @Column(name = "cb_name")
    public String getCbName() {
        return cbName;
    }

    public void setCbName(String cbName) {
        this.cbName = cbName;
    }

    private Integer wId;

    @Basic
    @Column(name = "w_id")
    public Integer getwId() {
        return wId;
    }

    public void setwId(Integer wId) {
        this.wId = wId;
    }

    private java.sql.Date cbTime;

    @Basic
    @Column(name = "cb_time")
    public java.sql.Date getCbTime() {
        return cbTime;
    }

    public void setCbTime(java.sql.Date cbTime) {
        this.cbTime = cbTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CraftBase craftBase = (CraftBase) o;
        return cbId == craftBase.cbId &&
                Objects.equals(cbName, craftBase.cbName) &&
                Objects.equals(wId, craftBase.wId) &&
                Objects.equals(cbTime, craftBase.cbTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cbId, cbName, wId, cbTime);
    }
}
