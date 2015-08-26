package passenger.activity;

import wyy.activity.LibTempActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.imagemanager6.R;

public class PassengerMainActivity extends Activity implements OnClickListener {

	private View layout1;
	private View layout2;
	private View layout3;
	private View layout4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_passengermain);
		initViews();
		initDates();
		initEvents();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		layout1 = this.findViewById(R.id.passenger_1);
		layout2 = this.findViewById(R.id.passenger_2);
		layout3 = this.findViewById(R.id.passenger_3);
		layout4 = this.findViewById(R.id.passenger_4);
	}

	private void initDates() {
		// TODO Auto-generated method stub

	}

	private void initEvents() {
		// TODO Auto-generated method stub
		layout1.setOnClickListener(this);
		layout2.setOnClickListener(this);
		layout3.setOnClickListener(this);
		layout4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;

		switch (v.getId()) {
		case R.id.passenger_1:
			intent = new Intent(PassengerMainActivity.this,
					DiseaseActivity.class);
			startActivity(intent);
			break;
		case R.id.passenger_2:
			intent = new Intent(PassengerMainActivity.this,
					LibTempActivity.class);
			intent.putExtra("value", R.array.linchuanglujing);
			startActivity(intent);
			break;
		case R.id.passenger_3:
			intent = new Intent(PassengerMainActivity.this,
					FindDoctorActivity.class);
			startActivity(intent);
			break;
		case R.id.passenger_4:

			break;
		default:
			break;
		}
	}
}
