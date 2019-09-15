package dao;

import domain.User;

public interface UserDao {
    Integer findUserByName(String name);

    int register(User user);

    int login(User user);

    User getUserByName(String username);

    Integer active(String code);
}
