package wyy.activity;

import java.util.Calendar;

import wyy.bean.CalendarEvents;
import wyy.utils.CalendarCRUD;
import wyy.utils.Constants;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.imagemanager6.R;

public class NewScheduleActivity extends Activity {
	private View date_layout;
	private View time_layout;
	private View repead_layout;
	private EditText et_remarks;
	private EditText et_events;
	private TextView tv_date;
	private TextView tv_time;
	private TextView tv_remind;

	private Button save;

	private RadioGroup rg_eventGroup;

	private ImageView drop_arrow;

	private Calendar calendar = Calendar.getInstance();

	private int currentYear;
	private int currentMonth;
	private int currentDay;
	private int currentHour;
	private int currentMinute;
	private int remindTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newschedule);
		this.setTitle("新建日程");
		initViews();
		initDates();
		initEvents();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		date_layout = this.findViewById(R.id.schedule_date_layout);
		time_layout = this.findViewById(R.id.schedule_time_layout);
		repead_layout = this.findViewById(R.id.schedule_repead_layout);
		et_remarks = (EditText) this.findViewById(R.id.schedule_remarks);
		et_events = (EditText) this.findViewById(R.id.schedule_event);

		tv_date = (TextView) this.findViewById(R.id.newschedule_date);
		tv_time = (TextView) this.findViewById(R.id.newschedule_time);
		tv_remind = (TextView) this.findViewById(R.id.newschedule_remaid);

		save = (Button) this.findViewById(R.id.schedule_save);

		drop_arrow = (ImageView) this.findViewById(R.id.drop_arrow);

		rg_eventGroup = (RadioGroup) this.findViewById(R.id.event_group);
	}

	private void initDates() {
		// TODO Auto-generated method stub
		// date.ge
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		currentYear = year;
		currentMonth = month;
		currentDay = day;
		tv_date.setText(year + "年" + month + "月" + day + "日");
		tv_time.setText("请设置时间");
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		date_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dateDialog();
			}
		});
		time_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				timeDialog();
			}
		});
		repead_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showRepeadChoice();
			}
		});

		rg_eventGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				int radioButtonId = group.getCheckedRadioButtonId();
				// 根据ID获取RadioButton的实例
				RadioButton rb = (RadioButton) NewScheduleActivity.this
						.findViewById(radioButtonId);
				// 更新文本内容，以符合选中项
				et_events.setText("【" + rb.getText() + "】");
				et_events.setSelection(et_events.length());
			}
		});

		drop_arrow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (rg_eventGroup.getVisibility() == View.GONE) {
					rg_eventGroup.setVisibility(View.VISIBLE);
					Animation animation = AnimationUtils.loadAnimation(
							NewScheduleActivity.this,
							R.anim.drop_arrow_rotatedown);
					animation.setFillAfter(true);
					v.startAnimation(animation);
				} else {
					rg_eventGroup.setVisibility(View.GONE);
					Animation animation = AnimationUtils.loadAnimation(
							NewScheduleActivity.this,
							R.anim.drop_arrow_rotateup);
					animation.setFillAfter(true);
					v.startAnimation(animation);
				}
				// v.setAnimation(animation);

			}
		});

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 第二个参数待修改
				long startm;
				long endm;
				Log.i("wyy", "currentYear" + currentYear + "currentMonth"
						+ currentMonth + "currentDay" + currentDay
						+ "currentHour" + currentHour + "currentMinute"
						+ currentMinute);
				calendar.set(currentYear, currentMonth - 1, currentDay,
						currentHour, currentMinute);
				startm = calendar.getTimeInMillis();
				calendar.set(currentYear, currentMonth - 1, currentDay,
						currentHour + 1, currentMinute);
				endm = calendar.getTimeInMillis();

				long eventid = addEventToCalendar(startm, endm);
				addEventToLocalDateBase(eventid);
				sendBroadCast();
				finish();
			}

		});
	}

	private long addEventToCalendar(long startm, long endm) {
		long eventId = CalendarCRUD.addEventToCalendar(
				NewScheduleActivity.this, 1, startm, endm, et_events.getText()
						.toString(), remindTime, et_remarks.getText()
						.toString());
		Toast.makeText(NewScheduleActivity.this, "添加完成", 1000).show();
		return eventId;
	}

	protected void addEventToLocalDateBase(long eventid) {
		// TODO Auto-generated method stub
		CalendarEvents ce = new CalendarEvents();
		ce.setEventID(eventid);

		ce.setDay(currentDay);

		ce.setHour(currentHour);

		ce.setMinute(currentMinute);

		ce.setMonth(currentMonth);
		ce.setRemarks(et_remarks.getText().toString());
		ce.setRemindTime(tv_remind.getText().toString());
		ce.setTitle(et_events.getText().toString());

		ce.setYear(currentYear);
		if (ce.save()) {
			Log.i("localdatabase", "local保存成功");
		} else {
			Log.i("localdatabase", "local保存失败");
		}

	}

	protected void showRepeadChoice() {
		// TODO Auto-generated method stub
		final String[] names = { "无", "15分钟前", "30分钟前", "1小时前", "2小时前", "1天前",
				"2天前" };
		final String[] selecteName = { "" };
		new AlertDialog.Builder(NewScheduleActivity.this)
				.setTitle("提醒")
				.setSingleChoiceItems(names, 0,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								selecteName[0] = names[which];
								switch (which) {
								case 0:
									remindTime = 0;
									break;
								case 1:
									remindTime = 15;
									break;
								case 2:
									remindTime = 30;
									break;
								case 3:
									remindTime = 60;
									break;
								case 4:
									remindTime = 120;
									break;
								case 5:
									remindTime = 1440;
									break;
								case 6:
									remindTime = 2880;
									break;
								default:
									break;
								}
							}
						})
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Animation animation = AnimationUtils.loadAnimation(
								NewScheduleActivity.this, R.anim.text_showin);
						if (selecteName[0].equals("")) {
							tv_remind.setText("无");
						} else {
							tv_remind.setText(selecteName[0]);
						}
						tv_remind.startAnimation(animation);
					}
				}).setNegativeButton("取消", null).show();
	}

	private void dateDialog() {
		// TODO Auto-generated method stub
		new DatePickerDialog(NewScheduleActivity.this, new OnDateSetListener() {
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				currentYear = year;
				currentMonth = monthOfYear + 1;
				currentDay = dayOfMonth;
				Animation animation = AnimationUtils.loadAnimation(
						NewScheduleActivity.this, R.anim.text_showin);
				tv_date.setText(currentYear + "年" + currentMonth + "月"
						+ currentDay + "日");
				tv_date.startAnimation(animation);
			}
		}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH)).show();
	}

	protected void timeDialog() {
		// TODO Auto-generated method stub
		new TimePickerDialog(NewScheduleActivity.this, new OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				currentHour = hourOfDay;
				currentMinute = minute;
				Animation animation = AnimationUtils.loadAnimation(
						NewScheduleActivity.this, R.anim.text_showin);
				tv_time.setText(hourOfDay + ":" + minute);
				tv_time.startAnimation(animation);
			}
		}, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
				true).show();
	}

	private void sendBroadCast() {
		Intent mIntent = new Intent(Constants.BROADCAST_EVENTSRECORD);
		mIntent.putExtra("yaner", "发送广播，相当于在这里传送数据");
		// 发送广播
		this.sendBroadcast(mIntent);
	}
}
