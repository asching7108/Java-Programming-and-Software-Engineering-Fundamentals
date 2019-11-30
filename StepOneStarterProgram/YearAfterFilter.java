
/**
 * Filter of year.
 * 
 * @author Hsingyi Lin
 * @version 09/13/2019
 */

public class YearAfterFilter implements Filter {
	private int myYear;
	
	public YearAfterFilter(int year) {
		myYear = year;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getYear(id) >= myYear;
	}

}
