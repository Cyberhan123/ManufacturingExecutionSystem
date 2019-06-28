package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
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
}
