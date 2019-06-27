package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "DeviceState")

public class DeviceState {
    @Id
    @GeneratedValue
    private Long id;
    private Long dId;
    private Long weId;
    private Long ceId;
    private int state;
    private Date startTime;
    private Date endTime;
    private Date pStartTime;
    private Date pEndTime;
    private Date rStartTime;
    private Date rEndTime;
    private Long sId;
    private Date upTime;
}
