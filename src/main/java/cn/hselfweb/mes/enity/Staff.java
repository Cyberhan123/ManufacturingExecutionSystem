package cn.hselfweb.mes.enity;


import lombok.Data;

@Data
public class Staff {
    private int id;
    private String name;
    private String sex;
    private int age;
    private double salaryBase;
    private double salaryWork;
    private double salaryExtra;
    private int workTime;
    private String position;
}