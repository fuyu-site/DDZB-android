package site.fuyu.stu.ddzb;

public class Result {
    public static int OK = 1;
    public static int FILL = -1;
    private int status;
    private String message;
    private User user;

    int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
