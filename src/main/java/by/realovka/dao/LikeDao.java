package by.realovka.dao;

import by.realovka.entity.Like;

import java.util.List;

public interface LikeDao {

    void insertLikePost(Like like);
    List<Like> getLikeListFromDB();
}
