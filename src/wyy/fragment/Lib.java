package wyy.fragment;

import mmy.activities.CaseDataActivity;
import wyy.activity.DecisionMakingActivity;
import wyy.activity.LibTempActivity;
import wyy.activity.ThreeDimensionalModelActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.imagemanager6.R;

public class Lib extends BaseFragment implements OnClickListener {

	private View layout1;
	private View layout2;
	private View layout3;
	private View layout4;
	private View layout5;
	private View layout6;
	private View layout7;
	private View layout8;
	private TextView toptext;

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_lib, null, false);
		initsViews();
		initsDates();
		initsEvents();
		return view;
	}

	private void initsViews() {
		// TODO Auto-generated method stub
		layout1 = view.findViewById(R.id.lib_1);
		layout2 = view.findViewById(R.id.lib_2);
		layout3 = view.findViewById(R.id.lib_3);
		layout4 = view.findViewById(R.id.lib_4);
		layout5 = view.findViewById(R.id.lib_5);
		layout6 = view.findViewById(R.id.lib_6);
		layout7 = view.findViewById(R.id.lib_7);
		layout8 = view.findViewById(R.id.lib_8);
		toptext = (TextView) view.findViewById(R.id.lib_toptext);
	}

	private void initsDates() {
		// TODO Auto-generated method stub
		SharedPreferences settings = getActivity().getSharedPreferences(
				"setting", 0);
		int name = settings.getInt("name", 0);
		if (name == 0) {
			toptext.setText("实习医师  XXX 你好，根据你的级别，我们推荐如下内容：");
		} else if (name == 1) {
			toptext.setText("中级医师  XXX 你好，根据你的级别，我们推荐如下内容：");
			layout7.setVisibility(View.GONE);
		} else if (name == 2) {
			toptext.setText("专家医师  XXX 你好，根据你的级别，我们推荐如下内容：");
			layout7.setVisibility(View.GONE);
			layout8.setVisibility(View.GONE);
		}
	}

	private void initsEvents() {
		// TODO Auto-generated method stub
		layout1.setOnClickListener(this);
		layout2.setOnClickListener(this);
		layout3.setOnClickListener(this);
		layout4.setOnClickListener(this);
		layout5.setOnClickListener(this);
		layout6.setOnClickListener(this);
		layout7.setOnClickListener(this);
		layout8.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.lib_1:
			intent = new Intent(getActivity(),
					ThreeDimensionalModelActivity.class);
			startActivity(intent);
			break;
		case R.id.lib_2:
			intent = new Intent(getActivity(), LibTempActivity.class);
			intent.putExtra("value", R.array.hangyexinwen);
			startActivity(intent);
			break;
		case R.id.lib_3:
			intent = new Intent(getActivity(), LibTempActivity.class);
			intent.putExtra("value", R.array.zhuanjiagongshi);
			startActivity(intent);
			break;
		case R.id.lib_4:
			intent = new Intent(getActivity(), LibTempActivity.class);
			startActivity(intent);
			break;
		case R.id.lib_5:
			intent = new Intent(getActivity(), LibTempActivity.class);
			startActivity(intent);
			break;
		case R.id.lib_6:
			intent = new Intent(getActivity(), LibTempActivity.class);
			intent.putExtra("value", R.array.linchuanglujing);
			startActivity(intent);
			break;
		case R.id.lib_7:
			intent = new Intent(getActivity(), CaseDataActivity.class);
			startActivity(intent);
			break;
		case R.id.lib_8:
			intent = new Intent(getActivity(), DecisionMakingActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
