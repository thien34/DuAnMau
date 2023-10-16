package utils;

import entity.Employee;

public class AuthHelper {

    public static Employee USER = null;

    public static void logout() {
        AuthHelper.USER = null;
    }

    public static boolean isLogin() {
        return AuthHelper.USER != null;
    }

    public static void setUSER(Employee employee) {
        USER = employee;
    }
}
