package by.realovka.service;

import by.realovka.dao.UserDaoImpl;
import by.realovka.dto.user.UserAuthDTO;
import by.realovka.dto.user.UserRegDTO;
import by.realovka.entity.User;
import by.realovka.service.exception.DuplicateUserException;
import by.realovka.service.exception.NoSuchUserException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDaoImpl userDao;

    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public void createUser(UserRegDTO userRegDTO) {
        if (userDao.containsByLogin(userRegDTO.getLoginUserDTO()).isPresent()) {
            throw new DuplicateUserException("Such user is already");
        }
        User user = new User(userRegDTO.getNameUserDTO(),userRegDTO.getLoginUserDTO(),userRegDTO.getPasswordUserDTO());
        userDao.createUser(user);
    }

    public boolean authorizeUser(UserAuthDTO userAuthDTO) {
        User user = new User (userAuthDTO.getLoginAuthUser(),userAuthDTO.getPasswordAuthUser());
        if (userDao.containsUser(user).isEmpty()) {
            throw new NoSuchUserException("No such user in DB");
        }
        return true;
    }


    public User getAuthUserIdAndName(UserAuthDTO userAuthDTO) {
        User user = new User(userAuthDTO.getLoginAuthUser(),userAuthDTO.getPasswordAuthUser());
        User userAuth = new User();
        if (userDao.containsUser(user).isPresent()) {
             userAuth =  userDao.containsUser(user).get();
        }
        return userAuth;
    }

}
