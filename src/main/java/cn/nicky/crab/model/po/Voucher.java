package cn.nicky.crab.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by zhang on 2017/11/13.
 */
@Entity
public class Voucher {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer userId;

    @Column
    private Integer voucherCategoryId;

    @Column
    private Integer identityNumber;

    @Column
    private Date createDateTime;

    @Column
    private Date activeDateTime;

    @Column
    private Integer status;

    @Column
    private String trackingNumber;

    @Column
    private Integer courierCompanyId;

    @Column
    private Integer orderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVoucherCategoryId() {
        return voucherCategoryId;
    }

    public void setVoucherCategoryId(Integer voucherCategoryId) {
        this.voucherCategoryId = voucherCategoryId;
    }

    public Integer getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(Integer identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getActiveDateTime() {
        return activeDateTime;
    }

    public void setActiveDateTime(Date activeDateTime) {
        this.activeDateTime = activeDateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Integer getCourierCompanyId() {
        return courierCompanyId;
    }

    public void setCourierCompanyId(Integer courierCompanyId) {
        this.courierCompanyId = courierCompanyId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
