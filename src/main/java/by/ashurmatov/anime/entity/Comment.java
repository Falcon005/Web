package by.ashurmatov.anime.entity;

import java.util.Objects;

public class Comment extends AbstractEntity{
    private int id;
    private String comment_text;
    private int anime_id;

    public Comment() {
    }

    public Comment(int id, String comment_text, int anime_id) {
        this.id = id;
        this.comment_text = comment_text;
        this.anime_id = anime_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public int getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(int anime_id) {
        this.anime_id = anime_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && anime_id == comment.anime_id && Objects.equals(comment_text, comment.comment_text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment_text, anime_id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment_text='" + comment_text + '\'' +
                ", anime_id=" + anime_id +
                '}';
    }
}
