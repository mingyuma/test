package passenger.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import wyy.activity.BaseActivity;
import wyy.widget.XListView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.imagemanager6.R;
import com.mmy.patient.MainDoctorDetailActivity;
import com.xd.Vos.Doctor;

public class FindDoctorActivity extends BaseActivity implements
		XListView.IXListViewListener {

	private static final int HOSPITAL_REQUEST_CODE = 100;
    private static final int SEARCH_BASED_ON_HOSPITAL = 1;
    private static final int SEARCH_BASED_ON_DOCTOR = 2;
	
	private List<Doctor> doctorVos=new ArrayList<Doctor>();
	private XListView xlistview;
	private Handler mHandler;

	private LinearLayout find_hospital;
	private LinearLayout find_doctor;
	
	private int[] image = { R.drawable.doctor1, R.drawable.doctor2,
			R.drawable.doctor3 };
	private String[] names = { "李磊", "李明", "韩梅梅" };
	private String[] grade = { "90.5", "85.8", "88.5" };
	private String[] clazz = { "主任医师", "主任医师", "副主任医师" };
	private String[] hospital = { "西京医院         骨科", "西京医院         骨科",
			"西京医院         骨科" };
	private String[] jianjie = { "地方性骨病、关节退行性骨病、骨肿瘤", "胫骨平台骨折,职业性骨病",
			"先天性骨病、代谢性骨病、骨坏死" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finddoctor);
		initViews();
		initDates();
		initEvents();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		xlistview = (XListView) this.findViewById(R.id.finddoc_listview);
		
		find_hospital = (LinearLayout)this.findViewById(R.id.ll_find_hospital);
		find_doctor = (LinearLayout)this.findViewById(R.id.ll_find_doctor);
		find_hospital.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FindDoctorActivity.this,SearchDoctorActivity.class);
				intent.putExtra("flag", SEARCH_BASED_ON_HOSPITAL);
				startActivityForResult(intent, HOSPITAL_REQUEST_CODE);		
			}
		});
		
		find_doctor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FindDoctorActivity.this,SearchDoctorActivity.class);
				intent.putExtra("flag", SEARCH_BASED_ON_DOCTOR);
				startActivity(intent);	
				
			}
		});
		
		
		xlistview.setPullRefreshEnable(true);
		xlistview.setPullLoadEnable(true);
		xlistview.setAutoLoadEnable(true);
		xlistview.setXListViewListener(this);
		xlistview.setRefreshTime(getTime());
		xlistview.setOnItemClickListener(new MyOnItemClickListener());
	}

	public class MyOnItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent(FindDoctorActivity.this, MainDoctorDetailActivity.class);
			Doctor doctor=new Doctor();
			doctor.setName(names[position-1]);
			doctor.setGrade(clazz[position-1]);
			doctor.setIntro(jianjie[position-1]);
			doctor.setLevel(grade[position-1]);
			doctor.setHospital("西京医院");
			doctor.setDepartment("骨科");
			doctorVos.add(doctor);
			intent.putExtra("intent_inter", doctorVos.get(0));
			//intent.putExtra("intent_inter", doctor);
			startActivityForResult(intent, 1);
			
//			startActivity(intent);
		}
    	
    }
	private void initDates() {
		// TODO Auto-generated method stub
		mHandler = new Handler();
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		xlistview.setAdapter(new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ViewHolder viewholder = null;
				if (convertView == null) {
					viewholder = new ViewHolder();
					convertView = LayoutInflater.from(FindDoctorActivity.this)
							.inflate(R.layout.item_finddoctot, null);
					viewholder.image = (ImageView) convertView
							.findViewById(R.id.doc_image);
					viewholder.name = (TextView) convertView
							.findViewById(R.id.doc_name);
					viewholder.clazz = (TextView) convertView
							.findViewById(R.id.doc_class);
					viewholder.grade = (TextView) convertView
							.findViewById(R.id.doc_grade);
					viewholder.hospital = (TextView) convertView
							.findViewById(R.id.doc_hosipital);
					viewholder.jianjie = (TextView) convertView
							.findViewById(R.id.doctor_info);
					convertView.setTag(viewholder);
				} else {
					viewholder = (ViewHolder) convertView.getTag();
				}
				Log.i("wyy1", "position:" + position);
				imageLoader.displayImage("drawable://" + image[position],
						viewholder.image);
				viewholder.name.setText(names[position]);
				viewholder.clazz.setText(clazz[position]);
				viewholder.grade.setText(grade[position]);
				viewholder.hospital.setText(hospital[position]);
				viewholder.jianjie.setText("擅长:" + jianjie[position]);
				return convertView;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return names.length;
			}
		});
	}

	final static class ViewHolder {
		ImageView image;
		TextView name;
		TextView grade;
		TextView clazz;
		TextView hospital;
		TextView jianjie;
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// mIndex = ++mRefreshIndex;
				// items.clear();
				// geneItems();
				// mAdapter = new ArrayAdapter<String>(XListViewActivity.this,
				// R.layout.vw_list_item, items);
				// mListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// geneItems();
				// mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

	private void onLoad() {
		xlistview.stopRefresh();
		xlistview.stopLoadMore();
		xlistview.setRefreshTime(getTime());
	}

	private String getTime() {
		return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA)
				.format(new Date());
	}
}
