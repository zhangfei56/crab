package cn.nicky.crab.model.po;

import org.hibernate.mapping.PrimaryKey;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User{
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phoneNumber;

    @Column(columnDefinition="tinyint default 0")
    private boolean markForDelete;

    @Column(columnDefinition="tinyint default 0")
    private boolean isMembership;

    @Column
    private Date registerDate;

    @Column
    private Date expireDate;

    @Column
    private String headPic;

    private String invitationCode;

    @Column(name=" image_code", columnDefinition="Blob")
    private byte[] imageCode;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable()
    public List<Role> roles;

    public User() {
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public byte[] getImageCode() {
        return imageCode;
    }

    public void setImageCode(byte[] imageCode) {
        this.imageCode = imageCode;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(boolean markForDelete) {
        this.markForDelete = markForDelete;
    }

    public boolean isMembership() {
        return isMembership;
    }

    public void setMembership(boolean membership) {
        isMembership = membership;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
}
