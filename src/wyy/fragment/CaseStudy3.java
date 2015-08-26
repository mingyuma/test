package wyy.fragment;

import com.example.imagemanager6.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CaseStudy3 extends BaseFragment {
	private View view;
	private Spinner sp1;
	private Spinner sp2;
	private Spinner sp3;

	private String[] array_hulidengji = { "一级护理", "二级护理", "三级护理" };
	private String[] array_yinshi = { "普食", "流食", "半流食", "禁食水", "糖尿病饮食" };
	private String[] array_zhidong = { "短腿管型固定", "长腿管型固定", "短腿石膏托固定",
			"长腿石膏托固定", "皮牵引", "跟骨牵引", "胫骨结节牵引" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_casestudy3, null);
		initViews();
		initDates();
		initEvents();
		return view;
	}

	private void initViews() {
		// TODO Auto-generated method stub
		sp1 = (Spinner) view.findViewById(R.id.sp1);
		sp2 = (Spinner) view.findViewById(R.id.sp2);
		sp3 = (Spinner) view.findViewById(R.id.sp3);
	}

	private void initDates() {
		// TODO Auto-generated method stub
		setSpAdapter();
	}

	private void initEvents() {
		// TODO Auto-generated method stub

	}

	private void setSpAdapter() {
		// TODO Auto-generated method stub
		ArrayAdapter<String> ada1 = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, array_hulidengji);
		ada1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(ada1);
		ArrayAdapter<String> ada2 = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, array_yinshi);
		ada2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp2.setAdapter(ada2);
		ArrayAdapter<String> ada3 = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, array_zhidong);
		ada3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp3.setAdapter(ada3);
	}
}
