package entity;

public class Room {
    private int roomid;
    private String roomname;
    private RoomType roomtype;
    private String roomphoto;
    private String introduce;
    private String truename;
    private int type;

    private int user1;
    private int user2;

    public int getUser1() {
        return user1;
    }

    public void setUser1(int user1) {
        this.user1 = user1;
    }

    public int getUser2() {
        return user2;
    }

    public void setUser2(int user2) {
        this.user2 = user2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getRoomphoto() {
        return roomphoto;
    }

    public void setRoomphoto(String roomphoto) {
        this.roomphoto = roomphoto;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public RoomType getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(RoomType roomtype) {
        this.roomtype = roomtype;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomname='" + roomname + '\'' +
                ", roomtype=" + roomtype.getTypename() +
                '}';
    }
}
