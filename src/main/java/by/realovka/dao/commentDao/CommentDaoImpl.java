package by.realovka.dao.commentDao;

import by.realovka.ConnectionPool;
import by.realovka.dto.comment.CommentDTO;
import by.realovka.dto.user.UserAuthDTO;
import by.realovka.entity.User;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class CommentDaoImpl {
    private ConnectionPool connectionPool;

    public CommentDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void createComment(CommentDTO commentDTO, int id) {
        try {
            String sql = "INSERT INTO comments VALUES (default, ?,?,?)";
            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ps.setString(1, commentDTO.getText());
            ps.setInt(2, id);
            ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
