import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Calculator {

	// Constants
	private static final List<SimpleDateFormat> DATE_FORMATS = new ArrayList<>() {{
		add(DateUtilities.getDdMMyyFormat());
		add(DateUtilities.getDdMMyyyyFormat());
		add(DateUtilities.getDdMMyySlashFormat());
		add(DateUtilities.getDdMMyyyySlashFormat());
		add(DateUtilities.getDdMMyyPointFormat());
		add(DateUtilities.getDdMMyyyyPointFormat());
	}};

	/**
	 *  Convert text input to a Date
	 * @param text - String of date
	 * @return Date if the text is a number, and otherwise return null
	 */
	public static Date getDate(String text) {
		for (SimpleDateFormat format : DATE_FORMATS) {
			try {
				return format.parse(text);
			} catch (ParseException ignored) {
			}
		}
		return null;
	}

	/**
	 * Convert text input to a Integer
	 * @param text - String of number
	 * @return Integer if the text is a number, and otherwise return null
	 */
	public static Integer getDaysToAdd(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException ignored) {
		}
		return null;
	}

	/**
	 * @param cal - Calendar with some format
	 * @return String of date with SLASH_FORMAT
	 */
	private static String formatDate(Calendar cal) {
		return DateUtilities.getDdMMyySlashFormat().format(cal.getTime());
	}

	/**
	 * @param daysInString - The number of days to add
	 * @param dateInString - The date to add The days to
	 * @return String of the new date 
	 */
	public static String calculate(String daysInString, String dateInString) {
		Integer daysToAdd = getDaysToAdd(daysInString);
		Date date = getDate(dateInString);
		Calendar cal = GregorianCalendar.getInstance();
        if ((date == null) || (daysToAdd == null)) {
            return null;
        }
        cal.setTime(date);
        cal.add(Calendar.DATE, daysToAdd);
        return formatDate(cal);
    }
}

