
/**
 * Filter of directors.
 * 
 * @author Hsingyi Lin
 * @version 09/13/2019
 */

import java.util.*;

public class DirectorsFilter implements Filter {
    private String[] myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = directors.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        String drct = MovieDatabase.getDirector(id);
        for (String drctFilter : myDirectors) {
            if (drct.contains(drctFilter)) {
                return true;
               }
        }
        return false;
    }

}
