package wyy.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mmy.tools.MyTools;
//
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import wyy.adapter.MyImageAdapter;
import wyy.bean.Condition;
import wyy.bean.Folder;
import wyy.bean.TimeImage;
import wyy.fragment.PatientRecord;
import wyy.utils.Constants;
import wyy.utils.RoundCorner;
import wyy.widget.DeleteDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.imagemanager6.R;
import com.xd.Vos.Doctor;
import com.xd.Vos.ImageVo;
import com.xd.Vos.Patient;
import com.xd.dialog.DialogFactory;
import com.xd.util.ConnectUtil;
import com.xd.util.MyConstants;
import com.xd.util.PostParameter;
import com.xd.util.SharePreferenceUtil;
import com.xd.util.ToastCustom;

public class MedicalRecordListActivity extends BaseActivity {

	private ListView listview;
	// protected ImageLoader imageLoader = ImageLoader.getInstance();
	private List<TimeImage> timeimages;
	public static final String TEXT_FORMAT = "<font size=\"20px\">%s</font>��<font size=\"10px\">%s</font>��";
//	private ImageAdapter imageadapter;
	public static final String BRODCASTFLAG = "wyy";

	private MyImageAdapter myImageAdapter;
	private View noDataLayout;

	private Patient people;
	private ImageView imageview;
	private TextView name;
	private TextView age;
	private TextView sexy;
	private TextView tel;
	private List<Condition> conditions;
	private ImageView addImageView;

	private int loc = 0;
	private DeleteDialog deleteDialog;

	private String id;

	
//	private List<String> imagePaths = new ArrayList<String>();
//	private List<String> imagePaths_name = new ArrayList<String>();
	private Dialog mDialog = null;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			// Ҫ��������
			dismissRequestDialog();
			String message="",reCode="";
			switch (msg.what) {
			case 1:{//ɾ������
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
					PatientRecord.refresh++;
					ToastCustom.makeToast(getApplicationContext(), "ɾ���ɹ�");
					finish();
				}
				else if(reCode.equalsIgnoreCase("fail")){
					if(!message.equals("")){
						ToastCustom.makeToast(getApplicationContext(), message);
					}
					else
						ToastCustom.makeToast(getApplicationContext(), "����ʧ��");
				}
				else{
					
					ToastCustom.makeToast(getApplicationContext(), "����ʧ��");
				}
				}
				break;
			case 2:{//��ȡ�����б�
				JSONObject result = (JSONObject)msg.obj;
				try {
					reCode = result.getString("reCode");
					message = result.getString("message");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(reCode.equalsIgnoreCase("SUCCESS")){
					try {
						JSONArray medicalRecordList = result.getJSONArray("medicalRecordList");
						conditions = new ArrayList<Condition>();
						for(int i=0;i<medicalRecordList.length();i++){
							Condition record = new Condition();
							JSONObject recordJson = medicalRecordList.getJSONObject(i);
							record.setMedicalID(recordJson.getString("medicalID"));
							record.setDate(recordJson.getString("date"));
							record.setDignose(recordJson.getString("disease"));
							record.setRemarks(recordJson.getString("remarks"));
							ArrayList<ImageVo> myImages = new ArrayList<ImageVo>();
							JSONArray imageList = recordJson.getJSONArray("medicalImages");
							for(int j=0;j<imageList.length();j++){
								JSONObject imageJson =imageList.getJSONObject(j);
								ImageVo imageVo= new ImageVo();
								imageVo.setImageName(imageJson.getString("remarks"));
								imageVo.setImagePath(imageJson.getString("imageUrl"));
								imageVo.setRemarks(imageJson.getString("remarks"));
								myImages.add(imageVo);
							}
							record.setImageList(myImages);
							record.setImageNum(myImages.size());
							conditions.add(record);
							Log.i("mmy", "------1");
						}
						Log.i("mmy", "------2");
						myImageAdapter = new MyImageAdapter(conditions, getApplicationContext());
						//imageadapter = new ImageAdapter();
						listview.setAdapter(myImageAdapter);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(reCode.equalsIgnoreCase("fail")){
					if(!message.equals("")){
						ToastCustom.makeToast(getApplicationContext(), message);
					}
					else
						ToastCustom.makeToast(getApplicationContext(), "��ȡ����ʧ��");
				}
				else{
					
					ToastCustom.makeToast(getApplicationContext(), "��ȡ����ʧ��");
				}
			}
				break;
			case 0:{
				ToastCustom.makeToast(getApplicationContext(), "����ʧ��");
				break;
			}
			case -1:
				ToastCustom.makeToast(getApplicationContext(), "δ�����ӵ�������");
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
		setContentView(R.layout.activity_listview);
//		registerBoradcastReceiver();
		this.setTitle("��ϸ��Ϣ");
		Intent intent = getIntent();
		people = (Patient) intent.getSerializableExtra("detail");
		id = people.getPatientId();
		initViews();
		initDates();
		initEvents();
		
		
		getMedicalrecord();
		// imageadapter.notifyDataSetChanged();
		// Log.i("wyy", "" + 11111);
	}

	void getMedicalrecord(){
		showRequestDialog();
		
    	new Thread(){
			@Override
			public void run() {
				Message message=new Message();
				if(ConnectUtil.isNetworkAvailable(MedicalRecordListActivity.this)){
					SharePreferenceUtil sp = new SharePreferenceUtil(MedicalRecordListActivity.this, MyConstants.SAVE_USER);
					PostParameter[] postParams;
					postParams = new PostParameter[2];
					postParams[0] = new PostParameter("docUsername",sp.getAccount());
					postParams[1] = new PostParameter("patientUsername",people.getPatientId());
					
					String jsonStr = ConnectUtil.httpRequest(ConnectUtil.DoctorGetMedicalRecordList, postParams, ConnectUtil.POST);
					if(jsonStr==null||jsonStr.equals("")){
						message.what=0;//ʧ��
						message.obj="fail";
						handler.sendMessage(message);
					}
					else{
						
						try {
							JSONObject result = new JSONObject(jsonStr);
							//String msg= result.getString("reCode");
							message.what=2;
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
					message.obj="���粻����";
					handler.sendMessage(message);
				}
			}
		}.start();
	}
	private void initEvents() {
		// TODO Auto-generated method stub

		addImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Toast.makeText(ListActivity.this, "���", 1000).show();
				showPopUpWindow();
			}
		});

	}

	private void initDates() {
		// TODO Auto-generated method stub
		//initImagePaths();
		conditions = new ArrayList<Condition>();
		myImageAdapter = new MyImageAdapter(conditions, getApplicationContext());
		//imageadapter = new ImageAdapter();
		listview.setAdapter(myImageAdapter);
		int id;
		String sex="��";
		if (people.getSex().equals("0")) {
			id = R.drawable.user_image;
			sex="��";
		} else {
			id = R.drawable.user_image_female;
			sex="Ů";
		}
		if (people != null) {
			
			name.setText(people.getName());
			String birth = people.getBirthday();
			age.setText(MyTools.getAge(birth)+ "��");
			//age.setText(MyTools.getAge(birth) + "��");
			sexy.setText(sex);
			tel.setText("�绰:" + people.getMobile());
		}
		
		Log.i("wyy", "after7");
		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), id);
		Bitmap output = RoundCorner.toRoundCorner(bitmap, 200);
		imageview.setImageBitmap(output);
	}

