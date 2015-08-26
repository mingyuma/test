package wyy.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import wyy.activity.MedicalRecordListActivity;
import wyy.activity.NewMedicalRecordActivity;
import wyy.adapter.PatientSortAdapter;
import wyy.bean.People;
import wyy.utils.CharacterParser;
import wyy.utils.PinyinComparator;
import wyy.widget.ClearEditText;
import wyy.widget.SideBar;
import wyy.widget.SideBar.OnTouchingLetterChangedListener;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.imagemanager6.R;
import com.xd.Vos.Doctor;
import com.xd.Vos.Patient;
import com.xd.dialog.DialogFactory;
import com.xd.util.ConnectUtil;
import com.xd.util.MyConstants;
import com.xd.util.PostParameter;
import com.xd.util.SharePreferenceUtil;
import com.xd.util.ToastCustom;
/**
 * 病例列表
 * 
 * */
public class PatientRecord extends BaseFragment {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private PatientSortAdapter adapter;
	private ClearEditText mClearEditText;

	public static int refresh=0;
	private View noDataLayout;
	private View newRecordView;
	private Dialog mDialog = null;
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<Patient> SourceDateList = new ArrayList<Patient>();
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	private View view;
	private Activity activity;

	private List<Patient> peoples;
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
					JSONArray patientArray;
					try {
						patientArray = result.getJSONArray("patientList");
						peoples = new ArrayList<Patient>();
						//Log.i("mmm", patientArray.length()+"---"+patientArray.toString());
						for (int i=0;i<patientArray.length();i++){
							Patient patient = new Patient();
							JSONObject patientJson = patientArray.getJSONObject(i);
							
							patient.setPatientId(patientJson.getString("username_patient"));
							patient.setName(patientJson.getString("name"));
							patient.setSex(patientJson.getString("sex"));
//							patient.setBirthday(patientJson.getString("birthday"));
//							patient.setBirthday(patientJson.getString("birthday"));
							patient.setMobile(patientJson.getString("tele"));
							patient.setAge(patientJson.getString("age"));
							patient.setImageCount(patientJson.getString("imageCount"));
							patient.setMedicalCount(patientJson.getString("medicalCount"));
							//patient.setPhotoUrl(patientJson.getString("head_photo"));
							peoples.add(patient);
						}
						if (peoples != null) {
							SourceDateList = peoples;
							filledData(SourceDateList);
							Collections.sort(SourceDateList,pinyinComparator);
							adapter = new PatientSortAdapter(activity, SourceDateList);
							sortListView.setAdapter(adapter);
						}
						
						if (SourceDateList.isEmpty()) {
							noDataLayout.setVisibility(View.VISIBLE);
						} else {
							noDataLayout.setVisibility(View.GONE);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

				}
				else if(reCode.equalsIgnoreCase("fail")){
					if(!message.equals("")){
						ToastCustom.makeToast(activity, message);
					}
					else
						ToastCustom.makeToast(activity, "获取病人列表失败");
				}
				else{
					
					ToastCustom.makeToast(activity, "获取病人列表失败");
				}
				}
				break;
			case 0:{
				ToastCustom.makeToast(activity, "获取病人列表失败");
				break;
			}
			case -1:
				ToastCustom.makeToast(activity, "未能连接到服务器");
				break;
			default:
				break;
			}

			super.handleMessage(msg);
		}

	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_medicalrecord, null, false);
		activity = this.getActivity();
		//registerBoradcastReceiver();
		initViews();
		initDates();
		initEvents();
		refresh=0;
		return view;

	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		if(refresh!=0){
			peoples = new ArrayList<Patient>();
			getPatientList();
			refresh=0;
		}
		super.onResume();
	}

	private void initEvents() {
		// TODO Auto-generated method stub

		adapter = new PatientSortAdapter(activity, SourceDateList);
		sortListView.setAdapter(adapter);
	}

	public void getPatientList(){
		showRequestDialog();
		
    	new Thread(){

			@Override
			public void run() {
				Message message=new Message();
				if(ConnectUtil.isNetworkAvailable(activity)){
					SharePreferenceUtil sp = new SharePreferenceUtil(activity, MyConstants.SAVE_USER);
					PostParameter[] postParams;
					postParams = new PostParameter[1];
					postParams[0] = new PostParameter("docUsername",sp.getAccount());

					
					String jsonStr = ConnectUtil.httpRequest(ConnectUtil.DoctorGetPatientList, postParams, ConnectUtil.POST);
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
	private void initDates() {
		// TODO Auto-generated method stub
		peoples = new ArrayList<Patient>();
		getPatientList();
		//peoples = DataSupport.findAll(People.class, true);
		if (peoples != null) {
			SourceDateList = peoples;
			filledData(SourceDateList);
		}
		if (SourceDateList.isEmpty()) {
			noDataLayout.setVisibility(View.VISIBLE);
		} else {
			noDataLayout.setVisibility(View.GONE);
		}
		// Log.i("wyy", "firstchar::" + SourceDateList.get(0).getSortLetters());
		// ////////////////////
		// ////////////////////
		// ////////////////////
		// ////////////////////
		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList,pinyinComparator);
	}

	private void initViews() {
		// TODO Auto-generated method stub
		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();
		sideBar = (SideBar) view.findViewById(R.id.sidrbar);
		dialog = (TextView) view.findViewById(R.id.dialog);
		noDataLayout = view.findViewById(R.id.no_data_layout);
		newRecordView = view.findViewById(R.id.new_record);
		mClearEditText = (ClearEditText) view.findViewById(R.id.filter_edit);

		newRecordView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity,
						NewMedicalRecordActivity.class);
				activity.startActivity(intent);
			}
		});
		noDataLayout.setVisibility(View.GONE);
		sideBar.setTextView(dialog);
		// 设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}

			}
		});

		sortListView = (ListView) view.findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
				Intent intent = new Intent(activity,
						MedicalRecordListActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("detail", SourceDateList.get(position));
				intent.putExtras(bundle);
				// intent.putExtra(Extra.IMAGES, Constants.IMAGES);
				startActivity(intent);
			}
		});
		mClearEditText.addTextChangedListener(new TextWatcher() {

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
		// ////////////////////
		// ////////////////////
		// ////////////////////
		// ////////////////////
		// SourceDateList =
		// filledData(getResources().getStringArray(R.array.date));

		// mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		//
		// // 根据输入框输入值的改变来过滤搜索
		// mClearEditText.addTextChangedListener(new TextWatcher() {
		//
		// @Override
		// public void onTextChanged(CharSequence s, int start, int before,
		// int count) {
		// // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
		// filterData(s.toString());
		// }
		//
		// @Override
		// public void beforeTextChanged(CharSequence s, int start, int count,
		// int after) {
		//
		// }
		//
		// @Override
		// public void afterTextChanged(Editable s) {
		// }
		// });
	}

	// private List<People> filledData(String[] date) {
	// List<People> mSortList = new ArrayList<People>();
	//
	// for (int i = 0; i < date.length; i++) {
	// People sortModel = new People();
	// sortModel.setName(date[i]);
	// // 汉字转换成拼音
	// String pinyin = characterParser.getSelling(date[i]);
	// String sortString = pinyin.substring(0, 1).toUpperCase();
	//
	// // 正则表达式，判断首字母是否是英文字母
	// if (sortString.matches("[A-Z]")) {
	// sortModel.setSortLetters(sortString.toUpperCase());
	// } else {
	// sortModel.setSortLetters("#");
	// }
	//
	// mSortList.add(sortModel);
	// }
	// return mSortList;
	//
	// }

	private void filledData(List<Patient> mSortList) {

		for (int i = 0; i < mSortList.size(); i++) {
			// People sortModel = mSortList.get(i);
			// sortModel.setName(date[i]);
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(mSortList.get(i)
					.getName());
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				mSortList.get(i).setSortLetters(sortString.toUpperCase());
			} else {
				mSortList.get(i).setSortLetters("#");
			}

		}

	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<Patient> filterDateList = new ArrayList<Patient>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (Patient sortModel : SourceDateList) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

//	public void registerBoradcastReceiver() {
//		IntentFilter myIntentFilter = new IntentFilter();
//		myIntentFilter.addAction(Constants.BROADCAST_PATIENTRECORD);
//		myIntentFilter.addAction(Constants.BROADCAST_MEDICALRECORD);
//		// 注册广播
//		this.getActivity().registerReceiver(mBroadcastReceiver, myIntentFilter);
//	}

//	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
//
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			// TODO Auto-generated method stub
//			String action = intent.getAction();
//			if (action.equals(Constants.BROADCAST_PATIENTRECORD)) {
//				// Toast.makeText(ListActivity.this, "处理action名字相对应的广播", 200)
//				// .show();
//				initDates();
//				adapter.updateListView(SourceDateList);
//			} else if (action.equals(Constants.BROADCAST_MEDICALRECORD)) {
//				initDates();
//				adapter.updateListView(SourceDateList);
//			}
//		}
//	};

	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		if (mBroadcastReceiver != null) {
//			activity.unregisterReceiver(mBroadcastReceiver);
//		}

	}
	private void showRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
		mDialog = DialogFactory.creatRequestDialog(activity, "请稍等...");
		mDialog.show();
	}

	private void dismissRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
	}
}
