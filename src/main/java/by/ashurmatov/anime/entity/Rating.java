package by.ashurmatov.anime.entity;

import java.util.Objects;

public class Rating extends AbstractEntity{
    private int id;
    private int anime_id;
    private double value;

    public Rating() {
    }

    public Rating(int id, int anime_id, double value) {
        this.id = id;
        this.anime_id = anime_id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(int anime_id) {
        this.anime_id = anime_id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return id == rating.id && anime_id == rating.anime_id && Double.compare(rating.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, anime_id, value);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", anime_id=" + anime_id +
                ", value=" + value +
                '}';
    }
}
