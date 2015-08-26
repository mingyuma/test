package com.mmy.patient;

import wyy.activity.BaseActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.imagemanager6.R;
import com.xd.Vos.Doctor;

public class MainDoctorDetailActivity extends BaseActivity {
	private TextView name;
	private TextView grade;
	private TextView department;
	private TextView hospital;
	private TextView intro;
	private TextView level;
	String sname,sgrade,slevel,sintro,sdepartment,shospital;
	Intent intent= null;
	Doctor doctor=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mmy_main_doctor_detail);
		intent = getIntent();
		doctor = (Doctor)intent.getExtras().get("intent_inter");
		if(doctor==null){
			initData();
		}
		else{
			sname=doctor.getName();
			sgrade=doctor.getGrade();
			slevel=doctor.getLevel();
			sintro=doctor.getIntro();
			sdepartment=doctor.getDepartment();
			shospital=doctor.getHospital();
		}
		initView();
	}
	public void initData(){
		sname="����";
		sgrade="����ҽʦ";
		slevel="90.5";
		sintro="���Σ��ط��Թǲ����ؽ������Թǲ���������";
		sdepartment="�ǿ�";
		shospital="����ҽԺ";
	}
	public void initView(){
		name =(TextView) findViewById(R.id.doctor_detail_name);
		grade =(TextView) findViewById(R.id.doctor_detail_level_grade);
		department =(TextView) findViewById(R.id.doctor_detail_department_grade);
		hospital =(TextView) findViewById(R.id.doctor_detail_hospital_grade);
		intro =(TextView) findViewById(R.id.doctor_detail_intro);
		level =(TextView) findViewById(R.id.doctor_detail_level_grade1);
		
		name.setText(sname);
		grade.setText(sgrade);
		department.setText(sdepartment);
		hospital.setText(shospital);
		intro.setText(sintro);
		level.setText(slevel);
	}
}
