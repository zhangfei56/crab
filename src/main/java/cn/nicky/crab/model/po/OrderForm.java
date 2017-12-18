package cn.nicky.crab.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhang on 2017/11/13.
 */
@Entity
public class OrderForm {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Date createDateTime;

    @Column
    private Date needDateTime;

    @Column
    private String address;

    @Column
    private String contact;

    @Column
    private String phoneNumber;

    @Column
    private String courierCompanyType;

    @Column
    private String trackingNumber;

    @OneToOne
    @JoinColumn
    private Voucher voucher;

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getCourierCompanyType() {
        return courierCompanyType;
    }

    public void setCourierCompanyType(String courierCompanyType) {
        this.courierCompanyType = courierCompanyType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getNeedDateTime() {
        return needDateTime;
    }

    public void setNeedDateTime(Date needDateTime) {
        this.needDateTime = needDateTime;
    }
}