//	private void initImagePaths() {
//		conditions = people.getConditions();
//		List<Folder> li = people.getFolders();
//		Log.i("wyy", "after1");
//		imagePaths.clear();
//		Log.i("wyy", "after2");
//		for (Folder folder : li) {
//			imagePaths.add(folder.getImagePath());
//			imagePaths_name.add(folder.getFolderDescripe());
//		}
//	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("wyy", "" + 22222);
	}

	private void initViews() {
		// TODO Auto-generated method stub
		imageview = (ImageView) this.findViewById(R.id.detail_image);
		name = (TextView) this.findViewById(R.id.detail_name);
		age = (TextView) this.findViewById(R.id.detail_age);
		sexy = (TextView) this.findViewById(R.id.detail_sexy);
		tel = (TextView) this.findViewById(R.id.detail_tel);

		addImageView = (ImageView) this.findViewById(R.id.add_imageview);

		listview = (ListView) this.findViewById(R.id.imagelistview);

		noDataLayout = this.findViewById(R.id.no_data_layout);
		noDataLayout.setVisibility(View.GONE);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				Intent intent = new Intent(MedicalRecordListActivity.this,
//						ImagePagerActivity.class);
//				Image
//				intent.putExtra("image", (Serializable) imagePaths);
//				loc = 0;
//				for (int i = 0; i < arg2; i++) {
//					loc = loc + conditions.get(i).getImageNum();
//				}
//				intent.putExtra("loc", loc);
//				startActivity(intent);
			}
		});

		// listview.setOnItemLongClickListener(new OnItemLongClickListener() {
		//
		// @Override
		// public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
		// int arg2, long arg3) {
		// // TODO Auto-generated method stub
		//
		// final int delLine = arg2;
		//
		// AlertDialog.Builder builder = new AlertDialog.Builder(
		// ListActivity.this);
		// builder.setItems(new String[] { "ɾ��" },
		// new DialogInterface.OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog,
		// int which) {
		// // TODO Auto-generated method stub
		// switch (which) {
		// case 0:
		// timeimages.get(delLine).delete();
		// Intent mIntent = new Intent("wyy");
		// mIntent.putExtra("yaner", "���͹㲥���൱�������ﴫ������");
		// // ���͹㲥
		// ListActivity.this.sendBroadcast(mIntent);
		// break;
		// default:
		// break;
		// }
		// }
		// });
		// builder.create().show();
		// return true;
		// }
		// });
	}


