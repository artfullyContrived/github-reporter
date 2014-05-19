package com.demandcube.githubflow.utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterators;
import com.google.common.io.Files;

public class Utils {
	public static Date getStartOfWeek() {
		// Get calendar set to current date and time
		Calendar c = Calendar.getInstance();

		// Set the calendar to monday of the current week
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);

		return c.getTime();
	}

	public static Date getStartOfPreviousWeek(int weeksBack) {
		// Get calendar set to current date and time
		Calendar c = Calendar.getInstance();

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
		Calendar c = Calendar.getInstance();

		// Set the calendar to monday of the current week
		c.add(Calendar.WEEK_OF_YEAR, -weeksBack);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);

		return c.getTime();
	}

	public static String getPassword() {

		String x = "";
		try {
			x = Iterators.get((Files.readLines(new File(
					"C:\\Users\\Anthony\\Desktop\\cred.txt"), Charsets.UTF_8)
					.iterator()), 1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return x;
	}

	public static String getUserName() {
		String x = "";
		try {
			x = Iterators.get((Files.readLines(new File(
					"C:\\Users\\Anthony\\Desktop\\cred.txt"), Charsets.UTF_8)
					.iterator()), 0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return x;
	}
}
