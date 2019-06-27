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
    private Long id;
    private Long ptId;
    private Long cbId;
}
