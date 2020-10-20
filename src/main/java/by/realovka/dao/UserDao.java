package by.realovka.dao;

import by.realovka.entity.User;

import java.util.Optional;

public interface UserDao {

    void createUser(User user);
    Optional<User> containsByLogin(String login);
    Optional<User> containsUser(User user);
    Optional<User> findById(long id);
}
