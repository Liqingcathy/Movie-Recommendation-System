import edu.duke.*;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {

/**    
Write a method named loadMovies that has one parameter, a String named filename.
This method should process every record from the CSV file whose name is filename, 
a file of movie information, and return an ArrayList of type Movie with all of the movie data from the file.  
**/
public static ArrayList<Movie> loadMovies(String filename){
   ArrayList<Movie> movieInfo = new ArrayList<Movie>();
   //Path filePath = Paths.get(file);
   FileResource fr = new FileResource("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");
   //Returns a CSVParser object to access the contents of an open file.
   CSVParser parser = fr.getCSVParser();
   for(CSVRecord record : parser) {
       String ID = record.get("id"); 
       String Title = record.get("title");
       String Year = record.get("year");
       String Country = record.get("country");
       String Genre = record.get("genre"); 
       String Director = record.get("director");
       int Minutes = Integer.parseInt(record.get("minutes"));
       String Poster = record.get("poster");
       
       Movie movie = new Movie(ID, Title,Year, Country, Genre,Director,Minutes, Poster );      
       movieInfo.add(movie);
    }
    return movieInfo;
}

//Call the method loadMovies on the file ratedmovies_short.csv and store the result in an ArrayList local variable.
public static void testLoadMovies(){
    ArrayList<Movie> movie = new ArrayList<>(loadMovies("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv"));
    System.out.println("There are " + movie.size() + " of movies in the list");
    
    for(Movie movies : movie){
        System.out.println(movies.toString2());
    }
    
    int count = 0;
    int CountMovieLength = 0;
    for(Movie movies : movie){
        //Add code to determine how many movies include the Comedy genre.
        if(movies.getGenres().contains("Comedy")){
            count ++;    
        }
        //Add code to determine how many movies are greater than 150 minutes in length.
        if(movies.getMinutes() > 150){
            CountMovieLength ++;    
        }
    }
        System.out.println("There are total " + count + "Comedy movie(s)");
        System.out.println("There are total " + CountMovieLength + " movie(s) last longer than 150 min.");
    
    
    //Determines the maximum number of movies by any director, and who the directors are that directed that many movies.
    int sum = 1;
    Map<String, Integer> directorMovies = new HashMap<>();
    for(Movie movies : movie) {
        String[] directors = movies.getDirector().split(",");
        for(String director : directors){
            if(!directorMovies.containsKey(director)){
                directorMovies.put(director, 1);
            }else{
                directorMovies.put(director, directorMovies.get(director)+1);
        }
    }
}
    int max  = 0;
    for (String director : directorMovies.keySet()) {
            if (directorMovies.get(director) > max) {
                max = directorMovies.get(director);
            }
        }
        System.out.println("Max. of movies directed by one director: " + max );
}


/*
 * Processes every record from the CSV file whose name is filename, 
 * a file of raters and their ratings, and return an ArrayList of type Rater
 * with all the rater data from the file.
 * //rater_id   movie_id   rating
   //1          68646      10
   //1          113277     10
   //2          68646      10
   //2          1798709    10
*/
public static ArrayList<Rater> loadRaters(String filename){
    ArrayList<Rater> raterInfo = new ArrayList<Rater>(); 
    
    FileResource fr = new FileResource(filename);
    CSVParser csvParser = fr.getCSVParser();
    for(CSVRecord csvRecord : csvParser) {
        String rater_id = csvRecord.get("rater_id");
        String movie_id = csvRecord.get("movie_id");
        int rating = Integer.parseInt(csvRecord.get("rating"));
        
        //each rater has 3 different movies rated
        int count = 0; //count of each rater's rated movie
        for(Rater person : raterInfo){
            if(person.getID().contains(rater_id)){
                person.addRating(movie_id, rating); //method from Rater.class
                count++;
            }
        }
        if(count == 0){ //only add 1 significant rater_id in the arrayList, no duplicates
            //Rater pp = new EfficientRater(rater_id); //constructor Rater(String id) 
            EfficientRater pp = new EfficientRater(rater_id);
            pp.addRating(movie_id,rating); //method from Rater.class
            raterInfo.add(pp); 
            }
    }
    return raterInfo;
}

public static void testLoadRaters(){
    ArrayList<Rater> result =  loadRaters("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");
    System.out.println("Total number of raters are: " + result.size());
   
    //2. Add code to find the number of ratings for a particular rater you specify 
    for(Rater raters : result){
        if(raters.getID() =="193"){ 
            System.out.println("Rater's ID: " + raters.getID() + " has " + raters.numRatings() + " of ratings");
        //to print each rater and their ratings
        ArrayList<String> rating = raters.getItemsRated();
        for(String item : rating){   
            System.out.println("movie id: " + item + " ");
            System.out.println(raters.getRating(item) + "rating");
        }  
    }
}
    
    //3. Add code to find the maximum number of ratings by any rater.      
    int max = 0;
    for(Rater raters : result){
        if(raters.numRatings() > max){
        max = raters.numRatings();
        }
        //System.out.println("User: " + rater.getID() + "rated " + max + " max number of ratings");
    }
    // how many raters have this maximum number of ratings and who those raters are 
    for(Rater raters : result){
        if(raters.numRatings() == max){
         System.out.println("User: " + raters.getID() + " has Max. ratings " + raters.numRatings());
        }
    } 
    
    /**
    Finds the number of ratings a particular movie has. 
    If you run this code on the file ratings_short.csv for the movie “1798709”, 
    you will see it was rated by four raters.
    **/
    ArrayList<String> getItemsRated() stores in string arraylist
    int count = 0 ;
    String movie = "1798709"; //to find in the string arraylist has items
    for(Rater raters : result) {
        ArrayList<String> rating = raters.getItemsRated();
        if(rating.contains(movie)){
            count ++;
            System.out.println(count + " : " + "id= " + raters.getID() + rating );
    }
    }
    System.out.println("1798709 movie has been rated totally " + count);
    
    //Determines how many different movies have been rated by all these raters.
    ArrayList<String> nonDuplicatedMovie = new ArrayList<>();
    for(Rater raters : result) {
        ArrayList<String> rating = raters.getItemsRated();//added
        for(String item : rating){
        if(!nonDuplicatedMovie.contains(item)){ 
            nonDuplicatedMovie.add(item);
        }
    }
}
    System.out.println("There are " + nonDuplicatedMovie.size() + " unique movies overall ");
}
}






