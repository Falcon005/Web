package by.ashurmatov.anime.dao.query;

public class UserQuery {
    private UserQuery(){

    }


    public static final String INSERT_USER_QUERY="INSERT INTO users (email,role,firstname,lastname,username,password,status) VALUES(?,?,?,?,?,?,?)";

    public static final String FIND_COUNT_OF_USERS_QUERY = "SELECT count(*) FROM  users";
    public static final String CHECK_FOR_LOGIN = "SELECT password FROM users WHERE username=?";

    public static final String CHECK_LOGIN = "SELECT users.firstname FROM users WHERE users.username=?";
    public static final String CHECK_EMAIL = "SELECT users.firstname FROM users WHERE users.email=?";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE userid = ?";
    public static final String SELECT_ALL_USERS = "SELECT userid,email,role,firstname,lastname,username,password,status FROM users";
    public static final String SELECT_BY_LOGIN =  "SELECT userid,email,role,firstname,lastname,username,password,status FROM users WHERE username = ?";
    public static final String FIND_USER_ROLE_BY_LOGIN = "SELECT role FROM users WHERE username = ?";
    public static final String FIND_USER_STATUS_BY_LOGIN = "SELECT status FROM users WHERE username = ?";
    public static final String FIND_BY_ID = "SELECT userid,email,role,firstname,lastname,username,password,status FROM users WHERE userid = ?";
    public static final String FIND_BY_ROLE = "SELECT userid,email,role,firstname,lastname,username,password,status FROM users WHERE role = ?";
    public static final String UPDATE_USER = "UPDATE users SET email = ?,firstname = ?, lastname = ?,username = ?, password = ? WHERE userid = ?";
    public static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE username = ?";
    public static final String DELETE_BY_LOGIN = "DELETE FROM users WHERE username = ?";
    public static final String DELETE_SO_THAT_TO_CHANGE_STATUS_TO_BLOCKED = "UPDATE users SET status = 'BLOCKED' WHERE username = ?";
    public static final String ACTIVATE_SO_THAT_TO_CHANGE_STATUS_TO_ACTIVATED = "UPDATE users SET status = 'ACTIVE' WHERE username = ?";

}
