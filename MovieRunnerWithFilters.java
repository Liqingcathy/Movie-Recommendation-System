
import java.util.*;
/**
 * find the avearage rating of movies using different filters.
 * 
 * @author (Liqing Li) 
 * @version (5/1/2021)
 */
public class MovieRunnerWithFilters {
    
    public static void printAverageRatings(){
        //create a ThirdRatings object with one parameter(rating data file)
        ThirdRatings tr1 = new ThirdRatings("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");
        //tr = private ArrayList<Rater> myRaters -> list of (movieID, rating)
        
        System.out.println("rater numbers: " + tr1.getRaterSize());
        
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
        ArrayList<Rating> avgRating = tr1.getAverageRatings(minimalRaters);
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
 * create a Year After Filter and call getAverageRatingsByFilter ​
 * to get an ArrayList of type Rating of all the movies that have a specified number of minimal 
 * ratings and came out in a specified year or later. 
 * Print the number of movies found, and for each movie found, print its rating, its year, and its title.
 */
    public static void printAverageRatingsByYearAfter(){
     //create a ThirdRatings object with one parameter(rating data file)
        ThirdRatings tr2 = new ThirdRatings("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");
        //tr = private ArrayList<Rater> myRaters -> list of (movieID, rating)
        
        System.out.println("read data for: " + tr2.getRaterSize() + " rators");
        
        //call the MovieDatabase​initialize(movie file)​to set up the movie database.
        MovieDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");
        
        //Print the number of movies in the database.
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int year = 2000; //define year by author
        //Create a year after filter and call filter function(year after filter is not interface)
        YearAfterFilter yearFilter = new YearAfterFilter(year);
        
        //call getAverageRatings(int minimalRater)return an ArrayList of type Rating.
        int minimalRaters = 20;
        ArrayList<Rating> avgRating = tr2.getAverageRatingsByFilter(minimalRaters, yearFilter);
      
        System.out.println("there are average " + avgRating.size() + " of movies" 
        + minimalRaters + " of raters");
        
        //7.0 2013 Dallas Buyers Club
        Collections.sort(avgRating);
        for(Rating rating : avgRating){
            System.out.println(avgRating.size() + " movie matched" );
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) +
            " " + MovieDatabase.getTitle(rating.getItem()));
        
        }
    }
    
     public static void printAverageRatingsByGenre(){
         //create a ThirdRatings object with one parameter(rating data file)
        ThirdRatings tr3 = new ThirdRatings("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");     
        //call the MovieDatabase​initialize(movie file)​to set up the movie database.
        MovieDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");
        
        
        String genre = "Comedy"; //define year by author Romance//test main comedy
        GenreFilter genreFilter = new GenreFilter(genre);
        
        //call getAverageRatings(int minimalRater)return an ArrayList of type Rating.
        int minimalRaters = 20;
        ArrayList<Rating> avgRating = tr3.getAverageRatingsByFilter(minimalRaters, genreFilter);
        System.out.println(avgRating.size() + " movie matched" );
        
        //int count = 0;
        //9.0 The Godfather Crime, Drama
        /*
        Collections.sort(avgRating);
        for(Rating rating : avgRating){
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) +
            " " + MovieDatabase.getGenres(rating.getItem()));
        }*/
    }
    //given values: with a minimal rater of 1, minimum minutes of 110, and maximum minutes of 170
    public static void printAverageRatingsByMinutes(){
         //create a ThirdRatings object with one parameter(rating data file)
        ThirdRatings tr4 = new ThirdRatings("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");     
        //call the MovieDatabase​initialize(movie file)​to set up the movie database.
        MovieDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");
      
        //call getAverageRatings(int minimalRater)return an ArrayList of type Rating.
        int minimalRaters = 5;
        ArrayList<Rating> avgRating = tr4.getAverageRatingsByFilter(minimalRaters, new MinutesFilter(105,135));
        System.out.println(avgRating.size() + " movie matched" );
        //9.0 The Godfather Crime, Drama
        /*
        Collections.sort(avgRating);
        for(Rating rating : avgRating){
            System.out.println(rating.getValue() + " " + "Time: " + 
            MovieDatabase.getMinutes(rating.getItem()) +
            " " + MovieDatabase.getTitle(rating.getItem()));
        }*/
    }
    //Print the number of movies found, and for each movie print its rating and its title on one line, 
    //and all its directors on the next line.
    //a minimal rater of 1 and the directors set to "Charles Chaplin,Michael Mann,Spike Jonze"
    // 8.25 Her
    // Spike Jonze
    public static void printAverageRatingsByDirectors(){
         //create a ThirdRatings object with one parameter(rating data file)
        ThirdRatings tr5 = new ThirdRatings("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");     
        System.out.println("read data for: " + tr5.getRaterSize() + " rators");
        
        //call the MovieDatabase​initialize(movie file)​to set up the movie database.
        MovieDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");
        //Print the number of movies in the database.
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        
        //call getAverageRatings(int minimalRater)return an ArrayList of type Rating.
        int minimalRaters = 4;
        ArrayList<Rating> avgRating = tr5.getAverageRatingsByFilter(minimalRaters, directorsFilter);
        System.out.println(avgRating.size() + " movie matched" );
        //9.0 The Godfather Crime, Drama
        Collections.sort(avgRating);
        for(Rating rating : avgRating){
            
            System.out.println(rating.getValue() + " " +  MovieDatabase.getTitle(rating.getItem()) +
            " " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    //This method should call getAverageRatingsByFilter​to get an ArrayList of type Rating of 
    //all the movies that have a specified number of minimal ratings and the two criteria based 
    //on year and genre. 
    // 8.25 2013 Her
    // Drama, Romance, Sci-Fi
    public static void printAverageRatingsByYearAfterAndGenre(){
        
        ThirdRatings tr6 = new ThirdRatings("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");     
        
        MovieDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");
        
        int minimalRaters = 8;
        int year = 1990; //given by instruction
        YearAfterFilter yearFilter = new YearAfterFilter(year);
        String genre = "Drama"; //given by instruction
        GenreFilter genreFilter = new GenreFilter(genre);
        
        //allFilter class constructor: filters = new ArrayList<Filter>();
        //allFilter class method: public void addFilter(Filter f) 
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);
        
        ArrayList<Rating> Rating = tr6.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println(Rating.size() + " movie matched" );
        Collections.sort(Rating);
        for(Rating rating : Rating){
            System.out.println(rating.getValue() + " "  
            + MovieDatabase.getYear(rating.getItem()) + " " 
            + MovieDatabase.getTitle(rating.getItem()) +
            " " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    //1, minimum minutes set to 30, maximum minutes set to 170, 
    //and the directors set to "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"
    // 8.25 Time: 126 Her
    // Spike Jonze
    public static void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings tr7 = new ThirdRatings("/Users/liqingli/Desktop/转码/4.Project/data/ratings.csv");     
        System.out.println("read data for: " + tr7.getRaterSize() + " rators");
        
        MovieDatabase.initialize("/Users/liqingli/Desktop/转码/4.Project/data/ratedmoviesfull.csv");
        //Print the number of movies in the database.
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int minimalRaters = 3;
        int minLength = 90; //given by instruction
        int maxLength = 180;
        MinutesFilter minutesFilter = new MinutesFilter(minLength, maxLength);
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"; //given by instruction
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        
        //allFilter class constructor: filters = new ArrayList<Filter>();
        //allFilter class method: public void addFilter(Filter f) 
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(minutesFilter);
        allFilters.addFilter(directorsFilter);
        
        ArrayList<Rating> Rating = tr7.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println(Rating.size() + " movie matched" );
        Collections.sort(Rating);
        for(Rating rating : Rating){
            System.out.println(rating.getValue() + " "  
            + MovieDatabase.getYear(rating.getItem()) + " " + "Time: "  + MovieDatabase.getMinutes(rating.getItem()) + 
            " " + MovieDatabase.getTitle(rating.getItem()) +
            " " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
}

