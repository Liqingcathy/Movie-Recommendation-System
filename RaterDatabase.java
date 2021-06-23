/**
 *  R​aterDatabase​class that is designed and implemented similarly to the MovieDatabase class you used 
 *  recently—you will use both of these database classes in this assignment. You will also calculate a 
 *  different kind of average movie rating, one based on weighting ratings made by raters who are more 
 *  like you, or like any given rater—valuing their ratings more than raters who don't have your tastes 
 *  in movies. To calculate these weighted averages you will need to calculate similarity scores for each 
 *  rater to find out which raters you are more similar to than others, so you can weight ratings accordingly.
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class RaterDatabase {
    //A HashMap named ourRaters​that maps a rater ID String 
    //to a Rater object that includes all the movie ratings made by this rater.
    private static HashMap<String,Rater> ourRaters;
     
	private static void initialize() {
	    // this method is only called from addRatings 
	    //A private i​nitialize​method with no parameters 
	    //that initializes the HashMap o​urRaters​if it does not exist.
		if (ourRaters == null) {
			ourRaters = new HashMap<String,Rater>();
		}
	}

    public static void initialize(String filename) {
 		if (ourRaters == null) {
 			ourRaters= new HashMap<String,Rater>();
 			addRatings("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");
 		}
 	}	
 	
    public static void addRatings(String filename) {
        initialize(); 
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();
        for(CSVRecord rec : csvp) {
                String id = rec.get("rater_id");
                String item = rec.get("movie_id");
                String rating = rec.get("rating");
                addRaterRating(id,item,Double.parseDouble(rating));
        } 
    }
    /*
     * A public static void a​ddRaterRating​method that has three parameters, a String named 
     * raterID​representing a rater ID, a String named m​ovieID​that represents a movie ID, 
     * and a double named rating​that is the rating the rater raterID​has given to the movie 
     * movieID.​This function can be used to add one rater and their movie rating to the database. 
     * Notice that the method addRatings​calls this method.
     */
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize(); 
        Rater rater =  null;
                if (ourRaters.containsKey(raterID)) {
                    rater = ourRaters.get(raterID); 
                } 
                else { 
                    rater = new EfficientRater(raterID);
                    ourRaters.put(raterID,rater);
                 }
                 rater.addRating(movieID,rating);
    } 
    //A method getRater​has one String parameter named id.​
    //This method returns a Rater that has this ID.	         
    public static Rater getRater(String id) {
    	initialize();
    	
    	return ourRaters.get(id);
    }
    
    //A method getRaters​that has no parameters. 
    //This method returns an ArrayList of Raters from the database.
    public static ArrayList<Rater> getRaters() {
    	initialize();
    	ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
    	
    	return list;
    }
 
    public static int size() {
	    return ourRaters.size();
    }
      
}

