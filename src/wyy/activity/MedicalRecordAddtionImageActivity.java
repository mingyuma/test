package wyy.activity;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mmy.tools.MyTools;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import wyy.bean.Condition;
import wyy.bean.Folder;
import wyy.fragment.PatientRecord;
import wyy.utils.Constants;
import wyy.utils.StorageUtil;
import wyy.utils.TimeUtil;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imagemanager6.R;
import com.xd.Vos.ImageVo;
import com.xd.Vos.Patient;
import com.xd.dialog.DialogFactory;
import com.xd.util.ImageSettingUtil.ImageUploadDelegate;
import com.xd.util.ConnectUtil;
import com.xd.util.ImageSettingUtil;
import com.xd.util.MyConstants;
import com.xd.util.PostParameter;
import com.xd.util.SharePreferenceUtil;
import com.xd.util.ToastCustom;
import com.zhy.imageloader.LookPicActivity;

public class MedicalRecordAddtionImageActivity extends BaseActivity implements ImageUploadDelegate {

	private TextView tv_date;
	private EditText ed_remarks;
	private View takePicLayout;
	private Button save;

	private Spinner sp_image1;
	private Spinner sp_image2;
	private Spinner sp_image3;

	private String path;
	private List<String> paths = new ArrayList<String>();

	private Calendar calendar = Calendar.getInstance();
	private final static int LOCAL = 1;
	private final static int CAMERA = 2;
	
	List<String> listStr = new ArrayList<String>();
	private Dialog mDialog = null;
	
	private ImageView image1;
	private ImageView image2;
	private ImageView image3;
	private String[] array_hurtOfBody = { "腿部", "脚部", "髋部", "手臂", "手掌", "头部",
			"肩部", "胸部" };
	List<ImageView> listImageView = new ArrayList<ImageView>();
	List<Spinner> listSpinner = new ArrayList<Spinner>();
	private Patient people;
	private String id;
	String medicalId="";
	Patient temp;
	private Handler handler = new Handler(){
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
					
					PatientRecord.refresh=2;
					try {
						medicalId = result.getString("medicalID");
						if(!medicalId.equals("") && paths.size()>0){
							updatePic();
						}
						else{
							ToastCustom.makeToast(getApplicationContext(), "新建病例成功");
							setResult(0);
							finish();
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//finish();
				}
				else if(reCode.equalsIgnoreCase("fail")){
					if(!message.equals("")){
						ToastCustom.makeToast(getApplicationContext(), message);
					}
					else
						ToastCustom.makeToast(getApplicationContext(), "新建病例失败");
				}
				else{
					
					ToastCustom.makeToast(getApplicationContext(), "新建病例失败");
				}
				}
				break;
			case 0:{
				ToastCustom.makeToast(getApplicationContext(), "新建病例失败");
				break;
			}
			case -1:
				ToastCustom.makeToast(getApplicationContext(), "未能连接到服务器");
				break;
			case 3:
				ToastCustom.makeToast(getApplicationContext(), "新建病例成功");
				break;
			case 4:
				ToastCustom.makeToast(getApplicationContext(), "新建病例成功,图片上传失败");
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
		setContentView(R.layout.activity_additionimage);
		this.setTitle("添加资料");
		initViews();
		initDates();
		initEvents();
	}
	void updatePic(){
		showRequestDialog();
		new Thread(){

			@Override
			public void run() {
				
				for(int i=0;i<paths.size();i++){
					ImageVo imageVo= new ImageVo();
					imageVo.setImagePath(paths.get(i)+".jpg");
					String str = temp.getPatientId()+"_"+System.currentTimeMillis()+"";
					imageVo.setImageName(str);
					String remarks=listStr.get(i);
					//Drawable drawable = Drawable.createFromPath("file://"+paths.get(0));
					BitmapDrawable bp = (BitmapDrawable)BitmapDrawable.createFromPath(""+paths.get(i));
					Bitmap bm= bp.getBitmap();
					PostParameter[] postParams;
					postParams = new PostParameter[3];
					postParams[0] = new PostParameter("imageName",str+".jpg");
					postParams[1] = new PostParameter("remarks",remarks);
					postParams[2] = new PostParameter("medicalID",medicalId);
				
					InputStream is= ImageSettingUtil.compressJPG(paths.get(i));
					ImageSettingUtil.uploadImage(MedicalRecordAddtionImageActivity.this, i, postParams, imageVo, is);
				}
				
			}
		}.start();
	}
	private void initViews() {
		// TODO Auto-generated method stub
		tv_date = (TextView) this.findViewById(R.id.additionimage_date);
		ed_remarks = (EditText) this.findViewById(R.id.additionimage_remarks);
		takePicLayout = this.findViewById(R.id.add_imgae_layout);
		save = (Button) this.findViewById(R.id.addition_save);
		image1 = (ImageView) this.findViewById(R.id.addition_image1);
		image2 = (ImageView) this.findViewById(R.id.addition_image2);
		image3 = (ImageView) this.findViewById(R.id.addition_image3);
		sp_image1 = (Spinner) this.findViewById(R.id.sp_image1);
		sp_image2 = (Spinner) this.findViewById(R.id.sp_image2);
		sp_image3 = (Spinner) this.findViewById(R.id.sp_image3);
	}

