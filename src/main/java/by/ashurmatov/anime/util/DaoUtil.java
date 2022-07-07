package by.ashurmatov.anime.util;


import by.ashurmatov.anime.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUtil {
    private static final Logger logger = LogManager.getLogger(DaoUtil.class);

    public static void releaseResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        releaseResources(connection, preparedStatement);
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.warn("Can't close result set", e);
            }
        }
    }

    public static void releaseResources(Connection connection, PreparedStatement preparedStatement) {
        ConnectionPool.INSTANCE.releaseConnection(connection);
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.warn("Can't close result set", e);
            }
        }
    }
}
