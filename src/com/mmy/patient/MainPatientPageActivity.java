package com.mmy.patient;

import passenger.activity.DiseaseActivity;
import passenger.activity.FindDoctorActivity;
import passenger.activity.PassengerMainActivity;
import wyy.activity.BaseActivity;
import wyy.activity.LibTempActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.imagemanager6.R;
import com.xd.Vos.Doctor;

public class MainPatientPageActivity extends BaseActivity {
	Intent intent;
	private long mExitTime;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mmy_main_patient_page);
	}

	//主治医生
	public void clickZhuZhi(View v){
		intent = new Intent(this,
				MainDoctorDetailActivity.class);
		Doctor doctor=null;
		intent.putExtra("intent_inter", doctor);
		startActivity(intent);
		//Toast.makeText(getApplicationContext(), "主治医生", 1).show();
	}
	//用药进程
	public void clickYongYao(View v){
		intent = new Intent(this,
				DiseaseActivity.class);
		startActivity(intent);
		//Toast.makeText(getApplicationContext(), "用药进程", 1).show();
	}
	//临床路径
	public void clickLinChuang(View v){
		intent = new Intent(this,
				LibTempActivity.class);
		intent.putExtra("value", R.array.linchuanglujing);
		startActivity(intent);
		//Toast.makeText(getApplicationContext(), "临床路径", 1).show();	
	}
	//医生情况
	public void clickYiSheng(View v){
		intent = new Intent(this,
				FindDoctorActivity.class);
		startActivity(intent);
		//Toast.makeText(getApplicationContext(), "医生情况", 1).show();
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Object mHelperUtils;
				Toast.makeText(this, "再按一次退出程序", 2000).show();
				mExitTime = System.currentTimeMillis();

			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
