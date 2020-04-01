package entity;

public class MessageType {
    private int typeid;
    private String typename;

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public String toString() {
        return "MessageType{" +
                "typename='" + typename + '\'' +
                '}';
    }
}
