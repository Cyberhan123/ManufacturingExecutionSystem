package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "StaffAbility")
public class StaffAbility {
    @Id
    @GeneratedValue
    private Long StaffAbilityId;
    private Long sId;
    private Long ptId;
    private String grade;
}