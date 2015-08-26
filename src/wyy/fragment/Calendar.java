package wyy.fragment;

import java.util.ArrayList;
import java.util.List;

import wyy.activity.NewScheduleActivity;
import wyy.activity.ScheduleWatchActivity;
import wyy.adapter.EventsListViewAdapter;
import wyy.bean.CalendarEvents;
import wyy.utils.CalendarCRUD;
import wyy.utils.Constants;
import wyy.widget.KCalendar;
import wyy.widget.KCalendar.OnCalendarClickListener;
import wyy.widget.KCalendar.OnCalendarDateChangedListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.imagemanager6.R;
/**
 * 日程表
 * */
public class Calendar extends BaseFragment implements OnClickListener {

	private View view;
	private View addNewSchedule;
	private TextView calendarMonth;
	private KCalendar calendar;
	private ViewStub vs;

	private ListView listview;

	private String date = null;// 设置默认选中的日期 格式为 “2014-04-05” 标准DATE格式

	private List<CalendarEvents> list_ce;
	private EventsListViewAdapter mAdapter;

	private Handler Handler = new Handler();
	private Runnable mUpdateResults = new Runnable() {
		public void run() {
			updateUI();
		}
	};

	// class CreatRunnable implements Runnable {
	// LayoutInflater inflater;
	//
	// public CreatRunnable(LayoutInflater inflater) {
	// super();
	// this.inflater = inflater;
	// }
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// initUI(inflater);
	// }
	//
	// }


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("wyy", "日历1");
		view = inflater.inflate(R.layout.fragment_calendar, null, false);
		Log.i("wyy", "日历2");
		registerBoradcastReceiver();
		initViews();
		initDates();
		initEvents();
//		CreatRunnable mCreatRunnable = new CreatRunnable(inflater);
//		Handler.post(mCreatRunnable);
		return view;
	}

