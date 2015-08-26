package wyy.activity;

import java.util.ArrayList;
import java.util.List;

import wyy.fragment.EvaluateChartFragment;
import wyy.fragment.EveryMonthChartFrament;
import wyy.fragment.PeopleChartFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.imagemanager6.R;

public class ChartActivity extends FragmentActivity implements
		OnPageChangeListener, OnClickListener {

	private ViewPager mViewPager;
	private List<Fragment> listFragment = new ArrayList<Fragment>();
	private FragmentPagerAdapter mFPAdapter;
	private List<TextView> Indicators = new ArrayList<TextView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		initViews();
		initDates();
		initEvents();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager) this.findViewById(R.id.chart_viewpager);
	}

	private void initDates() {
		// TODO Auto-generated method stub
		EveryMonthChartFrament fragment1 = new EveryMonthChartFrament();
		PeopleChartFragment fragment2 = new PeopleChartFragment();
		EvaluateChartFragment fragment3 = new EvaluateChartFragment();
		listFragment.add(fragment1);
		listFragment.add(fragment2);
		listFragment.add(fragment3);

		TextView tv1 = (TextView) this.findViewById(R.id.indicator1);
		TextView tv2 = (TextView) this.findViewById(R.id.indicator2);
		TextView tv3 = (TextView) this.findViewById(R.id.indicator3);

		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);

		tv1.setTextColor(Color.parseColor("#D01839"));
		Indicators.add(tv1);
		Indicators.add(tv2);
		Indicators.add(tv3);
	}

	private void initEvents() {
		// TODO Auto-generated method stub
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

		mViewPager.setOnPageChangeListener(this);
		mViewPager.setAdapter(mFPAdapter);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		resetAllTextView();
		Indicators.get(arg0).setTextColor(Color.parseColor("#D01839"));
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		resetAllTextView();
		switch (v.getId()) {
		case R.id.indicator1:
			Indicators.get(0).setTextColor(Color.parseColor("#D01839"));
			mViewPager.setCurrentItem(0, false);
			break;
		case R.id.indicator2:
			Indicators.get(1).setTextColor(Color.parseColor("#D01839"));
			mViewPager.setCurrentItem(1, false);
			break;
		case R.id.indicator3:
			Indicators.get(2).setTextColor(Color.parseColor("#D01839"));
			mViewPager.setCurrentItem(2, false);
			break;
		default:
			break;
		}
	}

	private void resetAllTextView() {
		// TODO Auto-generated method stub
		for (TextView v : Indicators) {
			v.setTextColor(getResources().getColor(R.color.qianhuise));
		}
	}

}
