package wyy.activity;

import java.util.Calendar;

import org.litepal.crud.DataSupport;

import wyy.bean.CalendarEvents;
import wyy.utils.CalendarCRUD;
import wyy.utils.Constants;
import wyy.widget.DeleteDialog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.imagemanager6.R;

public class ScheduleWatchActivity extends Activity {

	private CalendarEvents ce;
	private TextView eventName;
	private TextView eventDate;
	private TextView eventTime;
	private TextView eventRemarks;
	private View deleteLayout;
	private Calendar calendar = Calendar.getInstance();
	private DeleteDialog deleteDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_watchschedule);
		this.setTitle("详细日程");
		initViews();
		initDates();
		initEvents();

	}

	private void initViews() {
		// TODO Auto-generated method stub
		eventName = (TextView) this.findViewById(R.id.watch_events_name);
		eventDate = (TextView) this.findViewById(R.id.watch_events_data);
		eventTime = (TextView) this.findViewById(R.id.watch_events_time);
		eventRemarks = (TextView) this.findViewById(R.id.watch_events_remarks);
		deleteLayout = this.findViewById(R.id.delete_layout);
	}

	private void initDates() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		ce = (CalendarEvents) intent.getSerializableExtra("value");
		eventName.setText(ce.getTitle());

		int year;
		int month;
		int day;
		year = ce.getYear();
		month = ce.getMonth();
		day = ce.getDay();
		if ((year == calendar.get(Calendar.YEAR))
				&& (month == calendar.get(Calendar.MONTH) + 1)
				&& day == calendar.get(Calendar.DATE)) {
			eventDate.setText("今天");
		} else {
			eventDate.setText(year + "年" + month + "月" + day + "日");
		}
		eventTime.setText(ce.getHour() + ":" + ce.getMinute());
		eventRemarks.setText(ce.getRemarks());
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		deleteLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteDialog = new DeleteDialog(ScheduleWatchActivity.this,
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								deleteLocalDataBase();
								deleteEventFromCalendar();
								deleteDialog.dismiss();
								sendBroadCast();
								ScheduleWatchActivity.this.finish();
							}
						});
				deleteDialog.show();
				deleteDialog.startAnimation();
			}
		});
	}

	private void sendBroadCast() {
		Intent mIntent = new Intent(Constants.BROADCAST_EVENTSRECORD);
		mIntent.putExtra("yaner", "发送广播，相当于在这里传送数据");
		// 发送广播
		this.sendBroadcast(mIntent);
	}

	private void deleteLocalDataBase() {
		DataSupport.deleteAll(CalendarEvents.class, "eventID=?",
				ce.getEventID() + "");
	}

	private void deleteEventFromCalendar() {
		CalendarCRUD.delete(ScheduleWatchActivity.this, ce.getEventID());
	}
}
