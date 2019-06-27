package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 客户表
 * id，用户名，密码
 */
@Data
@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue
    private Long clientId;
    private String name;
    private String password;
}
