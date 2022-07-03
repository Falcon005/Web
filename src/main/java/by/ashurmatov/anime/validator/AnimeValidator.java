package by.ashurmatov.anime.validator;

import by.ashurmatov.anime.entity.Anime;

public class AnimeValidator {
    private AnimeValidator() {

    }

    public static boolean validateAnime(String animeName,String country,int createdYear,String genre,int ageLimit,String description) {
        if(animeName.length() < 2 || animeName.length() > 100 ){
            return false;
        }
        if(country.length() < 2 || country.length() > 45){
            return false;
        }
        if(createdYear < 1895 || createdYear > 3000){
            return false;
        }
        if(genre.length()<2 || genre.length() > 100) {
            return false;
        }
        if(ageLimit < 0 || ageLimit > 21){
            return false;
        }
        if(description.length() > 3000){
            return false;
        }
        return true;
    }

    public static boolean validateInput(String animeName,String country,String createdYear,String genre,String ageLimit,String description){
        if (animeName.isEmpty() || country.isEmpty() || createdYear.isEmpty() || genre.isEmpty() || ageLimit.isEmpty() || description.isEmpty()) {
            return false;
        }
        return true;
    }
}
