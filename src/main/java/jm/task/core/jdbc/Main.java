package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Geralt", "from Rivia", (byte) 94);
        userService.saveUser("Triss", "Merigold", (byte) 47);
        userService.saveUser("Jennifer", "from Vengerberg", (byte) 110);
        userService.saveUser("Ciri", "a Swallow", (byte) 18);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}