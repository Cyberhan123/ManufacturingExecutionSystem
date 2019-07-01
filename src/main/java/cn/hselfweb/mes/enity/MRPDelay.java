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
    private Long MRPDelayId;
    private Long mrpbId;
    private double base;
    private float factor;
    private double max;

    public Long getMRPDelayId() {
        return MRPDelayId;
    }

    public void setMRPDelayId(Long MRPDelayId) {
        this.MRPDelayId = MRPDelayId;
    }

    public Long getMrpbId() {
        return mrpbId;
    }

    public void setMrpbId(Long mrpbId) {
        this.mrpbId = mrpbId;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
