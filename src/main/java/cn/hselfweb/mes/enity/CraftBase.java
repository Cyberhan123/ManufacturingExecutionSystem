package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 基本工艺表
 * 编号，工艺名称，工作车间，耗费时间
 */

@Entity
@Table(name = "CraftExtend")
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


}
