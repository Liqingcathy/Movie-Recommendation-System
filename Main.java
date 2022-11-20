import java.io.*;

public class Main {
    public static void main(String[] args) {
    /*
       //Week1
    FirstRatings fr = new FirstRatings();
    File file = new File("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");   
   
    fr.testLoadMovies();
    
    File file2 = new File("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");   
    fr.testLoadRaters();
    
    //Week2
    SecondRatings sr = new SecondRatings("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv",
    "/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");
    System.out.println(sr.getMovieSize());
    System.out.println(sr.getRaterSize());
    
    //String movieId = sr.getID("The maze runner");
    //System.out.println(movieId);
    //getAverageRatingOneMovie();
    //sr.getAverageByID("1790864", 1);
    System.out.println(sr.getAverageRatings(1)); //return arrayList of string id and minimalrating
    System.out.println(sr.getAverageRatings(50)); 
    System.out.println(sr.getID("moneyball")); //no such file?
    System.out.println(sr.getID("vacation"));//no such file?
    System.out.println(sr.getID("the maze runner"));//no such file?
    
    
    //Rater.getRating("1210166");  */
    //Week3
    //ThirdRatings third = new ThirdRatings("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");
    //System.out.println("call printAverageRatings:(minimalRaters=35): ");
    //third.getAverageRatings(35);
    
    //System.out.println("print average");
    //MovieRunnerWithFilters.printAverageRatings();
    
    //System.out.println("----------RatingsByYearAfter-----------------");
    //MovieRunnerWithFilters.printAverageRatingsByYearAfter();
    
    //System.out.println("----------RatingsByDirectors-----------------");
    //MovieRunnerWithFilters.printAverageRatingsByDirectors();

    //System.out.println("----------RatingsByMinutes-----------------");
    //MovieRunnerWithFilters.printAverageRatingsByMinutes();

    //System.out.println("----------RatingsByDirectorsAndMinutes-----------------");
    //MovieRunnerWithFilters.printAverageRatingsByDirectorsAndMinutes();

    //System.out.println("----------RatingsByGenre-----------------");
    //MovieRunnerWithFilters.printAverageRatingsByGenre();

    //System.out.println("----------RatingsByYearAfterAndGenre-----------------");
    //MovieRunnerWithFilters.printAverageRatingsByYearAfterAndGenre();

    /*
    MovieDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");
    RaterDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");
    MovieRunnerSimilarRatings sr = new MovieRunnerSimilarRatings();
    //fr.getAverageRatings(4);
    System.out.println("----------PrintSimilarRatings-----------------");
    sr.printSimilarRatings();
    //sr.printSimilarRatingsByGenre();
    System.out.println("----------RatingsByDirector-----------------");
    sr.printSimilarRatingsByDirector();
    System.out.println("----------RatingsByYearAfterAndMinutes-----------------");
    sr.printSimilarRatingsByYearAfterAndMinutes();*/
    
    //Test step 5, print html table
    RecommendationRunner rr = new RecommendationRunner();
    System.out.println(rr.getItemsToRate());
    rr.printRecommendationsFor("65");
    }
}
