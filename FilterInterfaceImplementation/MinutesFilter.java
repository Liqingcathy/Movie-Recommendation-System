
/**
 *  Its satisfies method should return true if a movie’s running time is 
 *  at least min minutes and no more than max minutes.
 * 
 */
public class MinutesFilter implements Filter{
   private int minMin;
   private int maxMin;
   
   public MinutesFilter(int min, int max){
       minMin = min;
       maxMin = max;
    }
   //return true if a movie’s running time is at least min​minutes and no more than max​minutes. 
   @Override
   public boolean satisfies(String id) {
       return ((MovieDatabase.getMinutes(id) >= minMin) && (MovieDatabase.getMinutes(id) <= maxMin));
}
}
