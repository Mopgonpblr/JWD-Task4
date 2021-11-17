package by.epamtc.HacakConstantine.task4.Service;

import by.epamtc.HacakConstantine.task4.Bean.enums.Role;
import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.Bean.enums.UserStatus;

public class CurrentUser extends User {
    private static final CurrentUser instance = new CurrentUser();
    private UserStatus status;

    private CurrentUser() {
         this.setRole(Role.GUEST);
    }

    public void setUser(User user) {
        this.setName(user.getName());
        this.setLogin(user.getLogin());
        this.setRole(user.getRole());
        this.setPassword(user.getPassword());
        this.setStatus(UserStatus.ONLINE);
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
