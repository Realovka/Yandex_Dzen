package by.realovka.dao;

//import by.realovka.ConnectionPool;

import by.realovka.entity.Post;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;


@Repository
public class PostDaoImpl implements PostDao {
//    private ConnectionPool connectionPool;
//
//    public PostDaoImpl(ConnectionPool connectionPool) {
//        this.connectionPool = connectionPool;

    private Connection connectionPool;

    public PostDaoImpl(Connection connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void createPost(Post post, long id) {
        try {
            String sql = "INSERT INTO posts VALUES (default, ?,?,?,?,default, ?)";//TODO
            PreparedStatement ps = connectionPool.prepareStatement(sql);
//            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getDescription());
            ps.setString(3, post.getText());
            ps.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis())); //TODO
            ps.setLong(5, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Post> getListPostsToFirstPage() {
        ArrayList<Post> posts = new ArrayList<>();
        Post postFromDB = new Post();
        try {
            String sql = "SELECT * FROM posts";
            PreparedStatement ps = connectionPool.prepareStatement(sql);
//            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                Timestamp timestamp = resultSet.getTimestamp("created_at");
                postFromDB = new Post(id, title, text, timestamp);
                posts.add(postFromDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post getPostToPage(long id) {
        Post postViewOnPage = new Post();
        try {
            String sql = "SELECT * FROM posts WHERE id=?";
            PreparedStatement ps = connectionPool.prepareStatement(sql);
//            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                postViewOnPage = new Post(rs.getLong("id"), rs.getString("title"), rs.getString("text"), rs.getLong("views"), rs.getTimestamp("created_at"), rs.getInt("users_test_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postViewOnPage;
    }

    @Override
    public void insertViewPost(long id, boolean flag) {
        String sql = null;
        try {
            if (flag == false) {
                sql = "UPDATE posts SET views=+1 WHERE id=?";
            } else {
                sql = "UPDATE posts SET views=views+1 WHERE id=?";
            }
            PreparedStatement ps = connectionPool.prepareStatement(sql);
//            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

