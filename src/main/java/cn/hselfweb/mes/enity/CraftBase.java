package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 基本工艺表
 * 编号，工艺名称，工作车间，耗费时间
 */
@Data
@Entity
@Table(name = "CraftBase")
public class CraftBase {
    @Id
    @GeneratedValue
    private Long craftBaseId;
    private String name;
    private Long workpieceId;
    private Date time;
}
