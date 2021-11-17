package by.epamtc.HacakConstantine.task4.Service.validation;

import by.epamtc.HacakConstantine.task4.Bean.enums.Role;

public class RoleValidation {
    public static boolean isAdmin (Role role){
        return role.equals(Role.ADMIN);
    }

    public static boolean isUser (Role role){
        return role.equals(Role.USER);
    }

    public static boolean isGuest (Role role){
        return role.equals(Role.GUEST);
    }
}
