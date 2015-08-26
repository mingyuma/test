package wyy.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.imagemanager6.R;

public class ThreeDimensionalModelActivity extends BaseActivity {
	private ListView listview;
	private List<String> strs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_model);
		initViews();
		initDates();
		initEvents();
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		listview.setAdapter(new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ViewHolder viewholder;
				if (convertView == null) {
					viewholder = new ViewHolder();
					LayoutInflater inflater = LayoutInflater
							.from(ThreeDimensionalModelActivity.this);
					convertView = inflater.inflate(R.layout.item_modellist,
							null);
					viewholder.tv = (TextView) convertView
							.findViewById(R.id.modelname);
					viewholder.iv = (ImageView) convertView
							.findViewById(R.id.model_sl);
					convertView.setTag(viewholder);
				} else {
					viewholder = (ViewHolder) convertView.getTag();

				}
				viewholder.tv.setText(strs.get(position));
				viewholder.iv.setVisibility(View.GONE);
				return convertView;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return strs.size();
			}
		});

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ThreeDimensionalModelActivity.this,
						ModelDetailActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initViews() {
		// TODO Auto-generated method stub
		listview = (ListView) findViewById(R.id.modelList);

	}

	final class ViewHolder {
		TextView tv;
		ImageView iv;
	}

	private void initDates() {
		// TODO Auto-generated method stub
		strs = new ArrayList<String>();
		String[] stringArray = getResources().getStringArray(R.array.modelname);
		Log.i("wyy1", stringArray.length + ";;;;");
		strs = Arrays.asList(stringArray);
	}
}
