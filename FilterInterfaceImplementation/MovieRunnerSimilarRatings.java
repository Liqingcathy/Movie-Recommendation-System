import java.util.*;
/**
 * You should not copy, nor should you have any instance variables in FourthRatings—
 * you'll use RaterDatabase and MovieDatabase static methods in place of instance variables—
 * 
 @author Liqing Li
 * 
 */
public class MovieRunnerSimilarRatings {

    
public void printAverageRatings(){
        //create a ThirdRatings object with one parameter(rating data file)
        FourthRatings fr = new FourthRatings();
        //tr = private ArrayList<Rater> myRaters -> list of (movieID, rating)
        
       // System.out.println("rater numbers: " + fr.getRaterSize());
        
        //call the MovieDatabase​initialize(movie file)​to set up the movie database.
        // MovieDatabase Hashmap<String,Movie>;
        MovieDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");
        /*
         * public static void initialize(String moviefile) {
            loadMovies("data/" + moviefile);
            output:
            movieDatabase = ourMovies.put(m.getID(), m);  
            m = new Movie(ID, Title,Year, Country, Genre,Director,Minutes, Poster );   
         */
        
        //Print the number of movies in the database.
        System.out.println("movie numbers: " + MovieDatabase.size());
        
        //call getAverageRatings(int minimalRater)return an ArrayList of type Rating.
        int minimalRaters = 35;
        ArrayList<Rating> avgRating = fr.getAverageRatings(minimalRaters);
        //how many movies with ratings are returned, 
        //then sort them, and print out the rating and title of each movie.
        //System.out.println("there are average " + avgRating.size() + "of movies" 
        //+ minimalRaters + " of raters");
        
        Collections.sort(avgRating);
        for(Rating rating : avgRating){
            System.out.println(rating.getValue() + " " + 
            MovieDatabase.getTitle(rating.getItem()));
        
        }
    }
    
/*
 * Creates a new FourthRatings object, reads data into the MovieDatabase and RaterDatabase, 
 * and then calls getSimilarRatings​for a particular rater ID, a number for the top number of similar raters, 
 * and a number of minimal rateSimilarRatings,​and then lists recommended movies and their similarity ratings. 
 */
 public void printSimilarRatings(){
     RaterDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");
     MovieDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");
     FourthRatings fr = new FourthRatings();  
     String id = "71"; //337
     int minimalRater = 5; //3
     int topSimRater = 20; //10
     ArrayList<Rating> ratings = fr.getSimilarRatings(id, topSimRater, minimalRater);    
     System.out.println("Found ratings for movies : " + ratings.size());
     
     for(Rating r : ratings){
        String movieTitle = MovieDatabase.getTitle(r.getItem());
        System.out.println(movieTitle + " " + r.getValue());
     /*
     for (int i = 0; i < ratings.size(); i++) {
         System.out.printf("%-10.2f%s%n", ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
     }*/
 }
}
 /*
  * This method is similar to printSimilarRatings​but also uses a genre filter and then lists recommended movies 
  * and their similarity ratings, and for each movie prints the movie and its similarity rating on one line and 
  * its genres on a separate line below it. 
  * For example, the genre “Action”, the rater ID 65, the number of minimal raters set to 5, 
  * and the number of top similar raters set to 20, the movie returned with the top rated average is “Rush”.
  */
 public void printSimilarRatingsByGenre(){
   FourthRatings fr4 = new FourthRatings(); 
   ArrayList<Rating> ratings = fr4.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));
   System.out.println("Found ratings for movies : " + ratings.size());
   for (int i = 0; i < 3; i++) {
       System.out.printf("%-10.2f%-16s%-5s%n", ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()), MovieDatabase.getGenres(ratings.get(i).getItem()));
   }
}
/*
 * This method uses a director filter and then lists recommended movies and their similarity ratings, and for each movie prints the movie 
 * and its similarity rating on one line and its directors on a separate line below it. 
 * For example,​the directors “Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone”, the rater ID 1034, the number of minimal raters 
 * set to 3, and the number of top similar raters set to 10, the movie returned with the top rated average is “Unforgiven”.
 */
public void printSimilarRatingsByDirector(){
    RaterDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");
     MovieDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");
   
    FourthRatings fr5 = new FourthRatings();  
    ArrayList<Rating> ratings = fr5.getSimilarRatingsByFilter("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
    
    System.out.println("Found ratings for movies : " + ratings.size());
    for(Rating r : ratings){
     String movieTitle = MovieDatabase.getTitle((r.getItem()));
     System.out.println(movieTitle+ " : " + r.getValue());
    /*
    int printNum = ratings.size();
    if (printNum >= 3) printNum = 3;
    for (int i = 0; i < printNum; i++) {
            
        System.out.printf("%-10.2f%-16s%-5s%n", ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()), MovieDatabase.getDirector(ratings.get(i).getItem()));
    */
    }
}
/*
 * uses a genre filter and a minutes filter and then lists recommended movies and their similarity ratings, 
 * and for each movie prints the movie, its minutes, and its similarity rating on one line and its genres on a separate line below it.
 * thegenre“Adventure”, minutes between 100 and 200 inclusive, the rater ID 65, the number of minimal raters set to 5, and the number of 
 * top similar raters set to 10, the movie returned with the top rated average is “Interstellar”.
 */
public void printSimilarRatingsByGenreAndMinutes(){
    FourthRatings fr6 = new FourthRatings();  
    AllFilters all = new AllFilters();
    all.addFilter(new GenreFilter("Adventure"));
    all.addFilter(new MinutesFilter(100, 200));
    ArrayList<Rating> ratings = fr6.getSimilarRatingsByFilter("65", 10, 5, all);
    System.out.println("Found ratings for movies : " + ratings.size());
    
    int printNum = ratings.size();
    if (printNum >= 3) printNum = 3;
    for (int i = 0; i < printNum; i++) {
        System.out.printf("%-10.2f%-5d%-16s%-5s%n", ratings.get(i).getValue(), MovieDatabase.getMinutes(ratings.get(i).getItem()), MovieDatabase.getTitle(ratings.get(i).getItem()), MovieDatabase.getGenres(ratings.get(i).getItem()));
    }
}

/*
 * uses a year­after filter and a minutes filter and then lists recommended movies and their similarity ratings, 
 * and for each movie prints the movie, its year, its minutes, and its similarity rating on one line. 
 * For example,​the year 2000, minutes between 80 and 100 inclusive, the rater ID 65, the number of minimal raters set * to 5, and the number of top similar raters set to 10, the movie returned with the top rated average is “The Grand *  * Budapest Hotel”.
 */
public void printSimilarRatingsByYearAfterAndMinutes(){
    FourthRatings fr7 = new FourthRatings();  
    AllFilters all = new AllFilters();
    all.addFilter(new YearAfterFilter(1975));
    all.addFilter(new MinutesFilter(70, 200));
    ArrayList<Rating> ratings = fr7.getSimilarRatingsByFilter("314", 10, 5, all);
    System.out.println("Found ratings for movies : " + ratings.size());
    
    int printNum = ratings.size();
    if (printNum >= 3) printNum = 3;
    for (int i = 0; i < printNum; i++) {
        System.out.printf("%-10.2f%-5s%n", ratings.get(i).getValue(), MovieDatabase.getMovie(ratings.get(i).getItem()).toString());
    }   
   
}
}
