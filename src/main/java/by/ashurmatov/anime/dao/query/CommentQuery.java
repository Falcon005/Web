package by.ashurmatov.anime.dao.query;

public class CommentQuery {
    private CommentQuery() {

    }

    public static final String SELECT_ALL_COMMENT = "SELECT commentid,commenttext,userid,animeid FROM comment";
    public static final String INSERT_COMMENT_QUERY = "INSERT INTO comment (commenttext, animeid) VALUES(?,?)";
    public static final String DELETE_COMMENT_BY_ID = "DELETE FROM comment WHERE commentid=?";

    public static final String DELETE_COMMENT_BY_ANIME_ID = "DELETE FROM comment WHERE animeid=?";

    public static final String FIND_BY_ID = "SELECT commentid, commenttext, userid, animeid FROM comment WHERE commentid = ?";
    public static final String FIND_COMMENT_BY_ANIME_ID = "SELECT commentid, commenttext, animeid FROM comment WHERE animeid = ?";

}
