package wyy.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wyy.utils.Constants;
import wyy.widget.ClearEditText;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.imagemanager6.R;

public class ModelDetailActivity extends Activity {

	private ListView listview;
	private List<String> strs;
	private ClearEditText mClearEditText;
	private MyAdapter adapter = new MyAdapter();

	// private Animation animation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modeldetail);
		this.setTitle("模型库");
		initDates();
		initViews();
		// SlidingMenu menu = new SlidingMenu(this);
		// menu.setMode(SlidingMenu.LEFT);
		// menu.setTouchModeAbove(SlidingMenu.LEFT);
		// menu.setShadowWidthRes(R.dimen.shadow_width);
		// menu.setShadowDrawable(R.drawable.shadow);
		// menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// menu.setFadeDegree(0.35f);
		// menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		// menu.setMenu(R.layout.activity_ps);

	}

	private void initDates() {
		// TODO Auto-generated method stub
		strs = new ArrayList<String>();
		String[] stringArray = getResources().getStringArray(
				R.array.gangbanname);
		strs = Arrays.asList(stringArray);
		// animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
	}

	private void initViews() {
		// TODO Auto-generated method stub
		listview = (ListView) this.findViewById(R.id.modeldetaillistview);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ModelDetailActivity.this,
						ModelWatchActivity.class);
				intent.putExtra("name", arg2 + "");
				startActivity(intent);
			}
		});
		mClearEditText = (ClearEditText) this.findViewById(R.id.filter_edit);
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				// filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		LayoutAnimationController lac = new LayoutAnimationController(
				AnimationUtils.loadAnimation(this, R.anim.zoom_in));
		lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
		listview.setLayoutAnimation(lac);
		listview.startLayoutAnimation();
	}

	final class ViewHolder {
		TextView tv;
		ImageView iv;
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewholder;
			if (convertView == null) {
				viewholder = new ViewHolder();
				LayoutInflater inflater = LayoutInflater
						.from(ModelDetailActivity.this);
				convertView = inflater.inflate(R.layout.item_modellist, null);
				viewholder.tv = (TextView) convertView
						.findViewById(R.id.modelname);
				viewholder.iv = (ImageView) convertView
						.findViewById(R.id.model_sl);
				// convertView.setAnimation(animation);

				// convertView.is
				convertView.setTag(viewholder);

			} else {
				viewholder = (ViewHolder) convertView.getTag();

			}
			viewholder.tv.setText(strs.get(position));
			viewholder.iv.setBackgroundResource(Constants.imgId[position]);
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
			return strs.size();
		}

	}

	private void filterData(String filterStr) {
		List<String> filterDetaList = new ArrayList<String>();
		if (TextUtils.isEmpty(filterStr)) {
			filterDetaList = strs;
		}
		filterDetaList.clear();
		for (String string : strs) {
			if (string.indexOf(filterStr.toString()) != -1
					|| string.startsWith(filterStr.toString())) {
				filterDetaList.add(string);
			}
		}

	}
}
