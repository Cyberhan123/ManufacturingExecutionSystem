package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "StaffAbility")
public class StaffAbility {
    @Id
    @GeneratedValue
    private Long StaffAbilityId;
    private Long sId;
    private Long ptId;
    private String grade;

    public Long getStaffAbilityId() {
        return StaffAbilityId;
    }

    public void setStaffAbilityId(Long staffAbilityId) {
        StaffAbilityId = staffAbilityId;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public Long getPtId() {
        return ptId;
    }

    public void setPtId(Long ptId) {
        this.ptId = ptId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}