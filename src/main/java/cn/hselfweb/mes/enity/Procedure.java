package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Procedure")
public class Procedure {
    @Id
    @GeneratedValue
    private Long ProcedureId;
    private Long ptId;
    private Long cbId;

    public Long getProcedureId() {
        return ProcedureId;
    }

    public void setProcedureId(Long procedureId) {
        ProcedureId = procedureId;
    }

    public Long getPtId() {
        return ptId;
    }

    public void setPtId(Long ptId) {
        this.ptId = ptId;
    }

    public Long getCbId() {
        return cbId;
    }

    public void setCbId(Long cbId) {
        this.cbId = cbId;
    }
}
