package by.ashurmatov.anime.dao.mapper;

public class ColumnName {

    /*
    Users table
     */
    public static final String USER_ID = "userid";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String STATUS = "status";


    /*
    Anime table
     */
    public static final String ANIME_ID = "animeid";
    public static final String ANIME_NAME = "name";
    public static final String COUNTRY = "country";
    public static final String CREATED_YEAR = "createdyear";
    public static final String GENRE = "genre";
    public static final String AGE_LIMIT = "agelimit";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE_PATH = "image_path";

    /*
    Attachment table
     */

    public static final String ATTACHMENT_ID = "attachmentid";
    public static final String ATTACHMENT_NAME = "attachmentname";
    public static final String PATH = "path";
    public static final String ANIME_ID_IN_ATTACHMENT = "animeid";

    /*
    Comment  table
     */

    public static final String COMMENT_ID = "commentid";
    public static final String COMMENT_TEXT = "commenttext";
    public static final String USER_ID_IN_COMMENT = "userid";
    public static final String ANIME_ID_IN_COMMENT = "animeid";

    /*
    Rating table
     */

    public static final String RATING_ID = "ratingid";
    public static final String USER_ID_IN_RATING = "userid";
    public static final String ANIME_ID_IN_RATING = "animeid";
    public static final String VALUE = "value";

}
