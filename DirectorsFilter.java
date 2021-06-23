
/**
 * Create a new class named DirectorsFilter​that implements Filter. 
 * The constructor should have one parameter named directors​representing a list of directors 
 * separated by commas (Example: "Charles Chaplin,Michael Mann,Spike Jonze", 
 * and its satisfies​method should return true if a movie has at least one of these directors as one of its directors. 
 * Note that each movie may have several directors.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String director;
    
    public DirectorsFilter(String directors){
        director = directors;
}

//might have a few directors for one movie
 @Override
   public boolean satisfies(String id) {
       String[] directs = director.split(",");
       for(String dir: directs){
           if(MovieDatabase.getDirector(id).contains(dir)){
               return true;
            }
        }
        return false;
}
}

