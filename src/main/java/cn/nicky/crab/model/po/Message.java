package cn.nicky.crab.model.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhang on 2017/11/13.
 */
@Entity
public class Message {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private Boolean isRead;

    @ManyToOne
    @JoinColumn
    private User sendUser;

    @ManyToOne
    @JoinColumn
    private User toUser;

    @Column
    private Date sendDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        this.isRead = read;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}
