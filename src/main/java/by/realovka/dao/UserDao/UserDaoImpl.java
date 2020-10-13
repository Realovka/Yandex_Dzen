package by.realovka.dao.UserDao;

import by.realovka.ConnectionPool;
import by.realovka.dto.user.UserAuthDTO;
import by.realovka.dto.user.UserRegDTO;
import by.realovka.dto.user.UserUpdateDTO;
import by.realovka.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;


@Repository
public class UserDaoImpl implements UserDao {

    private ConnectionPool connectionPool;

    public UserDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void createUser(UserRegDTO userRegDTO) {
        try {
            String sql = "INSERT INTO users_test VALUES (default, ?,?,?)";
            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ps.setString(1, userRegDTO.getNameUserDTO());
            ps.setString(2, userRegDTO.getLoginUserDTO());
            ps.setString(3, userRegDTO.getPasswordUserDTO());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> containsByLogin(String login) {
        try {
            String sql = "SELECT * FROM users_test WHERE login=?";
            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(rs.getString("login")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> containsUser(UserAuthDTO user) {
        try {
            String sql = "SELECT * FROM users_test WHERE login=? AND password=?";
            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ps.setString(1, user.getLoginAuthUser());
            ps.setString(2, user.getPasswordAuthUser());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateUser(User oldUser, UserUpdateDTO newUserUpdateDTO) {
        try {
            String sql = "UPDATE users_test SET name=?, password=? WHERE login=?";
            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ps.setString(1, newUserUpdateDTO.getName());
            ps.setString(2, newUserUpdateDTO.getPassword());
            ps.setString(3, oldUser.getLogin());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            String sql = "DELETE FROM users_test WHERE login = ?";
            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ps.setString(1, user.getLogin());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Optional<User> getIdUser(UserAuthDTO user) {
        try {
            String sql = "SELECT * FROM users_test WHERE login=? AND password=?";
            PreparedStatement ps = connectionPool.getConnection().prepareStatement(sql);
            ps.setString(1, user.getLoginAuthUser());
            ps.setString(2, user.getPasswordAuthUser());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
