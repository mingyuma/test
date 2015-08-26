package wyy.activity;

import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import wyy.widget.dd.CircularProgressButton;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.imagemanager6.R;
import com.xd.dialog.DialogFactory;
import com.xd.util.ComfirmPassword;
import com.xd.util.ConnectUtil;
import com.xd.util.PostParameter;
import com.xd.util.ToastCustom;

public class RegisterActivity extends BaseActivity {
	private CircularProgressButton cpb;
	EditText etName, etSex, etBirth, etType, etPwd1, etPwd2;
	private String[] sexArr = { "��", "Ů" };
	private String[] typeArr = { "����", "ʵϰҽʦ", "����ҽʦ", "����ҽʦ" };
	int chooseItem = 0;
	int chooseItem2 = 0;
	String oldpwd = "", account = "", sex = "", birth = "", sexStr = "";
	String newpwd = "";
	private Dialog mDialog = null;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			// Ҫ��������
			dismissRequestDialog();
			String message = "", reCode = "";
			switch (msg.what) {
			case 1: {
				// String mesg=(String)msg.obj;
				JSONObject result = (JSONObject) msg.obj;
				Log.i("wyy", result.toString());
				try {
					reCode = result.getString("reCode");
					message = result.getString("message");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (reCode.equalsIgnoreCase("SUCCESS")) {
					ToastCustom.makeToast(getApplicationContext(), "ע��ɹ�");
					RegisterActivity.this.finish();
				} else if (reCode.equalsIgnoreCase("Fail")) {
					if (!message.equals("")) {
						ToastCustom.makeToast(getApplicationContext(), message);
					} else
						ToastCustom.makeToast(getApplicationContext(),
								"ע��ʧ�ܣ������³���");
				} else {

					ToastCustom
							.makeToast(getApplicationContext(), "ע��ʧ�ܣ������³���");
				}
			}
				break;
			case 0: {
				ToastCustom.makeToast(getApplicationContext(), "ע��ʧ�ܣ������³���");
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
		setContentView(R.layout.activity_register);
		this.setTitle("ע��");
		initView();
		initData();
	}

	public void initData() {
		// sexArr={"��","Ů"};
	}

	public void initView() {
		cpb = (CircularProgressButton) this.findViewById(R.id.btnRegister);
		cpb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!judge())
					return;
				else
					submit();
			}
		});
		etName = (EditText) findViewById(R.id.regitername);
		etSex = (EditText) findViewById(R.id.regitersex);
		etBirth = (EditText) findViewById(R.id.regiterbirth);
		etType = (EditText) findViewById(R.id.regitertype);
		etPwd1 = (EditText) findViewById(R.id.regiterpassword);
		etPwd2 = (EditText) findViewById(R.id.regiterpassword2);
		etSex.setClickable(true);
		etType.setClickable(true);
		etBirth.setClickable(true);
		etSex.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						RegisterActivity.this)
						.setTitle("ѡ���Ա�")
						.setIcon(R.drawable.logo7)
						.setSingleChoiceItems(sexArr, chooseItem,
								new DialogInterface.OnClickListener() {
									//
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										String choose = sexArr[which];
										dialog.dismiss();
										etSex.setText(choose);
										chooseItem = which;
									}
								});
				builder.create().show();
			}
		});
		etType.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						RegisterActivity.this)
						.setTitle("ѡ���û�����")
						.setIcon(R.drawable.logo7)
						.setSingleChoiceItems(typeArr, chooseItem2,
								new DialogInterface.OnClickListener() {
									//
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										String choose = typeArr[which];
										dialog.dismiss();
										etType.setText(choose);
										chooseItem2 = which;
									}
								});
				builder.create().show();
			}
		});
		etBirth.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				createPickerDateDialog(RegisterActivity.this, etBirth);
			}
		});
	}

	/** �������� */
	public void createPickerDateDialog(Context context, final EditText textView) {
		Dialog dialog = null;
		String defaultDateStr = textView.getText().toString();
		int defaultYear;
		int defaultMonth;
		int defaultDay;
		if (defaultDateStr.equals("") || defaultDateStr == null) {
			Calendar c = Calendar.getInstance();
			defaultYear = c.get(Calendar.YEAR);
			defaultMonth = c.get(Calendar.MONTH);
			defaultDay = c.get(Calendar.DAY_OF_MONTH);
		} else {
			String result[] = defaultDateStr.split("-");
			defaultYear = Integer.valueOf(result[0]);
			defaultMonth = Integer.valueOf(result[1]) - 1;
			defaultDay = Integer.valueOf(result[2]);
		}

		dialog = new DatePickerDialog(context,
				new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						textView.setText(year + "-" + (monthOfYear + 1) + "-"
								+ dayOfMonth);
					}
				}, defaultYear, defaultMonth, defaultDay);
		dialog.show();
	}

	private boolean judge() {
		oldpwd = etPwd1.getText().toString().trim();
		newpwd = etPwd2.getText().toString().trim();
		account = etName.getText().toString().trim();
		sex = etSex.getText().toString().trim();
		birth = etBirth.getText().toString().trim();
		if (account.equals("")) {
			ToastCustom.makeToast(RegisterActivity.this, "�������û���");
			return false;
		}
		if (!ComfirmPassword.isLegal2(account)) {
			ToastCustom.makeToast(RegisterActivity.this, "�û���������6λ������ĸ�����ֵ����");
			return false;
		}
		if (sex.equals("")) {
			ToastCustom.makeToast(RegisterActivity.this, "��ѡ���Ա�");
			return false;
		}
		if (birth.equals("")) {
			ToastCustom.makeToast(RegisterActivity.this, "��ѡ���������");
			return false;
		}
		if (oldpwd.equals("")) {
			ToastCustom.makeToast(RegisterActivity.this, "����������");
			return false;
		}
		if (newpwd.equals("")) {
			ToastCustom.makeToast(RegisterActivity.this, "������ȷ������");
			return false;
		}
		// ���������9Ϊ������ĸ�����ֵ����
		if (!ComfirmPassword.isLegal2(oldpwd)) {
			ToastCustom.makeToast(RegisterActivity.this, "����������6λ������ĸ�����ֵ����");
			return false;
		}
		if (!oldpwd.equals(newpwd)) {
			ToastCustom.makeToast(RegisterActivity.this, "ȷ�����������벻���");
			return false;
		}

		return true;
	}

	public void submit() {
		showRequestDialog();

		new Thread() {

			@Override
			public void run() {
				Message message = new Message();
				if (sex.equals("Ů")) {
					sexStr = "1";
				} else {
					sexStr = "0";
				}

				if (ConnectUtil.isNetworkAvailable(getApplicationContext())) {
					PostParameter[] postParams = new PostParameter[4];
					postParams[0] = new PostParameter("name", account);
					postParams[1] = new PostParameter("password", newpwd);// Encode.getEncode("MD5",
																			// password)
					postParams[2] = new PostParameter("sex", sexStr);
					postParams[3] = new PostParameter("birthday", birth);
					String jsonStr = ConnectUtil.httpRequest(
							ConnectUtil.USER_REGISTER, postParams,
							ConnectUtil.POST);
					if (jsonStr == null || jsonStr.equals("")) {
						Log.i("wyy", 1+"");
						message.what = 0;// ʧ��
						message.obj = "fail";
						handler.sendMessage(message);
					} else {
						try {
							JSONObject result = new JSONObject(jsonStr);
							// String msg= result.getString("reCode");
							message.what = 1;
							message.obj = result;
							handler.sendMessage(message);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					message.what = -1;
					message.obj = "���粻����";
					handler.sendMessage(message);
				}
			}
		}.start();
	}

	private void showRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
		mDialog = DialogFactory.creatRequestDialog(this, "������֤�˺�...");
		mDialog.show();
	}

	private void dismissRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
	}
}
