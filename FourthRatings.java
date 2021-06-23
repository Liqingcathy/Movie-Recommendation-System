import java.util.*;
/**
 * Copy methods from ThirdRatings and get FourthRatings to compile.  You should not copy, 
 * nor should you have any instance variables in FourthRatings— you'll use RaterDatabase and 
 * MovieDatabase static methods in place of instance variables—
 * so where you have code with myRaters,​you need to replace the code with calls to methods 
 * in the RaterDatabase class.
 * ○ getAverageByID
 * ○ getAverageRatings
 * ○ getAverageRatingsByFilter
 * 
 * @Liqing Li
 */
public class FourthRatings {
    
 private double getAverageByID(String ID, int minimalRaters){
      //double avg = 0;
      int sum = 0;
      int count = 0;
      for(Rater rater : RaterDatabase.getRaters()){
         if(rater.hasRating(ID)){
            count ++;
            sum += rater.getRating(ID);
            }
        }
      if(count >= minimalRaters)
         return sum / count;
         return 0.0;
}

public ArrayList<Rating> getAverageRatings(int minimalRaters){
    //trueFilter implements filter interface:boolean satisfies(String id){}
    //stores arraylist of movie id that satisfies the moviedatabase movie IDs
    ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    ArrayList<Rating> avgRatings = new ArrayList<Rating>();
    double avg = 0;
    int min = 0;
    //for each movie ID in the ArrayList movies,you’ll need to calculate its 
    //averageRating and return an ArrayList of Ratings for each movie that was 
    //rated by minimalRaters.Make sure this class compiles before moving on.
    for(String id : movies){
        //if(movie.getRating() == minimalRaters)
            //String MID = movie.getID();
            avg = getAverageByID(id ,minimalRaters);
            //Store each such rating in a Rating object in which the movie ID 
            //and the average rating are used in creating the Rating object. 
            Rating rating = new Rating(id, avg);
            if(avg > 0)
            avgRatings.add(rating);   
    }
    return avgRatings;    
}
public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ratings = new ArrayList<>(); //for returing avg ratings
        Filter trueFilter = new TrueFilter();
        ArrayList<String> filteredListMovieID = MovieDatabase.filterBy(trueFilter);
        for(String id : filteredListMovieID){
            if(filterCriteria.satisfies(id)){
                double avg = getAverageByID(id, minimalRaters);
                if( avg > 0 )
                   ratings.add(new Rating(id, avg));
                }
            }
            return ratings;
}
//This method should first translate a rating from the scale 0 to 10 to the scale 5 to 5 and 
//return the dot product of the ratings of movies that they both rated. 
//This method will be called by getSimilarities.
   //simliar rating by multiplying me and other rating
    //Sam[*, 0, -3, 2, *, 3, -4]
    //Me [-3,1, *, -1, 0, -1, 1]
    //Sam and Me: 0*1 + 2*-1 + 3*-1 + -4*1 = -9
private double dotProduct(Rater me, Rater r){
    //To present 1-10 scale, calculate by subtracting 5 from each rating
    ArrayList<String> myRated = me.getItemsRated();
    double similar = 0;
    for(String movieID : myRated){
        if(r.hasRating(movieID)){
           double rRating = r.getRating(movieID)- 5; //myRating
           double meRating = me.getRating(movieID) -5;
           similar += (rRating * meRating);
       }
    }
   
        return similar;
 }

//Computes a similarity rating fore each Rater in the RaterDatabase (except the rater with the 
//ID given by the parameter) to see how similar they are to the Rater whose ID is the parameter to getSimilarities.​

//Note that in each Rating object the item field is a rater’s ID, and the value field is the dot product 
//comparison between that rater and the rater whose ID is the parameter to get Similarities . ​ 
//Be sure not to use the ​dotProduct method with parameter id and itself !
private ArrayList<Rating> getSimilarities(String id){
    ArrayList<Rating> similarRatings = new ArrayList<>();
    ArrayList<Rater> raters = RaterDatabase.getRaters(); //
    for(Rater r : raters){
        if(!r.getID().equals(id)){
        double dotProduct = dotProduct(RaterDatabase.getRater(id), r);
        if(dotProduct > 0){
            //This method returns an ArrayList of type Rating sorted​by ratings 
            //from highest to lowest rating with the highest rating first and only including those raters who have a 
            //positive similarity rating since those with negative values are not similar in any way. 
            similarRatings.add(new Rating(r.getID(), dotProduct)); 
            }
        }
    }

    Collections.sort(similarRatings);
    Collections.reverse(similarRatings);
    return similarRatings;

}

