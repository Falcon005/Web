package by.ashurmatov.anime.dao.query;

public class RatingQuery {
    private RatingQuery() {

    }
    public static final String INSERT_RATING_QUERY = "INSERT INTO rating (animeid,value) VALUES(?,?)";
    public static final String DELETE_RATING_BY_ID = "DELETE FROM rating WHERE ratingid=?";
    public static final String DELETE_RATING_BY_ANIME_ID = "DELETE FROM rating WHERE animeid=?";
    public static final String SELECT_ALL_RATING = "SELECT ratingid,animeid,value FROM rating";
    public static final String FIND_BY_ID = "SELECT ratingid,animeid,value FROM anime WHERE ratingid = ?";
    public static final String FIND_RATING_BY_ANIME_ID = "SELECT ratingid,animeid,value FROM rating WHERE animeid = ?";
    public static final String UPDATE_VALUE = "UPDATE rating SET value=? WHERE animeid=?";
}
