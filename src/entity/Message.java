package entity;

import java.util.Date;

public class Message {
    private int messageid;
    private User user;
    private Room room;
    private MessageType messageType;
    private String content;
    private Date time;

    public int getMessageid() {
        return messageid;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "user=" + user.getUsername() +
                ", room=" + room.getRoomname() +
                ", messageType=" + messageType.getTypename() +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
