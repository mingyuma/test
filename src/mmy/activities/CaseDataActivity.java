package mmy.activities;

import wyy.fragment.CaseStudy1;
import wyy.fragment.CaseStudy2;
import wyy.fragment.CaseStudy3;
import wyy.fragment.CaseStudy4;
import wyy.widget.StepsView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.imagemanager6.R;

/**
 * 
 * 
 * 浣滆�咃細椹槑瀹� 鏃堕棿锛� 淇敼浜猴細榄忛洦鏃� 淇敼鏃堕棿锛�2015骞�7鏈�20鏃� 涓嬪崍6:57:58 淇敼澶囨敞锛�
 * 
 *
 */
public class CaseDataActivity extends FragmentActivity {

	// private TextView tvResult;
	// private LinearLayout llRealResult;
	//
	// private ScrollView sv;
	//
	// private int PreHeight;
	// private int inNowHeight;
	//
	// private ArrayList<String> arr = new ArrayList<String>();
	// private String[] arr1;
	// int chooseItem = 0;

	private StepsView mStepsView;
	// private ViewPager mViewPager;
	private final String[] labels = { "病史", "检查", "入院", "治疗" };
	// private List<Fragment> listFragment = new ArrayList<Fragment>();
	// private FragmentPagerAdapter mFPAdapter;
	private Fragment fragment1;
	private Fragment fragment3;
	private Fragment fragment2;
	private Fragment fragment4;
	private int index = 0;

	private Button pre;
	private Button next;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mmy_casedata_1);
		initView();
		initData();
		initEvent();

	}

	public void initView() {
		mStepsView = (StepsView) findViewById(R.id.stepsView);
		// mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		mStepsView
				.setLabels(labels)
				.setBarColorIndicator(
						this.getResources().getColor(
								R.color.material_blue_grey_800))
				.setProgressColorIndicator(
						this.getResources().getColor(R.color.orange))
				.setLabelColorIndicator(
						this.getResources().getColor(R.color.primary))
				.drawView();
		pre = (Button) findViewById(R.id.casestudy_pre);
		next = (Button) findViewById(R.id.casestudy_next);
		// sv = (ScrollView) findViewById(R.id.scroll);
		//
		// llRealResult = (LinearLayout)
		// findViewById(R.id.tv_casedata_real_result);
		// tvResult = (TextView) findViewById(R.id.tv_casedata_result);
		// tvResult.setClickable(true);
		// tvResult.setFocusable(true);
		// tvResult.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// AlertDialog.Builder builder = new AlertDialog.Builder(
		// CaseDataActivity.this)
		// .setTitle("鍒嗗瀷閫夋嫨")
		// .setIcon(R.drawable.logo7)
		// .setSingleChoiceItems(arr1, chooseItem,
		// new DialogInterface.OnClickListener() {
		//
		// public void onClick(DialogInterface dialog,
		// int which) {
		// // TODO Auto-generated method stub
		// String choose = arr1[which];
		// dialog.dismiss();
		// tvResult.setText("X鍏夌墖缁撴灉鎻愮ず锛氬乏鑳杩戠绮夌鎬ч鎶橈紝绱強鍏宠妭闈紝鎸夌収鑳骞冲彴楠ㄦ姌Schatzker鍒嗗瀷灞炰簬  "
		// + choose
		// + "  鍨嬨�俋鍏夌墖鍜�/鎴朇T鏁版嵁娴嬮噺缁撴灉鎻愮ず鍏宠妭闈㈠闄凤紴3 mm锛屽缓璁墜鏈不鐤椼��");
		//
		// llRealResult
		// .setVisibility(View.VISIBLE);
		//
		// chooseItem = which;
		// }
		// });
		// builder.create().show();
		// }
		//
		// });

	}

	public void initData() {
		fragment1 = new CaseStudy1();
		fragment2 = new CaseStudy2();
		fragment3 = new CaseStudy3();
		fragment4 = new CaseStudy4();

	}

	// mFPAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
	//
	// @Override
	// public int getCount() {
	// // TODO Auto-generated method stub
	// return listFragment.size();
	// }
	//
	// @Override
	// public Fragment getItem(int arg0) {
	// // TODO Auto-generated method stub
	// return listFragment.get(arg0);
	// }
	//
	// @Override
	// public void destroyItem(View container, int position, Object object) {
	// // TODO Auto-generated method stub
	// // super.destroyItem(container, position, object);
	// }
	//
	// };
	//
	// }
	//
	// private void initEvent() {
	// // TODO Auto-generated method stub
	// // mViewPager.setAdapter(mFPAdapter);
	// }
	//
	// @Override
	// public void onPageScrollStateChanged(int arg0) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onPageScrolled(int arg0, float arg1, int arg2) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onPageSelected(int arg0) {
	// // TODO Auto-generated method stub
	//
	// }
	private void initEvent() {
		// TODO Auto-generated method stub
		setSelect(index);
		pre.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setSelect(--index);
			}
		});
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setSelect(++index);
			}
		});
	}

	private void setSelect(int i) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		// transaction.setCustomAnimations(R.animator.fragment_left,
		// R.animator.fragment_right);
		switch (i) {
		case 0:
			transaction.replace(R.id.id_content, fragment1);
			break;
		case 1:
			transaction.replace(R.id.id_content, fragment2);
			break;
		case 2:
			transaction.replace(R.id.id_content, fragment3);
			break;
		case 3:
			transaction.replace(R.id.id_content, fragment4);
			break;
		default:
			break;
		}
		if (index >= 0 && index <= 3)
			mStepsView.setCompletedPosition(index).drawView();
		if (index == 0)
			pre.setVisibility(View.GONE);
		else
			pre.setVisibility(View.VISIBLE);
		if (index == 3) {
			next.setText("完成学习");
		} else if (index < 3) {
			next.setText("下一阶段");
		}
		if (index == 4)
			finish();
		transaction.commit();
	}
}
