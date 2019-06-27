package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * 设备状态
 * id，设备id，设备所属
 */
@Data
@Entity
@Table(name = "DeviceState")
public class DeviceState {
    @Id
    @GeneratedValue
    private Long deviceStateid;
    private Long deviceId;
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
