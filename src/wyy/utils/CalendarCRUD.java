package wyy.utils;

import java.util.*;

import org.litepal.crud.DataSupport;

import wyy.bean.CalendarEvents;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import android.util.Log;

public class CalendarCRUD {

	public static long addEventToCalendar(Context context, long id,
			long startm, long endm, String events, int remindtime,
			String description) {
		long calID = id;
		long startMillis = startm;
		long endMillis = endm;

		// Calendar beginTime = Calendar.getInstance();
		// beginTime.set(2012, 9, 14, 7, 30);
		// startMillis = beginTime.getTimeInMillis();
		// Calendar endTime = Calendar.getInstance();
		// endTime.set(2012, 9, 14, 8, 45);
		// endMillis = endTime.getTimeInMillis();
		ContentResolver cr = context.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Events.DTSTART, startMillis);
		values.put(Events.DTEND, endMillis);
		values.put(Events.ORGANIZER, "wyytzf@163.com");
		values.put(Events.TITLE, events);
		values.put(Events.DESCRIPTION, description);
		values.put(Events.CALENDAR_ID, calID);
		values.put(Events.EVENT_TIMEZONE, "Asia/Chongqing");
		Uri uri = cr.insert(Events.CONTENT_URI, values);
		// get the event ID that is the last element in the Uri
		long eventID = Long.parseLong(uri.getLastPathSegment());
		if (remindtime != 0) {
			addReminder(context, eventID, remindtime);
		}
		return eventID;
	}

	public static List<CalendarEvents> queryEventsAtLocal() {
		List<CalendarEvents> ce_lists = new ArrayList<CalendarEvents>();
		ce_lists = DataSupport.findAll(CalendarEvents.class, true);
		return ce_lists;
	}

	private static void addReminder(Context context, long ID, int remindtime) {
		long eventID = ID;
		ContentResolver cr = context.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Reminders.MINUTES, remindtime);
		values.put(Reminders.EVENT_ID, eventID);
		values.put(Reminders.METHOD, Reminders.METHOD_ALERT);
		Uri uri = cr.insert(Reminders.CONTENT_URI, values);
	}

	public static String[] getTimeZone() {
		return TimeZone.getAvailableIDs();
	}

	public static void delete(Context context, long eventID) {

		Uri deleteUri = null;
		deleteUri = ContentUris.withAppendedId(Events.CONTENT_URI, eventID);
		int rows = context.getContentResolver().delete(deleteUri, null, null);
		Log.i("localdatabase", "rows:" + rows);
	}
}
