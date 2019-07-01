package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class WorkpieceExtend {
    @Id
    @GeneratedValue
    private Long WorkpieceExtendId;
    private String num;
    private Long wId;
    private Long mrpbId;
    private String type;

    public Long getWorkpieceExtendId() {
        return WorkpieceExtendId;
    }

    public void setWorkpieceExtendId(Long workpieceExtendId) {
        WorkpieceExtendId = workpieceExtendId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Long getwId() {
        return wId;
    }

    public void setwId(Long wId) {
        this.wId = wId;
    }

    public Long getMrpbId() {
        return mrpbId;
    }

    public void setMrpbId(Long mrpbId) {
        this.mrpbId = mrpbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
