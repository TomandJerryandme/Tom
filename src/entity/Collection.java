package entity;

public class Collection {
    private User user;
    private Room room;

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

    @Override
    public String toString() {
        return "Collection{" +
                "user=" + user.getUsername() +
                ", room=" + room.getRoomname() +
                '}';
    }
}
