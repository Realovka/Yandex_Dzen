package by.realovka.service;

import by.realovka.dao.PostDao.PostDaoImpl;
import by.realovka.dto.post.PostUserAddDTO;
import by.realovka.dto.post.PostViewOnPageDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PostService {
    private PostDaoImpl postDao;

    public PostService(PostDaoImpl postDao) {
        this.postDao = postDao;
    }

    public void addPost(PostUserAddDTO postUserAddDTO, int idUser){
       postDao.createPost(postUserAddDTO, idUser);
    }

    public ArrayList<PostViewOnPageDTO> postViewOnTheFirstPageDTOS(){
      return   postDao.getListPostsToFirstPage();
    }
}