/*
 * Returns an ArrayList of type Rating, of movies and their weighted average ratings using only the top 
 * numSimilarRaters​with positive ratings and including only those movies that have at least minimalRaters​
 * ratings from those top raters. 
 * These Rating objects should be returned in sorted order by weighted average rating from largest to smallest ratings.
 * This method is very much like the get AverageRatings method you have written previously. 
 */
public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters){
    ArrayList<Rating> ratings = new ArrayList<>();
    ArrayList<String> movieIDByTopSimilar = new ArrayList<>();
    ArrayList<Rating> similarRatings = getSimilarities(raterID);
    for(int i=0; i< numSimilarRaters; i++){
        String raterIDs = similarRatings.get(i).getItem();
        ArrayList<String> movieRated = RaterDatabase.getRater(raterIDs).getItemsRated();
        for(String movieID : movieRated){
            if(!movieIDByTopSimilar.contains(movieID)){
                movieIDByTopSimilar.add(movieID);
            }
        }
    }
    for(String id : movieIDByTopSimilar){
        ArrayList<Rating> similarRatings2 = getSimilarities(raterID);
        int top = 0;
        int sum = 0;
        double weightSimilar = 0.0;
        double avg = 0.0;
    //For each movie, calculate a weighted average movie rating based on:
    //Use only the top (largest) numSimilarRaters​raters.
    //For each of these raters, multiply their similarity rating by the rating they gave that movie.
    for(int i=0; i < numSimilarRaters; i++){
        //**?? top rating..?
        double rating = RaterDatabase.getRater(similarRatings2.get(i).getItem()).getRating(id);
        if(rating != -1){
            top++;
            sum += rating * similarRatings.get(i).getValue();
            weightSimilar += similarRatings.get(i).getValue();
            //avg = sum / weightSimilar;
            //similarRatings.add(avg);
        }
    }
    if(top >= minimalRaters){
        avg = sum / top;
    }
    if(avg > 0 ){
        ratings.add(new Rating(id, avg)); //??
    //return similarRatings;
    }
}
    
    Collections.sort(ratings);
    Collections.reverse(ratings);
    return ratings;

}

//similar to the getSimilarRatings​method but has one additional Filter parameter named filterCriteria​and 
//uses that filter to access and rate only those movies that match the filter criteria.
public ArrayList<Rating> getSimilarRatingsByFilter(String raterID, int numSimilarRaters, int minimalRaters, Filter filter){
    ArrayList<Rating> ratings = new ArrayList<>();
    ArrayList<String> movieIDByTopSimilar = new ArrayList<>();
    ArrayList<Rating> similarRatings = getSimilarities(raterID);
    for(int i=0; i< numSimilarRaters; i++){
        String raterIDs = similarRatings.get(i).getItem();
        ArrayList<String> movieRated = RaterDatabase.getRater(raterIDs).getItemsRated();
        for(String movieID : movieRated){
            if(!movieIDByTopSimilar.contains(movieID)){
                movieIDByTopSimilar.add(movieID);
            }
        }
    }
    for(String id : movieIDByTopSimilar){
        if(filter.satisfies(id)){
        ArrayList<Rating> similarRatings2 = getSimilarities(raterID);
        int top = 0;
        int sum = 0;
        double weightSimilar = 0.0;
        double avg = 0.0;
    //For each movie, calculate a weighted average movie rating based on:
    //Use only the top (largest) numSimilarRaters​raters.
    //For each of these raters, multiply their similarity rating by the rating they gave that movie.
    for(int i=0; i < numSimilarRaters; i++){
        //**?? top rating..?
        double rating = RaterDatabase.getRater(similarRatings2.get(i).getItem()).getRating(id);
        if(rating > 0){
            top++;
            sum += rating * similarRatings.get(i).getValue();
            weightSimilar += similarRatings.get(i).getValue();
            //avg = sum / weightSimilar;
            //similarRatings.add(avg);
        }
    }

    if(top >= minimalRaters){
        avg = sum / top;
    }
    if(avg > 0 ){
        ratings.add(new Rating(id, avg)); 
    //return similarRatings;
    }
}
}
    
    Collections.sort(ratings);
    Collections.reverse(ratings);
    return ratings;
} 
}
