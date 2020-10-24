package by.realovka.connection;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class HikariCPDataSource {
    private static final String DB_USERNAME="postgres";
    private static final String DB_PASSWORD="Vorobei55";
    private static final String DB_URL ="jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_DRIVER_CLASS="org.postgresql.Driver";

    private static Properties properties = null;
    private static HikariDataSource dataSource;
    static{
        try {


            dataSource = new HikariDataSource();
            dataSource.setDriverClassName(DB_DRIVER_CLASS);

            dataSource.setJdbcUrl(DB_URL);
            dataSource.setUsername(DB_USERNAME);
            dataSource.setPassword(DB_PASSWORD);

            dataSource.setMinimumIdle(100);
            dataSource.setMaximumPoolSize(2000);//The maximum number of connections, idle or busy, that can be present in the pool.
            dataSource.setAutoCommit(false);
            dataSource.setLoginTimeout(3);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }


}
