package entity;

public class Count {
    //用户发表违禁词的次数
    private User user;
    private int count = 0;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return user.getUsername() + "发表违禁词的次数：" + count;
    }
}
