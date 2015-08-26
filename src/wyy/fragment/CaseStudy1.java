package wyy.fragment;

import com.example.imagemanager6.R;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * 
 * 作者： 魏雨旸 时间：2015年7月20日 下午10:24:24 修改人：魏雨旸 修改时间：2015年7月20日 下午10:24:24 修改备注：
 * 
 *
 */
public class CaseStudy1 extends BaseFragment {

	private View view;
	private FragmentPagerAdapter mFPAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_casestudy1, null);
		initViews();
		initDates();
		initEvents();
		return view;
	}

	private void initViews() {
		// TODO Auto-generated method stub

	}

	private void initDates() {
		// TODO Auto-generated method stub

	}

	private void initEvents() {
		// TODO Auto-generated method stub

	}
}
