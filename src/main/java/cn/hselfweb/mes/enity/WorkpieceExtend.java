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
}