//	protected void initUI(LayoutInflater inflater) {
//		// TODO Auto-generated method stub
//		view = inflater.inflate(R.layout.fragment_calendar, null, false);
//		Log.i("wyy", "日历2");
//		registerBoradcastReceiver();
//		initViews();
//		initDates();
//		initEvents();
//	}

	protected void updateUI() {
		// TODO Auto-generated method stub
		setMarks();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		vs = (ViewStub) view.findViewById(R.id.popupwindow_calendar);
		View vi = vs.inflate();
		Log.i("wyy", "日历3");
		calendar = (KCalendar) vi.findViewById(R.id.kcalendar);
		Log.i("wyy", "日历4");
		calendarMonth = (TextView) view
				.findViewById(R.id.popupwindow_calendar_month);
		addNewSchedule = view.findViewById(R.id.add_newschedule);

		listview = (ListView) view.findViewById(R.id.event_listview);

	}

	private void initDates() {
		// TODO Auto-generated method stub
		queryEventsAtLocal();
		calendarMonth.setText(calendar.getCalendarYear() + "年"
				+ calendar.getCalendarMonth() + "月");
		if (null != date) {
			int years = Integer.parseInt(date.substring(0, date.indexOf("-")));
			int month = Integer.parseInt(date.substring(date.indexOf("-") + 1,
					date.lastIndexOf("-")));
			calendarMonth.setText(years + "年" + month + "月");
			calendar.showCalendar(years, month);
			calendar.setCalendarDayBgColor(date,
					R.drawable.calendar_date_focused);
			setEventsListView(date);
		}
		// List<String> list = new ArrayList<String>(); // 设置标记列表
		// list.add("2015-05-01");
		// list.add("2015-05-02");
		// calendar.addMarks(list, 0);
		// 监听所选中的日期
		calendar.setOnCalendarClickListener(new OnCalendarClickListener() {
			public void onCalendarClick(int row, int col, String dateFormat) {
				// int month = Integer.parseInt(dateFormat.substring(
				// dateFormat.indexOf("-") + 1,
				// dateFormat.lastIndexOf("-")));
				//
				// if (calendar.getCalendarMonth() - month == 1// 跨年跳转
				// || calendar.getCalendarMonth() - month == -11) {
				// calendar.lastMonth();
				//
				// } else if (month - calendar.getCalendarMonth() == 1 // 跨年跳转
				// || month - calendar.getCalendarMonth() == -11) {
				// calendar.nextMonth();
				//
				// } else {
				calendar.removeAllBgColor();
				calendar.setCalendarDayBgColor(dateFormat,
						R.drawable.calendar_date_focused);
				// calendar.addMark(dateFormat, 0);
				date = dateFormat;// 最后返回给全局 date

				setEventsListView(date);

				// }
			}
		});

	}

	private void setEventsListView(String date) {
		// TODO Auto-generated method stub
		String year;
		String month;
		String day;
		String combineStr;
		List<CalendarEvents> temp = new ArrayList<CalendarEvents>();
		for (CalendarEvents calendarEvents : list_ce) {
			year = String.valueOf(calendarEvents.getYear());
			month = String.valueOf(calendarEvents.getMonth());
			day = String.valueOf(calendarEvents.getDay());
			if (month.length() == 1)
				month = '0' + month;
			if (day.length() == 1)
				day = '0' + day;
			combineStr = year + "-" + month + "-" + day;
			Log.i("localdatabase", combineStr + ":::" + date);
			if (combineStr.equals(date)) {
				temp.add(calendarEvents);
			}
		}
		Log.i("localdatabase", "tempsize:" + temp.size());
		// if (mAdapter == null) {
		mAdapter = new EventsListViewAdapter(temp, getActivity());
		listview.setAdapter(mAdapter);
		// } else {
		mAdapter.update(temp);
		// }
	}

	private void queryEventsAtLocal() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				list_ce = CalendarCRUD.queryEventsAtLocal();
				// Log.i("localdatabase", "list_ce size:" + list_ce.size());
				// String today = calendar.getCalendarYear() + "-"
				// + calendar.getCalendarMonth() + "-"
				// + calendar.getCalendarday();
				// setEventsListView(today);
				Handler.post(mUpdateResults);
			}
		}).start();
	}

	protected void setMarks() {
		// TODO Auto-generated method stub
		calendar.clearAll();
		List<String> list = new ArrayList<String>(); // 设置标记列表
		String year;
		String month;
		String day;
		for (CalendarEvents ce : list_ce) {
			year = String.valueOf(ce.getYear());
			month = String.valueOf(ce.getMonth());
			day = String.valueOf(ce.getDay());
			if (month.length() == 1)
				month = '0' + month;
			if (day.length() == 1)
				day = '0' + day;
			list.add(year + "-" + month + "-" + day);
		}
		// list.add("2015-05-01");
		// list.add("2015-05-02");
		calendar.addMarks(list, 0);
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		// 监听当前月份
		calendar.setOnCalendarDateChangedListener(new OnCalendarDateChangedListener() {
			public void onCalendarDateChanged(int year, int month) {
				calendarMonth.setText(year + "年" + month + "月");
			}
		});
		// 上月监听按钮
		RelativeLayout popupwindow_calendar_last_month = (RelativeLayout) view
				.findViewById(R.id.popupwindow_calendar_last_month);
		popupwindow_calendar_last_month
				.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						calendar.lastMonth();
					}
				});
		// 下月监听按钮
		RelativeLayout popupwindow_calendar_next_month = (RelativeLayout) view
				.findViewById(R.id.popupwindow_calendar_next_month);
		popupwindow_calendar_next_month
				.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						calendar.nextMonth();
					}
				});
		addNewSchedule.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ImageView iv = (ImageView) addNewSchedule
						.findViewById(R.id.add_image);
				Animation animation = AnimationUtils.loadAnimation(
						getActivity(), R.anim.scale360);
				iv.startAnimation(animation);
				animation.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(Calendar.this.getActivity(),
								NewScheduleActivity.class);
						Calendar.this.getActivity().startActivity(intent);
					}
				});

			}
		});

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				CalendarEvents ce = (CalendarEvents) arg0
						.getItemAtPosition(arg2);

				Intent intent = new Intent(getActivity(),
						ScheduleWatchActivity.class);
				intent.putExtra("value", ce);
				getActivity().startActivity(intent);
			}
		});

	}

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (action.equals(Constants.BROADCAST_EVENTSRECORD)) {
				// Toast.makeText(ListActivity.this, "处理action名字相对应的广播", 200)
				// .show();
				Log.i("localdatabase", "收到");
				initDates();
			}
		}
	};

	public void registerBoradcastReceiver() {
		IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction(Constants.BROADCAST_EVENTSRECORD);
		// 注册广播
		getActivity().registerReceiver(mBroadcastReceiver, myIntentFilter);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mBroadcastReceiver != null) {
			getActivity().unregisterReceiver(mBroadcastReceiver);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

}
