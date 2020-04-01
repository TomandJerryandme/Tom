package entity;

public class Room {
    private int roomid;
    private String roomname;
    private RoomType roomtype;
    private String roomphoto;
    private String introduce;
    private String truename;

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
