package by.ashurmatov.anime.model.dao.query;

public class UserQuery {
    private UserQuery(){

    }
    public static final String USERS_TABLE = "users";
    public static final String FIND_ALL_USERS_QUERY = "SELECT userid, email,role,firstname,lastname,username FROM " + USERS_TABLE;
    public static final String INSERT_USER_QUERY="INSERT INTO "+ USERS_TABLE + "(email,role,firstname,lastname,username,password) VALUES(?,?,?,?,?,?)";
    public static final String DELETE_USER_QUERY = "DELETE FROM " + USERS_TABLE + " WHERE (userid = ?)";
    public static final String UPDATE_USER_QUERY = "UPDATE " + USERS_TABLE + " SET email = ?, firstname = ?, lastname = ?, username = ?, password = ?," + " WHERE (userid = ?)";
    public static final String FIND_USER_WITH_LIMITS_QUERY = "SELECT userid, email, role, firstname, lastname," + " username, password   FROM " + USERS_TABLE + " ORDER BY userid DESC LIMIT ? OFFSET ?";
    public static final String FIND_COUNT_OF_USERS_QUERY = "SELECT count(*) FROM "+ USERS_TABLE;
    public static final String CHECK_FOR_LOGIN = "SELECT password FROM " + USERS_TABLE + " WHERE username=?";

}
