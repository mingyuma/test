package wyy.activity;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import wyy.bean.Folder;
import wyy.fragment.Calendar;
import wyy.fragment.Lib;
import wyy.fragment.PatientRecord;
import wyy.fragment.Picture;
import wyy.widget.ChangeColorIconWithText;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.imagemanager6.R;

public class MainActivity extends FragmentActivity implements OnClickListener,
		OnPageChangeListener {

	private ViewPager mViewPager;
	private List<Fragment> listFragment = new ArrayList<Fragment>();
	private List<ChangeColorIconWithText> listCCIWT = new ArrayList<ChangeColorIconWithText>();
	private FragmentPagerAdapter mFPAdapter;

	private long mExitTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SQLiteDatabase db = Connector.getDatabase();
		// DataSupport.deleteAll(People.class);
		// DataSupport.deleteAll(CalendarEvents.class);
		// DataSupport.deleteAll(Folder.class);
		// DataSupport.deleteAll(Condition.class);
		int i = DataSupport.findAll(Folder.class).size();
		Log.i("wyy", "folder.size:" + i);
		initView();
		initDatas();
		mViewPager.setAdapter(mFPAdapter);
		initEvent();
	}

	private void initView() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager) this.findViewById(R.id.id_viewpager);
		ChangeColorIconWithText one = (ChangeColorIconWithText) this
				.findViewById(R.id.id_indicator_one);
		ChangeColorIconWithText two = (ChangeColorIconWithText) this
				.findViewById(R.id.id_indicator_two);
		ChangeColorIconWithText three = (ChangeColorIconWithText) this
				.findViewById(R.id.id_indicator_three);
		ChangeColorIconWithText four = (ChangeColorIconWithText) this
				.findViewById(R.id.id_indicator_four);
		listCCIWT.add(one);
		listCCIWT.add(two);
		listCCIWT.add(three);
		listCCIWT.add(four);

		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
		one.setIconAlpha(1.0f);

	}

	private void initDatas() {
		// TODO Auto-generated method stub
		PatientRecord fragment1 = new PatientRecord();
		Picture fragment2 = new Picture();
		Calendar fragment3 = new Calendar();
		Lib fragment4 = new Lib();
		listFragment.add(fragment1);
		listFragment.add(fragment2);
		listFragment.add(fragment3);
		listFragment.add(fragment4);
		mFPAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return listFragment.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return listFragment.get(arg0);
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				// TODO Auto-generated method stub
				// super.destroyItem(container, position, object);
			}

		};

	}

	private void initEvent() {
		// TODO Auto-generated method stub
		mViewPager.setOnPageChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		clickTab(v);
	}

	private void clickTab(View v) {
		resetOtherTabs();

		switch (v.getId()) {
		case R.id.id_indicator_one:
			listCCIWT.get(0).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(0, false);
			break;
		case R.id.id_indicator_two:
			listCCIWT.get(1).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(1, false);
			break;
		case R.id.id_indicator_three:
			listCCIWT.get(2).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(2, false);
			break;
		case R.id.id_indicator_four:
			listCCIWT.get(3).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(3, false);
			break;

		}
	}

	private void resetOtherTabs() {
		for (int i = 0; i < listCCIWT.size(); i++) {
			listCCIWT.get(i).setIconAlpha(0);
		}
	}

	/**
	 * OnPageChangeListener Begin
	 */

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub
		if (positionOffset > 0) {
			ChangeColorIconWithText left = listCCIWT.get(position);
			ChangeColorIconWithText right = listCCIWT.get(position + 1);
			left.setIconAlpha(1 - positionOffset);
			right.setIconAlpha(positionOffset);
		}
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * OnPageChangeListener End
	 */

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Object mHelperUtils;
				Toast.makeText(this, "再按一次退出程序", 2000).show();
				mExitTime = System.currentTimeMillis();

			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.mainmenu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_chart:
			Intent intent = new Intent(MainActivity.this, ChartActivity.class);
			startActivity(intent);
			break;
		case R.id.menu_exit:
			Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(intent1);
			this.finish();
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
