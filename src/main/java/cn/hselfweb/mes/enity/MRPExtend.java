package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "MRPExtend")
public class MRPExtend {
    @Id
    @GeneratedValue
    private Long id;
    private Long mrpbId;
    private Long wid;
    private int count;
    private Date startTime;
    private Date endTime;
}
