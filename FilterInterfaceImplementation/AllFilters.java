import java.util.ArrayList;
/*
 * Combines asking questions about average ratings by genre
 * and films on or after a particular year. 
 */
public class AllFilters implements Filter {
    ArrayList<Filter> filters;
    
    //contains all filter in an array: generic Filter arraylist
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    // 8.25 2013 Her
    // Drama, Romance, Sci-Fi
    // satisfies both year filter and genre filter
    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (!f.satisfies(id)) {//if not satisfies, keep search
                return false; 
                
        }
    }
        return true; //if satisfies one, return true
}
}
