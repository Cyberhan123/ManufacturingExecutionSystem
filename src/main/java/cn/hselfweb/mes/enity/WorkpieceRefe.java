package cn.hselfweb.mes.enity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WorkpieceRefe")
@Data
public class WorkpieceRefe {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private Long wId;
    private Long ceId;
    private double price;
    private String leadTime;
    private String uptime;
}
