package by.ashurmatov.anime.dao.mapper.impl;

import by.ashurmatov.anime.dao.mapper.EntityMapper;
import by.ashurmatov.anime.dao.mapper.ColumnName;
import by.ashurmatov.anime.entity.User;
import by.ashurmatov.anime.entity.type.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserMapper implements EntityMapper<User> {
    private static final Logger logger = LogManager.getLogger(UserMapper.class);
    @Override
    public  Optional<User> map(ResultSet resultSet) {
        try {
            User user = new User();
            user.setId(resultSet.getInt(ColumnName.USER_ID));
            user.setEmail(resultSet.getString(ColumnName.EMAIL));
            user.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
            user.setFirstname(resultSet.getString(ColumnName.FIRSTNAME));
            user.setLastname(resultSet.getString(ColumnName.LASTNAME));
            user.setUserName(resultSet.getString(ColumnName.USERNAME));
            user.setPassword(resultSet.getString(ColumnName.PASSWORD));

            return Optional.of(user);
        }catch (SQLException sqlException) {
            logger.error("error in mapping resultSet into an object (User)", sqlException);
        }
        return Optional.empty();
    }
}
