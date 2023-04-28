package cdu.nls.app.model;

import java.io.Serializable;

public class Msg implements Serializable {

    private int id;
    private User user;
    private String subject;
    private String content;
    private long addMsgTime;
    private int isReplied;
    private User reUser;
    private String reply;
    private long reTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getAddMsgTime() {
        return addMsgTime;
    }

    public void setAddMsgTime(long addMsgTime) {
        this.addMsgTime = addMsgTime;
    }

    public boolean isReplied() {
        return isReplied == 1 ? true : false;
    }

    public int getIsReplied() {
        return isReplied;
    }

    public void setIsReplied(int isReplied) {
        this.isReplied = isReplied;
    }

    public User getReUser() {
        return reUser;
    }

    public void setReUser(User reUser) {
        this.reUser = reUser;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public long getReTime() {
        return reTime;
    }

    public void setReTime(long reTime) {
        this.reTime = reTime;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "id=" + id +
                ", user=" + user +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", addMsgTime=" + addMsgTime +
                ", isReplied=" + isReplied +
                ", reUser=" + reUser +
                ", reply='" + reply + '\'' +
                ", reTime=" + reTime +
                '}';
    }
}
