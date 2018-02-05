package cn.nicky.crab.model.po;

import javax.persistence.*;
import java.util.Date;

@Entity
public class InvitationCode {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String code;

    @Column
    private Date createDate;

    @OneToOne
    @JoinColumn
    private User createBy;

    @OneToOne
    @JoinColumn
    private User inviteUser;

    @Column
    private Date useDate;

    @Column
    private boolean markForDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    public User getInviteUser() {
        return inviteUser;
    }

    public void setInviteUser(User inviteUser) {
        this.inviteUser = inviteUser;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public boolean isMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(boolean markForDelete) {
        this.markForDelete = markForDelete;
    }
}
