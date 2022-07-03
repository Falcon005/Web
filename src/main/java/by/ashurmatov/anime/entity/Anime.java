package by.ashurmatov.anime.entity;

import java.util.Objects;

public class Anime extends AbstractEntity{
    private int id;
    private String name;
    private String country;
    private int createdYear;
    private String genre;
    private int ageLimit;
    private String description;

    public Anime() {
    }

    public Anime(int id, String name, String country, int createdYear, String genre, int ageLimit, String description) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.createdYear = createdYear;
        this.genre = genre;
        this.ageLimit = ageLimit;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCreatedYear() {
        return createdYear;
    }

    public void setCreatedYear(int createdYear) {
        this.createdYear = createdYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anime anime = (Anime) o;
        return id == anime.id && createdYear == anime.createdYear && ageLimit == anime.ageLimit && Objects.equals(name, anime.name) && Objects.equals(country, anime.country) && Objects.equals(genre, anime.genre) && Objects.equals(description, anime.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, createdYear, genre, ageLimit, description);
    }

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", createdYear=" + createdYear +
                ", genre='" + genre + '\'' +
                ", ageLimit=" + ageLimit +
                ", description='" + description + '\'' +
                '}';
    }
}