//	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
//
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			// TODO Auto-generated method stub
//			String action = intent.getAction();
//			if (action.equals(Constants.BROADCAST_MEDICALRECORD)) {
//				// Toast.makeText(ListActivity.this, "����action�������Ӧ�Ĺ㲥", 200)
//				// .show();
//				id = intent.getIntExtra("id", 0);
//				Log.i("wyy", "rece id:" + id);
//				people = DataSupport.find(People.class, id, true);
//				// Log.i("wyy", "condition size" +
//				// people.getConditions().size());
//				// initDates();
//				initImagePaths();
//				Log.i("wyy", "after8");
//				imageadapter.notifyDataSetChanged();
//				Log.i("wyy", "after9");
//			}
//		}
//	};
//
//	public void registerBoradcastReceiver() {
//		IntentFilter myIntentFilter = new IntentFilter();
//		myIntentFilter.addAction(Constants.BROADCAST_MEDICALRECORD);
//		// ע��㲥
//		registerReceiver(mBroadcastReceiver, myIntentFilter);
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.tel_menu:
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ people.getMobile()));
			startActivity(intent);
			break;
		case R.id.dele_menu:
			deleteDialog = new DeleteDialog(MedicalRecordListActivity.this,
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
//							DataSupport.delete(People.class, id);
							deleteDialog.dismiss();
							deletePatient();
							//sendBroadCast();
							
						}
					});
			deleteDialog.show();
			deleteDialog.setTitle("ȷ��Ҫɾ���������");
			deleteDialog.startAnimation();
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	public void deletePatient(){
		showRequestDialog();
		
    	new Thread(){

			@Override
			public void run() {
				Message message=new Message();
				if(ConnectUtil.isNetworkAvailable(MedicalRecordListActivity.this)){
					SharePreferenceUtil sp = new SharePreferenceUtil(MedicalRecordListActivity.this, MyConstants.SAVE_USER);
					PostParameter[] postParams;
					postParams = new PostParameter[2];
					postParams[0] = new PostParameter("docUsername",sp.getAccount());
					postParams[1] = new PostParameter("patientUsername",people.getPatientId());
					
					String jsonStr = ConnectUtil.httpRequest(ConnectUtil.DeletePatient, postParams, ConnectUtil.POST);
					if(jsonStr==null||jsonStr.equals("")){
						message.what=0;//ʧ��
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
					message.obj="���粻����";
					handler.sendMessage(message);
				}
			}
		}.start();
	}

	
//	private void sendBroadCast() {
//		Intent mIntent = new Intent(Constants.BROADCAST_PATIENTRECORD);
//		mIntent.putExtra("yaner", "���͹㲥���൱�������ﴫ������");
//		// ���͹㲥
//		this.sendBroadcast(mIntent);
//	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
//		unregisterReceiver(mBroadcastReceiver);
		super.onDestroy();
	}

	private void showPopUpWindow() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(this);
		View popview = inflater.inflate(R.layout.activity_pop, null);

		View view_image = popview.findViewById(R.id.pop_image);
//		View view_table = popview.findViewById(R.id.pop_table);

		int screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // ��Ļ�����أ��磺480px��
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // ��Ļ�ߣ����أ��磺800p
		RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) addImageView
				.getLayoutParams();
		int m = layoutParams.bottomMargin;
		final PopupWindow mPopupWindow = new PopupWindow(popview, screenWidth,
				screenWidth, true);
		mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
		mPopupWindow.showAtLocation(addImageView, Gravity.BOTTOM, 0,
				addImageView.getHeight() + m * 2);
		mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
		mPopupWindow.update();
		view_image.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPopupWindow.dismiss();
				Intent intent = new Intent(MedicalRecordListActivity.this,
						MedicalRecordAddtionImageActivity.class);
				intent.putExtra("value", people);
//				startActivity(intent);
				startActivityForResult(intent, 0);
			}
		});
//		view_table.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				mPopupWindow.dismiss();
//			}
//		});
		// popview.setOnKeyListener(new OnKeyListener() {
		//
		// @Override
		// public boolean onKey(View v, int keyCode, KeyEvent event) {
		//
		// switch (keyCode) {
		// case KeyEvent.KEYCODE_BACK:
		// if (mPopupWindow != null && mPopupWindow.isShowing()) {
		// mPopupWindow.dismiss();
		// System.out.println("menuGridfdsfdsfdfd");
		// }
		// break;
		// }
		// return true;
		// }
		// });
	}
	private void showRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
		mDialog = DialogFactory.creatRequestDialog(MedicalRecordListActivity.this, "���Ե�...");
		mDialog.show();
	}

	private void dismissRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case 0:
			getMedicalrecord();
			break;
		}
	}
}
