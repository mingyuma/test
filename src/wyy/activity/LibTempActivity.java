package wyy.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import wyy.widget.XListView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.example.imagemanager6.R;

public class LibTempActivity extends BaseActivity implements
		XListView.IXListViewListener {

	private XListView listview;

	private int index;

	private String[] strs = { "" };
	private int staticindex;
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temp);
		initsViews();
		initsDates();
		initsEvents();
	}

	private void initsViews() {
		// TODO Auto-generated method stub
		listview = (XListView) this.findViewById(R.id.temp_listview);
		listview.setPullRefreshEnable(true);
		listview.setPullLoadEnable(true);
		listview.setAutoLoadEnable(true);
		listview.setXListViewListener(this);
		listview.setRefreshTime(getTime());
	}

	private void initsDates() {
		// TODO Auto-generated method stub
		mHandler = new Handler();
		index = getIntent().getIntExtra("value", 0);
		try {
			strs = getResources().getStringArray(index);
		} catch (Exception e) {
			// TODO: handle exception
		}
		switch (index) {
		case R.array.hangyexinwen:
			staticindex = 0;
			break;
		case R.array.zhuanjiagongshi:
			staticindex = 1;
			break;
		case R.array.linchuanglujing:
			staticindex = 2;
			break;
		default:
			break;
		}
	}

	private void initsEvents() {
		// TODO Auto-generated method stub
		if (!strs[0].equals(""))
			listview.setAdapter(new ArrayAdapter<String>(LibTempActivity.this,
					R.layout.item_templist, R.id.tempname, strs));
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LibTempActivity.this,
						WatchLibTempActivity.class);
				intent.putExtra("title", strs[arg2 - 1]);
				intent.putExtra("content", arg2);
				intent.putExtra("index", staticindex);
				startActivity(intent);
			}
		});
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
		listview.stopRefresh();
		listview.stopLoadMore();
		listview.setRefreshTime(getTime());
	}

	private String getTime() {
		return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA)
				.format(new Date());
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // TODO Auto-generated method stub
	// super.onCreateOptionsMenu(menu);
	// getMenuInflater().inflate(R.menu.mainmenu, menu);
	// return true;
	// }
}
