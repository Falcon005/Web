package by.ashurmatov.anime.model.dao;

public interface UserDao {
    boolean authenticate(String login,String password);

}
