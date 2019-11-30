
/**
 * Filter of minutes.
 * 
 * @author Hsingyi Lin
 * @version 09/13/2019
 */

public class MinutesFilter implements Filter {
	private int myMin;
	private int myMax;
	public MinutesFilter(int min, int max) {
	    myMin = min;
	    myMax = max;
	}
	
	@Override
	public boolean satisfies(String id) {
	    int minute = MovieDatabase.getMinutes(id);
		return minute >= myMin && minute <= myMax;
	}

}
