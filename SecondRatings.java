
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() { //default constructor
        // default constructor
        this("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv",
        "/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");
    }
    /*
     * Write an additional SecondsRating constructor that has two String parameters named moviefile​and r​atingsfile.​The constructor should create a FirstRatings object and then c a l l t h e l ​o a d M o v i e s ​a n d l ​o a d R a t e r s ​m e t h o d s i n F i r s t R a t i n g s t o r e a d i n a l l t h e m o v i e
Java Programming: A DIY Version of Netflix and Amazon Recommendation Engines
and ratings data and store them in the two private ArrayList variables of the
SecondRatings class, ​myMovies​and m​yRaters.​
     */
    public SecondRatings(String moviefile, String ratingfile) {//constructor has file name parameters
       myMovies = FirstRatings.loadMovies(moviefile);
       myRaters = FirstRatings.loadRaters(ratingfile);
      
    }
    
    public int getMovieSize(){
     return myMovies.size();   
    }
    
    public int getRaterSize(){
     return myRaters.size();   
    }
    
    //returns a double representing the average movie rating for this ID if there are at least minimalRaters ratings. 
      private double getAverageByID(String ID, int minimalRaters){
      double avg = 0;
      int sum = 0;
      int count = 0;
      for(Rater rater : myRaters){
         if(rater.hasRating(ID)){
            count ++;
            sum += rater.getRating(ID);
            }
        }
      if(count >= minimalRaters)
         avg = sum / count;
         return avg;
}


//find the average rating for every movie that has been rated by at least minimalRaters raters. Store each such 
//rating in a Rating object in which the movie ID and the average rating are used in creating the Rating object. 

//For example, if minimalRaters has the value 10, then this method returns rating information (the movie ID and 
//its average rating) for each movie that has at least 10 ratings. You should consider calling the private getAverageByID method.
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
    ArrayList<Rating> avgRating = new ArrayList<Rating>();
    double avg = 0;
    int min = 0;
    for(Movie movie : myMovies){
        //if(movie.getRating() == minimalRaters)
            String MID = movie.getID();
            avg = getAverageByID(MID ,minimalRaters);
            //Store each such rating in a Rating object in which the movie ID 
            //and the average rating are used in creating the Rating object. 
            Rating rating = new Rating(MID, avg);
            avgRating.add(rating);   
    }
    return avgRating;    
}

public String getTitle(String mID){
    for(Movie m : myMovies){
        if(m.equals(mID)){
            return m.getTitle();
        }
    }
            return "This movie ID is not exsiting";
}


public String getID(String mTitle){
    for(Movie m : myMovies){
        if(m.getTitle() == mTitle)
            return m.getID();
        }
            return "No such title";
        
    }
}
