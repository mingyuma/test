package wyy.activity;



import org.json.JSONException;
import org.json.JSONObject;

import passenger.activity.PassengerMainActivity;
import wyy.application.MyApplication;
import wyy.widget.dd.CircularProgressButton;
import android.app.Dialog;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imagemanager6.R;
import com.mmy.patient.MainPatientPageActivity;

import com.xd.Vos.Doctor;
import com.xd.Vos.Patient;
import com.xd.dialog.DialogFactory;
import com.xd.util.ConnectUtil;

import com.xd.util.MyConstants;
import com.xd.util.Encode;
import com.xd.util.NetWorkUtil;
import com.xd.util.PostParameter;
import com.xd.util.SharePreferenceUtil;

import com.xd.util.ToastCustom;




public class LoginActivity extends BaseActivity implements OnClickListener {

	private CircularProgressButton cpb; // 登录
	private View animLayout;
	private ImageView animImage;

	private Button identity_1;
	private Button identity_2;
	private Button identity_3;
	private Button identity_4;
	private TextView passenger_login,register;

	private String URL="";
	private int identity = 0;
	//private EditText loginName;
	private Dialog mDialog = null;
	private EditText accountTv, passwordTv;
	private int indexClazz = 0;
	private NetWorkUtil netWorkUtil;
	private MyApplication application;
	String accounts ;
	String password ;
	SharePreferenceUtil sp;
	Patient patient;
	Doctor doctor;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			// 要做的事情
			dismissRequestDialog();
			String message="",reCode="";
			switch (msg.what) {
			case 1:{
				//String mesg=(String)msg.obj;
				JSONObject result = (JSONObject)msg.obj;
				try {
					reCode = result.getString("reCode");
					message = result.getString("message");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(reCode.equalsIgnoreCase("SUCCESS")){
					ToastCustom.makeToast(getApplicationContext(), "欢迎您，"+accountTv.getText().toString());
					sp.setAccount(accounts);
					sp.setPassword(password);
					if(indexClazz==0){//病人登录
						try {
							JSONObject patientJson = result.getJSONObject("patient");
							patient = new Patient();
							patient.setName(patientJson.getString("name"));
							patient.setSex(patientJson.getString("sex"));
							patient.setBirthday(patientJson.getString("birthday"));
							patient.setMobile(patientJson.getString("telephone"));
							patient.setPhotoUrl(patientJson.getString("head_photo"));
//							patient.setDisease(patientJson.getString("disease"));
							initPatientInfo();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else{//医生登录
						try {
							JSONObject patientJson = result.getJSONObject("patient");
							doctor = new Doctor();
							doctor.setType(patientJson.getString("type"));
							doctor.setName(patientJson.getString("name"));
							doctor.setSex(patientJson.getString("sex"));
							doctor.setBirthday(patientJson.getString("birthday"));
							doctor.setMobile(patientJson.getString("tele"));
							doctor.setHospital(patientJson.getString("hospitalName"));
							doctor.setIntro(patientJson.getString("brief"));
							doctor.setPhotoURL(patientJson.getString("head_photo"));
							doctor.setGoodat(patientJson.getString("good_at"));
							doctor.setDepartmentId(patientJson.getString("departmentID"));
							doctor.setDepartment(patientJson.getString("departmentName"));
							doctor.setHospitalId(patientJson.getString("hospitalID"));
							initDoctorInfo();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//
					gotoMainActivity();
				}
				else if(reCode.equalsIgnoreCase("fail")){
					if(!message.equals("")){
						ToastCustom.makeToast(getApplicationContext(), message);
					}
					else
						ToastCustom.makeToast(getApplicationContext(), "用户名或密码错误，请重新输入");
				}
				else{
					
					ToastCustom.makeToast(getApplicationContext(), "未能登录成功，请重新登录");
				}
				}
				break;
			case 0:{
				ToastCustom.makeToast(getApplicationContext(), "未能登录成功，请重新登录");
				break;
			}
			case -1:
				ToastCustom.makeToast(getApplicationContext(), "未能连接到服务器");
				break;
			default:
				break;
			}

			super.handleMessage(msg);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		this.setTitle("登录");
		initViews();
		initDatas();
		initEvents();
		initAnims();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		cpb = (CircularProgressButton) this.findViewById(R.id.btnWithText);
		animLayout = this.findViewById(R.id.anim_layout);
		animImage = (ImageView) this.findViewById(R.id.anim_image);
		//loginName = (EditText) this.findViewById(R.id.loginname);

		identity_1 = (Button) this.findViewById(R.id.identity_1);
		identity_2 = (Button) this.findViewById(R.id.identity_2);
		identity_3 = (Button) this.findViewById(R.id.identity_3);
		identity_4 = (Button) this.findViewById(R.id.identity_4);

		passenger_login = (TextView) this.findViewById(R.id.passenger_login);
		register= (TextView) this.findViewById(R.id.register);
		accountTv = (EditText) this.findViewById(R.id.loginname);
		passwordTv = (EditText) this.findViewById(R.id.loginpassword);
	}

	private void initDatas() {
		// TODO Auto-generated method stub
		netWorkUtil = new NetWorkUtil(LoginActivity.this);
		sp= new SharePreferenceUtil(getApplicationContext(), MyConstants.SAVE_USER);
	}

	void initPatientInfo(){
		sp.setPhotoUrl(patient.getPhotoUrl());
		sp.setUserName(patient.getName());
		sp.setBirth(patient.getBirthday());
		sp.setMoblie(patient.getMobile());
		sp.setPatientDisease(patient.getDisease());
		sp.setSex(patient.getSex());
	}
	void initDoctorInfo(){
		sp.setUserName(doctor.getName());
		sp.setBirth(doctor.getBirthday());
		sp.setMoblie(doctor.getMobile());
		sp.setSex(doctor.getSex());
		sp.setHospital(doctor.getHospital());
		sp.setDoctorIntro(doctor.getIntro());
		sp.setPhotoUrl(doctor.getPhotoURL());
		sp.setHospitalID(doctor.getHospitalId());
		sp.setDepartment(doctor.getDepartment());
		sp.setDepartmentID(doctor.getDepartmentId());
		sp.setType(doctor.getType());
		sp.setGoodAt(doctor.getGoodat());
	}
	private void initEvents() {
		// TODO Auto-generated method stub
		identity_1.setOnClickListener(this);
		identity_2.setOnClickListener(this);
		identity_3.setOnClickListener(this);
		identity_4.setOnClickListener(this);

		
		cpb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				sp.setAccount("liuhaoxian");
//				gotoMainActivity();
				submit();
			}
		});
		passenger_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,
						PassengerMainActivity.class);
				startActivity(intent);
			}
		});
		register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,
						RegisterActivity.class);
				startActivity(intent);
			}
		});
	}

	public void gotoMainActivity(){
		Intent intent;
		switch (indexClazz) {
		case 0:
			// 患者登录
			intent = new Intent(LoginActivity.this, MainPatientPageActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();
			break;
		case 1:
			// 初级医师登录
			intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();
			break;
		case 2:
			// 主任医师登录
			intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();
			break;
		case 3:
			// 管理医师登录
			intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();
			break;
		default:
			break;
		}
	}
	private void submit() {
		accounts = accountTv.getText().toString().trim();
		password = passwordTv.getText().toString().trim();
		if (!netWorkUtil.isNetworkAvailable()) {
			DialogFactory.ToastDialog(LoginActivity.this, "温馨提示", "当前网络不可用");
		}
		else{
			if (accounts.length() == 0 || password.length() == 0) {
				DialogFactory.ToastDialog(LoginActivity.this, "温馨提示", "请输入帐号和密码");
			}
			else{
				showRequestDialog();
				
		    	new Thread(){

					@Override
					public void run() {
						Message message=new Message();
						if(ConnectUtil.isNetworkAvailable(getApplicationContext())){
							PostParameter[] postParams;
							if(indexClazz==0){
								URL=ConnectUtil.PATIENT_LOGIN;
								postParams = new PostParameter[2];
								postParams[0] = new PostParameter("name", accounts);
								postParams[1] = new PostParameter("password",password );//Encode.getEncode("MD5", password)
							}
							else{
								URL=ConnectUtil.DOCTOR_LOGIN;
								postParams = new PostParameter[3];
								postParams[0] = new PostParameter("name", accounts);
								postParams[1] = new PostParameter("password",password );//Encode.getEncode("MD5", password)
								postParams[2] = new PostParameter("type",String.valueOf(indexClazz-1) );
							}
							String jsonStr = ConnectUtil.httpRequest(URL, postParams, ConnectUtil.POST);
							if(jsonStr==null||jsonStr.equals("")){
								message.what=0;//失败
								message.obj="fail";
								handler.sendMessage(message);
							}
							else{
								
								try {
									JSONObject result = new JSONObject(jsonStr);
									//String msg= result.getString("reCode");
									message.what=1;
									message.obj=result;
									handler.sendMessage(message);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						else{
							message.what=-1;
							message.obj="网络不可用";
							handler.sendMessage(message);
						}
					}
				}.start();					
			}
		}
		 
		
				
	}
	private void initAnims() {
		// TODO Auto-generated method stub
		Animation animlayou = AnimationUtils.loadAnimation(this,
				R.anim.loginbox);
		Animation animimage = AnimationUtils.loadAnimation(this,
				R.anim.loginimage);
		animLayout.startAnimation(animlayou);
		animImage.startAnimation(animimage);
	}

	Runnable r = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			cpb.setProgress(100);
			
		}
	};
	Runnable r1 = new Runnable() {

		@Override
		public void run() {
			
			Message msg = new Message();
			msg.what = 1;
			handler.sendMessage(msg);
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		resetButton();
		switch (v.getId()) {

		// 患者
		case R.id.identity_1:
			identity_1.setBackgroundColor(getResources().getColor(R.color.red));
			indexClazz = 0;
			break;
		// 初级
		case R.id.identity_2:
			identity_2.setBackgroundColor(getResources().getColor(R.color.red));
			indexClazz = 1;
			break;
		// 主任
		case R.id.identity_3:
			identity_3.setBackgroundColor(getResources().getColor(R.color.red));
			indexClazz = 2;
			break;
		// 管理
		case R.id.identity_4:
			identity_4.setBackgroundColor(getResources().getColor(R.color.red));
			indexClazz = 3;
			break;
		default:
			break;
		}
	}

	private void resetButton() {
		// TODO Auto-generated method stub
		identity_1.setBackgroundColor(getResources()
				.getColor(R.color.qianhuise));
		identity_2.setBackgroundColor(getResources()
				.getColor(R.color.qianhuise));
		identity_3.setBackgroundColor(getResources()
				.getColor(R.color.qianhuise));
		identity_4.setBackgroundColor(getResources()
				.getColor(R.color.qianhuise));
	}
	private void showRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
		mDialog = DialogFactory.creatRequestDialog(this, "正在验证账号...");
		mDialog.show();
	}

	private void dismissRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
	}
	
}
