package passenger.activity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import wyy.utils.CharacterParser;
import wyy.utils.PinyinComparator;
import wyy.widget.ClearEditText;
import wyy.widget.XListView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.imagemanager6.R;
import com.mmy.patient.MainDoctorDetailActivity;
import com.xd.Vos.Doctor;
import com.xd.dialog.DialogFactory;
import com.xd.util.ConnectUtil;
import com.xd.util.PostParameter;
import com.xd.util.ToastCustom;

public class SearchDoctorActivity extends Activity {
	private static final int HOSPITAL_RESULT_CODE = 2;
	private ListView listview;
	private List<Doctor> doctors= new ArrayList<Doctor>();
	private ClearEditText clearEditText;
	private CharacterParser characterParser;
	private DoctorAdapter adapter;
	private Dialog mDialog = null;
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			dismissRequestDialog();
			String reCode="";
			switch (msg.what) {
			case 1:{
				JSONObject result = (JSONObject)msg.obj;
				try {
					reCode = result.getString("reCode");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(reCode.equalsIgnoreCase("SUCCESS")){
					Log.i("kmj","hahah--------------");
					try {
						JSONArray jsonDoctor = result.getJSONArray("doctorList");
						for(int i=0;i < jsonDoctor.length(); i ++){
							Doctor doctor = new Doctor();
							JSONObject json = (JSONObject)jsonDoctor.getJSONObject(i);
							doctor.setName(json.getString("name"));
							doctor.setType(json.getString("type"));
							doctor.setGoodat(json.getString("good_at"));
							doctor.setPhotoURL(json.getString("head_photo"));
							doctor.setDepartment(json.getString("departmentName"));
							doctor.setDepartmentId(json.getString("departmentID"));
							doctor.setHospital(json.getString("hospitalName"));
							doctor.setHospitalId(json.getString("hospitalID"));
							doctors.add(doctor);
							
						}
						Log.i("kmj","doctors size is -"+ doctors.size());
						for(Doctor  d : doctors){
							Log.i("kmj","doctor.name is-----"+d.getHospital());
						}
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					ToastCustom.makeToast(getApplicationContext(), "获取失败，请重新尝试");
				}
			}
				break;
			case 0:{
				ToastCustom.makeToast(getApplicationContext(), "获取失败，请重新尝试");
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

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_based_on_hospital);	
		initdata();
		initviews();
	}

	private void initdata() {
		// TODO Auto-generated method stub
//		showRequestDialog();
		
    	new Thread(){

			@Override
			public void run() {
				Message message=new Message();
				if(ConnectUtil.isNetworkAvailable(getApplicationContext())){
					PostParameter[] postParams = null;
					String jsonStr = ConnectUtil.httpRequest(ConnectUtil.Doctor_List, postParams, ConnectUtil.POST);
					if(jsonStr==null||jsonStr.equals("")){
						message.what=0;//fail
						message.obj="fail";
						handler.sendMessage(message);
					}
					else{
						
						try {
						    Log.i("kmj","json" + jsonStr);
							JSONObject result = new JSONObject(jsonStr);
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
//	
//	   for(int i=0;i<12;i++){
//		   Hospital hos = new Hospital();
//		   hos.setHospitalID(i+"");
//		   if(i%2==0)
//			   hos.setHospitalName("西京医院");
//		   else
//			   hos.setHospitalName("第四军医大");
//			   
//		   hospitals.add(hos);
//	   }	
	}

	private void initviews() {
		// TODO Auto-generated method stub
		characterParser = CharacterParser.getInstance();
		clearEditText = (ClearEditText) this.findViewById(R.id.hospital_filter_edit);
		
		if(getIntent().getIntExtra("flag", 0) == 1){
			clearEditText.setHint("请输入医院名称");
		}else{
			clearEditText.setHint("请输入医生名称");
		}
		listview = (ListView) this.findViewById(R.id.doc_listview);
		
//		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent();
//				intent.putExtra("hospital_name", (Doctor)listview.getItemAtPosition(arg2));
//				intent.putExtra("selected_doctors", (Serializable)doctors);
//				setResult(HOSPITAL_RESULT_CODE,intent);
//				finish();
				Intent intent = new Intent(SearchDoctorActivity.this, MainDoctorDetailActivity.class);
				intent.putExtra("doctor", (Doctor)listview.getItemAtPosition(arg2));
				startActivity(intent);
				
			}
			
		});
		clearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	private void filterData(String filterStr) {
		List<Doctor> filterDateList = new ArrayList<Doctor>();
		String name = null;

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = doctors;
		} else {
			filterDateList.clear();
			for (Doctor sortModel :doctors) {
				if(getIntent().getIntExtra("flag", 0) == 1){
					name = sortModel.getHospital();	
				}else{
					name = sortModel.getName();
				}
				
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}
		// 根据a-z进行排序
		// Collections.sort(filterDateList, pinyinComparator);
		adapter = new DoctorAdapter(SearchDoctorActivity.this, filterDateList);
		listview.setAdapter(adapter);
//		adapter.updateListView(filterDateList);
	
	}
	
	private void showRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
		mDialog = DialogFactory.creatRequestDialog(this, "正在获取医院...");
		mDialog.show();
	}

	private void dismissRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
	}

}