	private void initDates() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		temp = (Patient) intent.getExtras().get("value");;
		id = temp.getPatientId();
		//people = DataSupport.find(People.class, id);
		tv_date.setText(calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DATE));
		listImageView.add(image1);
		listImageView.add(image2);
		listImageView.add(image3);
		listSpinner.add(sp_image1);
		listSpinner.add(sp_image2);
		listSpinner.add(sp_image3);
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		setSpImageAdapter();
		takePicLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(paths.size()<3){
					showChoice();
				}
				else{
					ToastCustom.makeToast(getApplicationContext(), "一次最多上传三张照片");
				}
				
			}
		});
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (paths.isEmpty()) {
					Toast.makeText(MedicalRecordAddtionImageActivity.this,
							"请添加图片", 1000).show();
					return;
				}
				saveToDataBase();
//				List<Folder> folders = people.getFolders();
//				List<Condition> conditions = people.getConditions();
//				Condition condition = new Condition();
//				condition.setDignose("");
//				condition.setDate(tv_date.getText().toString());
//				condition.setRemarks(ed_remarks.getText().toString());
//				condition.setImageNum(paths.size());
//				//condition.save();
//				conditions.add(condition);
//				for (int i = 0; i < paths.size(); i++) {
//					Folder folder = new Folder();
//					folder.setFolderDescripe(people.getName());
//					folder.setImagePath(paths.get(i));
//					folder.setBodyLoction(listSpinner.get(i).getSelectedItem()
//							.toString());
//					if (folder.save()) {
//						Log.i("wyy", "folder保存成功");
//					} else {
//						Log.i("wyy", "folder保存成功");
//					}
//					folders.add(folder);
//				}
//				if (people.save()) {
//					Log.i("wyy", "people保存成功");
//				} else {
//					Log.i("wyy", "people失败");
//				}
//				Intent mIntent = new Intent(Constants.BROADCAST_MEDICALRECORD);
//				mIntent.putExtra("id", id);
//				// 发送广播
//				MedicalRecordAddtionImageActivity.this.sendBroadcast(mIntent);
//				finish();
			}
		});
	}
	private void saveToDataBase() {

		showRequestDialog();
		
    	new Thread(){

			@Override
			public void run() {
				Message message=new Message();
				if(ConnectUtil.isNetworkAvailable(MedicalRecordAddtionImageActivity.this)){
					SharePreferenceUtil sp = new SharePreferenceUtil(MedicalRecordAddtionImageActivity.this, MyConstants.SAVE_USER);
					String sex =temp.getSex();
//					if(pa.getSelectedItem().toString().equals("男")){
//						sex="0";
//					}else{
//						sex="1";
//					}
					PostParameter[] postParams;
					postParams = new PostParameter[9];
					postParams[0] = new PostParameter("docUsername",sp.getAccount());
					postParams[1] = new PostParameter("patientUsername",temp.getPatientId());
					postParams[2] = new PostParameter("disease",ed_remarks.getText().toString());
					postParams[3] = new PostParameter("date",tv_date.getText().toString());
					postParams[4] = new PostParameter("remark","");
					postParams[5] = new PostParameter("name",temp.getName());
					postParams[6] = new PostParameter("sex",sex);
					postParams[7] = new PostParameter("age",temp.getBirthday());
					postParams[8] = new PostParameter("telephone",temp.getMobile());
					
					String jsonStr = ConnectUtil.httpRequest(ConnectUtil.AddPatientMedicalRecord, postParams, ConnectUtil.POST);
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
	private void showChoice() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setItems(new String[] { "本机相册", "拍摄" },
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent;
						switch (which) {
						case 0:
							intent = new Intent(
									MedicalRecordAddtionImageActivity.this,
									LookPicActivity.class);
							startActivityForResult(intent, LOCAL);
							break;
						case 1:
							intent = new Intent();
							intent.addCategory("android.intent.category.DEFAULT");
							intent.setAction("android.media.action.IMAGE_CAPTURE");
							File dir = StorageUtil.getOwnCacheDirectory(
									MedicalRecordAddtionImageActivity.this,
									"/imagemanager6/images/");
							String filename = TimeUtil.getCurrentTime(false);
							File file = new File(dir.getAbsolutePath()
									+ File.separator + filename + ".jpg");
							path = file.getAbsolutePath();
							Log.i("wyy", "path:" + path);
							Uri uri = Uri.fromFile(file);
							intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
							startActivityForResult(intent, CAMERA);
							break;
						default:
							break;
						}
					}
				});
		builder.create().show();
	}

	private void setSpImageAdapter() {
		ArrayAdapter<String> ada = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, array_hurtOfBody);
		ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_image1.setAdapter(ada);
		sp_image2.setAdapter(ada);
		sp_image3.setAdapter(ada);
		sp_image1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		    String cardNumber = array_hurtOfBody[arg2];
		    listStr.add(0, cardNumber);
		    //设置显示当前选择的项
		    arg0.setVisibility(View.VISIBLE);
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> arg0) {
		    // TODO Auto-generated method stub
		    	
		    }
		});
		sp_image2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		    String cardNumber = array_hurtOfBody[arg2];
		    listStr.add(1, cardNumber);
		    //设置显示当前选择的项
		    arg0.setVisibility(View.VISIBLE);
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> arg0) {
		    // TODO Auto-generated method stub
		    	
		    }
		});
		sp_image3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		    String cardNumber = array_hurtOfBody[arg2];
		    listStr.add(2, cardNumber);
		    //设置显示当前选择的项
		    arg0.setVisibility(View.VISIBLE);
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> arg0) {
		    // TODO Auto-generated method stub
		    	
		    }
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub

		outState.putSerializable("paths", (Serializable) paths);
		outState.putString("path", path);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		paths = (List<String>) savedInstanceState.getSerializable("paths");
		path = savedInstanceState.getString("path");
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case LOCAL:
			if (data == null) {
				return;
			}
			path = data.getExtras().getString("image");
			// imageLoader.displayImage("file://" + path, image1);
			paths.add(path);
			listStr.add("腿部");
			break;
		case CAMERA:
			// imageLoader.displayImage("file://" + path, image1);
			paths.add(path);
			listStr.add("腿部");
			break;

		default:
			break;
		}
		for (int i = 0; i < paths.size(); i++) {
			String str = paths.get(i);
			imageLoader.displayImage("file://" + str, listImageView.get(i),
					options);
			listImageView.get(i).setClickable(false);
			listSpinner.get(i).setVisibility(View.VISIBLE);
		}

	}
	@Override
	public void setUploadProgress(int index, double x) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getRecodeFromServer(int index, String reCode) {
		// TODO Auto-generated method stub
		if(reCode.equalsIgnoreCase("success")&&index==paths.size()-1){
			dismissRequestDialog();
			Message msg=new Message();
			msg.what=2;
			handler.sendMessage(msg);
			setResult(0);
			finish();
		}
		else if(reCode.equalsIgnoreCase("false")){
			Message msg=new Message();
			msg.what=2;
			handler.sendMessage(msg);
			//index=+1;
			//ToastCustom.makeToast(getApplicationContext(), "新建病例成功，但图片"+index+"上传失败");
		}
		
	}
	private void showRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
		mDialog = DialogFactory.creatRequestDialog(MedicalRecordAddtionImageActivity.this, "请稍等...");
		mDialog.show();
	}

	private void dismissRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
	}
}
