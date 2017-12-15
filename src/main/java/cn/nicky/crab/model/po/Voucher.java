package cn.nicky.crab.model.po;

import javax.persistence.*;
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

    @OneToOne
    @JoinColumn
    private VoucherCategory voucherCategory;

    @Column
    private String identityCode;

    @Column
    private Date createDateTime;

    @Column
    private Date activeDateTime;

    @Column
    private Integer status;

    @OneToOne(mappedBy = "voucher")
    private OrderForm orderForm;

    public OrderForm getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(OrderForm orderForm) {
        this.orderForm = orderForm;
    }

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

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
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

    public VoucherCategory getVoucherCategory() {
        return voucherCategory;
    }

    public void setVoucherCategory(VoucherCategory voucherCategory) {
        this.voucherCategory = voucherCategory;
    }
}
