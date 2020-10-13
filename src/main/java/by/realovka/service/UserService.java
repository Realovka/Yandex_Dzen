package by.realovka.service;

import by.realovka.dao.UserDao.UserDaoImpl;
import by.realovka.dto.user.UserAuthDTO;
import by.realovka.dto.user.UserRegDTO;
import by.realovka.dto.user.UserUpdateDTO;
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
        userDao.createUser(userRegDTO);
    }

    public boolean authorizeUser(UserAuthDTO user) {
        if (userDao.containsUser(user).isEmpty()) {
            throw new NoSuchUserException("No such user in DB");
        }
        return true;
    }

    public boolean getUserFromDB(UserAuthDTO user) {
         if(userDao.containsUser(user).isPresent()){
             return true;
         }else return false;
    }

    public void updateUserInDB(User oldUser, UserUpdateDTO newUserUpdateDTO) {
        userDao.updateUser(oldUser, newUserUpdateDTO);
    }

//    public long getAuthUserId(UserAuthDTO userAuthDTO) {
//        long id = 0;
//        if (userDao.getIdUser(userAuthDTO).isPresent()) {
//            id = userDao.getIdUser(userAuthDTO).get().getId();
//        }
//        return id;
//    }

    public void deleteUserFromDB(User user) {
        userDao.deleteUser(user);
    }
}
