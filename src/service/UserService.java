package service;

import domain.User;

public interface UserService {
    Integer findUserByName(String name);

    int register(User user);

    int login(User user);

    User getUserByName(String username1);

    Integer active(String code);
}
