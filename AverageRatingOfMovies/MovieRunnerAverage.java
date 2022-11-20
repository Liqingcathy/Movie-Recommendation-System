
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    

    // Create a SecondRatings object and use the CSV filenames of movie information 
    // and ratings information from the first assignment when calling the constructor.
public static void printAverageRatings(){
        SecondRatings sr = new SecondRatings("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv",
        "/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");
        System.out.println(sr.getMovieSize());
        System.out.println(sr.getRaterSize());
        
        ArrayList<Rating> mRating = new ArrayList<Rating>();
        for(Rating r : mRating){
            System.out.println(sr.getAverageRatings(10));
            //mRating.add(sr.getTitle(r.getItem())); //string convert to rating error
            System.out.printf("-10.2f%s%n", r.getValue(), sr.getTitle(r.getItem()));
        }
    }
    
public static void getAverageRatingOneMovie(){
    SecondRatings sr = new SecondRatings("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv",
     "/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");
    ArrayList<Rating> ratingList = new ArrayList<Rating>();
    for(Rating r : ratingList){
          if(sr.getTitle(r.getItem()) == "The Godfather" ){
          printAverageRatings();
          }
    }
}
}

    /*
     * Print the number of movies and number of raters from the two files by calling the appropriate methods 
     * in the SecondRatings class. Test your program to make sure it is reading in all the data from the two files. 
     * For example, if you run your program on the files ratings_short.csv and ratedmovies_short.csv, 
     * you should see 5 raters and 5 movies.
     */
