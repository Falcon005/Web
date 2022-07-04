package by.ashurmatov.anime.dao.query;

public class AnimeQuery {
    private AnimeQuery() {

    }

    public static final String SELECT_ALL_ANIME = "SELECT animeid,name,country,createdyear,genre,agelimit,description,image_path FROM anime";
    public static final String INSERT_ANIME_QUERY = "INSERT INTO anime (name,country,createdyear,genre,agelimit,description,image_path) VALUES(?,?,?,?,?,?,?)";
    public static final String DELETE_ANIME_BY_ID = "DELETE FROM anime WHERE animeid=?";
    public static final String FIND_BY_ID = "SELECT animeid,name,country,createdyear,genre,agelimit,description,image_path FROM anime WHERE animeid = ?";
    public static final String EDIT_ANIME = "UPDATE anime SET name=?,country=?,createdyear=?,genre=?,agelimit=?,description=?,image_path=? WHERE animeid=?";
    public static final String CHECK_NAME = "SELECT country FROM anime WHERE name=?";
    public static final String FIND_ID_BY_NAME = "SELECT animeid from anime WHERE name = ?";

}
