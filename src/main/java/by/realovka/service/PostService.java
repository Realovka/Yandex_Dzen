package by.realovka.service;

import by.realovka.dao.PostDaoImpl;
import by.realovka.dao.UserDaoImpl;
import by.realovka.dto.post.PostUserAddDTO;
import by.realovka.dto.post.PostViewOnPageDTO;
import by.realovka.entity.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {
    private PostDaoImpl postDao;
    private UserDaoImpl userDao;

    public PostService(PostDaoImpl postDao, UserDaoImpl userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    public void addPost(PostUserAddDTO postUserAddDTO, long idUser){
       Post post = new Post(postUserAddDTO.getTitle(),postUserAddDTO.getDescription(),postUserAddDTO.getText());
       postDao.createPost(post, idUser);
    }

    public List<Post> postViewOnTheFirstPage(){
        ArrayList<Post> list = postDao.getListPostsToFirstPage();
        Collections.sort(list, new SortPostsOnTheFirstPageComparator());
        return list;
    }

    public PostViewOnPageDTO getPostViewOnPage(long idPost){
        Post postFromDB = postDao.getPostToPage(idPost);
        String userNameWhoWritePost = null;
        if (userDao.findById(postDao.getPostToPage(idPost).getUserId()).isPresent()){
            userNameWhoWritePost = userDao.findById(postDao.getPostToPage(idPost).getUserId()).get().getName();
        }
        return new PostViewOnPageDTO(postFromDB.getId(),postFromDB.getTitle(),postFromDB.getText(),postFromDB.getTimestamp(), userNameWhoWritePost, postFromDB.getView());
    }

    public void insertView(int id) {
        if (postDao.getPostToPage(id).getView() == 0) {
            postDao.insertViewPost(id, false);
        } else {
            postDao.insertViewPost(id, true);
        }
    }


}
