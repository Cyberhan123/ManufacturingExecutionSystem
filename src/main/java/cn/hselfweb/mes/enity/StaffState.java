package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Table(name = "StaffState")
@Entity
public class StaffState {
    @Id
    @GeneratedValue
    private Long StaffStateId;
    private Long sId;
    private String state;
    private String startTime;
    private String endTime;
    private String upTime;

}