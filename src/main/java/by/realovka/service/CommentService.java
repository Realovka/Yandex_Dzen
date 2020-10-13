package by.realovka.service;

import by.realovka.dao.commentDao.CommentDaoImpl;
import by.realovka.dao.UserDao.UserDaoImpl;
import by.realovka.dto.comment.CommentDTO;
import by.realovka.dto.user.UserAuthDTO;
import by.realovka.entity.User;
import by.realovka.service.exception.NoSuchUserException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CommentService {
    private CommentDaoImpl commentDao;
    private UserDaoImpl userDao;

    public CommentService(CommentDaoImpl commentDao, UserDaoImpl userDao) {
        this.commentDao = commentDao;
        this.userDao = userDao;
    }

    public void addComment(CommentDTO commentDTO, HttpSession httpSession) {
        if (userDao.getIdUser((UserAuthDTO) httpSession.getAttribute("userAuth")).isPresent()) {
            User userAuth = userDao.getIdUser((UserAuthDTO) httpSession.getAttribute("userAuth")).get();
            commentDao.createComment(commentDTO, userAuth.getId());
        } else throw new NoSuchUserException();
    }
}