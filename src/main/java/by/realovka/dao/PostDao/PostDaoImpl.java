package by.realovka.dao.PostDao;

import by.realovka.ConnectionPool;

import by.realovka.dto.post.PostUserAddDTO;
import by.realovka.dto.post.PostViewOnPageDTO;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;


@Repository
public class PostDaoImpl {
    private ConnectionPool connectionPool;

    public PostDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    //    @Override
    public void createPost(PostUserAddDTO postUserAddDTO, int id) {
        try {
            String sql = "INSERT INTO posts VALUES (default, ?,?,?,?,?)";
            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ps.setString(1, postUserAddDTO.getTitle());
            ps.setString(2, postUserAddDTO.getDescription());
            ps.setString(3, postUserAddDTO.getText());
            ps.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setLong(5, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PostViewOnPageDTO> getListPostsToFirstPage() {
        ArrayList<PostViewOnPageDTO> postUserAddDTOList = new ArrayList<>();
        PostViewOnPageDTO postViewOnPageDTO = new PostViewOnPageDTO();
        try {
            String sql = "SELECT * FROM posts";
            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                Date data = resultSet.getDate("created_at");
                postViewOnPageDTO = new PostViewOnPageDTO(title, text, data);
                postUserAddDTOList.add(postViewOnPageDTO);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return postUserAddDTOList;
    }

}
