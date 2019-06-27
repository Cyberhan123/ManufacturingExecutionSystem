package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "MRPDelay")
public class MRPDelay {
    @Id
    @GeneratedValue
    private Long id;
    private Long mrpbId;
    private double base;
    private float factor;
    private double max;
}
