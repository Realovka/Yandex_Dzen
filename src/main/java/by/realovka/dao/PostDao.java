package by.realovka.dao;

import by.realovka.entity.Post;

import java.util.ArrayList;

public interface PostDao {

    void createPost(Post post, long id);
    ArrayList<Post> getListPostsToFirstPage();
    Post getPostToPage(long id);
    void insertViewPost(long id, boolean flag);
}
