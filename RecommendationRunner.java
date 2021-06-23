import java.util.*;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender {
    /*
     * returns a list of strings representing movie IDs that will be used to present movies to the user for them to rate. 
     */
    public ArrayList<String> getItemsToRate (){
       ArrayList<String> movieID = new ArrayList<>();
       MovieDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");
       
    }
    
    public void printRecommendationsFor (String webRaterID){
        
    }
}
