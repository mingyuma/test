package passenger.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wyy.bean.People;
import wyy.utils.CharacterParser;
import wyy.utils.PinyinComparator;
import wyy.widget.ClearEditText;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.imagemanager6.R;

public class DiseaseActivity extends Activity {
	private ListView listview;
	private List<Disease> diseases;
	private int type;
	private String xmlfile;
	private ClearEditText mClearEditText;
	private CharacterParser characterParser;
	private PinyinComparator pinyinComparator;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disease);
		type = getIntent().getIntExtra("type", 0);
		initdata();
		Log.i("wyy", "diseases.size():" + diseases.size());
		initviews();
	}

	private void initdata() {
		// TODO Auto-generated method stub
		switch (type) {
		case 0:
			break;
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:

			break;
		case 7:

			break;
		case 8:

			break;
		case 9:

			break;
		case 10:

			break;
		case 11:

			break;
		case 12:

			break;
		case 13:

			break;
		default:
			break;
		}

		InputStream in;
		try {
			in = this.getAssets().open("gu.xml");
			diseases = new ArrayList<Disease>();
			diseases = DiseaseXmlParser.DiseasexmlParser(in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();
		adapter = new MyAdapter(diseases);
	}

	private void initviews() {
		// TODO Auto-generated method stub

		mClearEditText = (ClearEditText) this
				.findViewById(R.id.disea_filter_edit);

		listview = (ListView) this.findViewById(R.id.disease_listview);
		listview.setAdapter(adapter);
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
	}

	class MyAdapter extends BaseAdapter {

		private List<Disease> list = null;

		public MyAdapter(List<Disease> list) {
			super();
			this.list = list;
		}

		public void updateListView(List<Disease> list) {
			this.list = list;
			notifyDataSetChanged();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewholder = new ViewHolder();
			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater
						.from(DiseaseActivity.this);
				convertView = inflater.inflate(R.layout.item_activity_disease,
						null);
				viewholder.dis_name = (TextView) convertView
						.findViewById(R.id.dis_name);
				viewholder.dis_nickname = (TextView) convertView
						.findViewById(R.id.dis_nickname);
				viewholder.dis_cheifContent = (TextView) convertView
						.findViewById(R.id.dis_chiefcontent);
				viewholder.dis_performs = (TextView) convertView
						.findViewById(R.id.dis_performs);
				convertView.setTag(viewholder);
			} else {
				viewholder = (ViewHolder) convertView.getTag();
			}
			Log.i("wyy", "position" + position);
			if (position % 2 != 0) {
				convertView.setBackgroundColor(getResources().getColor(
						R.color.qianhuise));
			} else {
				convertView.setBackgroundColor(getResources().getColor(
						R.color.chunbai));
			}
			viewholder.dis_name.setText(list.get(position).getName());
			viewholder.dis_nickname.setText("(别名："
					+ list.get(position).getNickName() + ")");
			viewholder.dis_cheifContent.setText(list.get(position)
					.getChiefContent());
			viewholder.dis_performs.setText(list.get(position).getPerforms());
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
			return list.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

	}

	final static class ViewHolder {
		TextView dis_name;
		TextView dis_nickname;
		TextView dis_cheifContent;
		TextView dis_performs;
	}

	private void filterData(String filterStr) {
		List<Disease> filterDateList = new ArrayList<Disease>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = diseases;
		} else {
			filterDateList.clear();
			for (Disease sortModel : diseases) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}
		// 根据a-z进行排序
		// Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}
}
