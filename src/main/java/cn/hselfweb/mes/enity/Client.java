package cn.hselfweb.mes.enity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * 客户表
 * id，用户名，密码
 */
@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue
    private Long clientId;
    private String name;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    private int cId;

    @Id
    @javax.persistence.Column(name = "c_id")
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    private String cName;

    @Basic
    @Column(name = "c_name")
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    private String cPassword;

    @Basic
    @Column(name = "c_password")
    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return cId == client.cId &&
                Objects.equals(cName, client.cName) &&
                Objects.equals(cPassword, client.cPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cId, cName, cPassword);
    }
}
