package by.ashurmatov.anime.model.dao.mapper;

import java.sql.ResultSet;
import java.util.Optional;

public interface EntityMapper<T> {
    Optional<T> map(ResultSet resultSet);
}
