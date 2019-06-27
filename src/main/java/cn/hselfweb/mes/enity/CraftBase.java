package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "CraftBase")
public class CraftBase {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long wid;
    private Date time;
}
