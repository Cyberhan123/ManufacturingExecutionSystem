package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Client")

public class Client {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
}
