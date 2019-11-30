
/**
 * @author Hsingyi Lin
 * @version 09/12/2019
 */

import java.util.*;

public class RatingComparator implements Comparator<Rating> {
    public int compare(Rating r1, Rating r2) {
        return Double.compare(r1.getValue(), r2.getValue());
    }

}
