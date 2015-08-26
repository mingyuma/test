package wyy.fragment;

import java.util.Arrays;
import java.util.List;

import org.litepal.crud.DataSupport;

import wyy.activity.GridDetailActivity;
import wyy.bean.Folder;
import wyy.bean.TimeImage;
import wyy.utils.Constants;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imagemanager6.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Picture extends BaseFragment {

	private GridView gridview;
	private Integer[] imageRid = { R.drawable.x_jianbu, R.drawable.x_jiaobu,
			R.drawable.x_kuanbu, R.drawable.x_shoubi, R.drawable.x_shouzhang,
			R.drawable.x_toubu, R.drawable.x_tuibu, R.drawable.x_xiongbu };
	String[] stringArray = null;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private List<Integer> allAblume;
	private List<String> allAblumeName;

	private List<Folder> list_folder;

	private ImageAdapter imageadapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_picture, null, false);
		registerBoradcastReceiver();
		imageadapter = new ImageAdapter();
		initDates();
		initViews(view);
		return view;
	}

	private void initDates() {
		// TODO Auto-generated method stub
		stringArray = getResources().getStringArray(R.array.bodyimage);
		allAblume = Arrays.asList(imageRid);
		allAblumeName = Arrays.asList(stringArray);
		list_folder = DataSupport.findAll(Folder.class, true);

	}

	private void initViews(View view) {
		// TODO Auto-generated method stub
		gridview = (GridView) view.findViewById(R.id.image_gridview);
		gridview.setAdapter(imageadapter);

		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Picture.this.getActivity(),
						GridDetailActivity.class);
				intent.putExtra("type", allAblumeName.get(arg2));
				startActivity(intent);
			}
		});

	}

	final static class ViewHolder {
		ImageView ablum_face;
		TextView ablum_name;
		TextView ablum_num;
	}

	public class ImageAdapter extends BaseAdapter {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewholder = null;

			if (convertView == null) {
				viewholder = new ViewHolder();
				LayoutInflater inflater = LayoutInflater.from(Picture.this
						.getActivity());
				convertView = inflater.inflate(R.layout.item_ablum_gridview,
						null);
				viewholder.ablum_face = (ImageView) convertView
						.findViewById(R.id.item_ablum_gridview_image);
				viewholder.ablum_name = (TextView) convertView
						.findViewById(R.id.item_ablum_gridview_name);
				viewholder.ablum_num = (TextView) convertView
						.findViewById(R.id.item_ablum_gridview_count);
				convertView.setTag(viewholder);
			} else {
				viewholder = (ViewHolder) convertView.getTag();
			}

			imageLoader.displayImage("drawable://" + allAblume.get(position),
					viewholder.ablum_face, options);

			String str = allAblumeName.get(position);
			Log.i("wyy", "swtich:" + str);
			// List<TimeImage> result = DataSupport.where("bodyLocation=?",
			// str.substring(0, 2)).find(TimeImage.class, true);
			int result = 0;
			List<Folder> find = DataSupport.where("bodyLoction=?",
					str.substring(0, 2)).find(Folder.class, true);
			for (Folder timeImage : find) {
				String imagePath = timeImage.getImagePath();
				if (imagePath != null) {
					result++;
				}
			}
			Log.i("wyy", "num:" + result);
			viewholder.ablum_name.setText(allAblumeName.get(position));
			viewholder.ablum_num.setText(result + "张");

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
			return allAblume.size();
		}
	}

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			// String action = intent.getAction();

			// Toast.makeText(ListActivity.this, "处理action名字相对应的广播", 200)
			// .show();
			initDates();
			Log.i("wyy", "gaibian");
			imageadapter.notifyDataSetChanged();

		}
	};

	public void registerBoradcastReceiver() {
		IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction(Constants.BROADCAST_PATIENTRECORD);
		myIntentFilter.addAction(Constants.BROADCAST_MEDICALRECORD);
		// 注册广播
		this.getActivity().registerReceiver(mBroadcastReceiver, myIntentFilter);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		this.getActivity().unregisterReceiver(mBroadcastReceiver);

		super.onDestroy();
	}

}
