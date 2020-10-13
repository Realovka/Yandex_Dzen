package by.realovka.dao.UserDao;

import by.realovka.dto.user.UserAuthDTO;
import by.realovka.dto.user.UserRegDTO;
import by.realovka.dto.user.UserUpdateDTO;
import by.realovka.entity.User;

import java.util.Optional;

public interface UserDao {
    void createUser(UserRegDTO userRegDTO);
    Optional <User> containsByLogin(String login);
    Optional<User> containsUser(UserAuthDTO user);
    void updateUser(User oldUser, UserUpdateDTO newUserUpdateDTO);
    void deleteUser(User user);
//    Optional<User> getIdUser(UserAuthDTO user);
}
