package by.realovka.dao;

import by.realovka.entity.Comment;

import java.util.List;

public interface CommentDao {

     void createComment(Comment comment);
     List<Comment> getListCommentsFromDB();
}
