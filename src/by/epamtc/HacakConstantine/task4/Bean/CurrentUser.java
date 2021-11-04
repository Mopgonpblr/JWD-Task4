package by.epamtc.HacakConstantine.task4.Bean;

public class CurrentUser extends User {
    private static final CurrentUser instance = new CurrentUser();
    private UserStatus status;

    private CurrentUser() {
    }

    public static CurrentUser getInstance() {
        return instance;
    }


    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
