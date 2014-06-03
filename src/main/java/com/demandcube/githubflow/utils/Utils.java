package com.demandcube.githubflow.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
	public static Date getStartOfWeek() {
		// Get calendar set to current date and time
		Calendar c = Calendar.getInstance(Locale.US);

		// Set the calendar to monday of the current week
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);

		return c.getTime();
	}

	public static Date getStartOfPreviousWeek(int weeksBack) {
		// Get calendar set to current date and time
		Calendar c = Calendar.getInstance(Locale.US);

		// Set the calendar to monday of the current week
		c.add(Calendar.WEEK_OF_YEAR, -weeksBack);
		// first day
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);

		return c.getTime();
	}

	public static Date getEndOfPreviousWeek(int weeksBack) {
		// Get calendar set to current date and time
		Calendar c = Calendar.getInstance(Locale.US);

		// Set the calendar to monday of the current week
		c.add(Calendar.WEEK_OF_YEAR, -weeksBack);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()-1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);

		return c.getTime();
	}
}
