package cn.nicky.crab.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class RegisterCode {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String registerCode;

    @Column
    private String phoneNumber;

    @Column
    private Boolean markForDelete;

    @Column
    private Date expireDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(Boolean markForDelete) {
        this.markForDelete = markForDelete;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
