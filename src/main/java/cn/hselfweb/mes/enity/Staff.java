package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "Staff")
@Entity
public class Staff {
    @Id
    @GeneratedValue
    private Long StaffId;
    private String name;
    private String sex;
    private Long age;
    private double salaryBase;
    private double salaryWork;
    private double salaryExtra;
    private Long workTime;
    private String position;

    public Long getStaffId() {
        return StaffId;
    }

    public void setStaffId(Long staffId) {
        StaffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public double getSalaryBase() {
        return salaryBase;
    }

    public void setSalaryBase(double salaryBase) {
        this.salaryBase = salaryBase;
    }

    public double getSalaryWork() {
        return salaryWork;
    }

    public void setSalaryWork(double salaryWork) {
        this.salaryWork = salaryWork;
    }

    public double getSalaryExtra() {
        return salaryExtra;
    }

    public void setSalaryExtra(double salaryExtra) {
        this.salaryExtra = salaryExtra;
    }

    public Long getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Long workTime) {
        this.workTime = workTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}