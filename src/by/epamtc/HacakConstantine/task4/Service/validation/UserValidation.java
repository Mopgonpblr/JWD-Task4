package by.epamtc.HacakConstantine.task4.Service.validation;

import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.Bean.enums.Role;

public class UserValidation {

    public static boolean isLoginValid(String login) {
        if (login == null | login.isEmpty())
            return false;
        if (!login.matches("\\w{5,15}+"))
            return false;
        return true;
    }

    public static boolean isPasswordValid(String password){
        if (password== null | password.isEmpty()) {
            return false;
        }
        if (!password.matches(".{8,}+"))
            return false;
        return true;
    }

    public static boolean isRoleValid(Role role){
        return role != null;
    }


    public static boolean isUserValid(User user){
        if (user == null) {
            return false;
        }
        if (!UserValidation.isLoginValid(user.getLogin()))
            return false;

        if (!UserValidation.isPasswordValid(user.getPassword()))
            return false;
        if (!UserValidation.isRoleValid(user.getRole())) {
            return false;
        }
        return true;
    }
}
