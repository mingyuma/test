package wyy.fragment;

import com.example.imagemanager6.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CaseStudy4 extends BaseFragment {

	private View view;
	private Spinner sp1;
	private Spinner sp2;
	private Spinner sp3;
	private Spinner sp4;
	private Spinner sp5;

	private String[] array_mazhui = { "全身麻醉", "局部麻醉", "椎管内麻醉" };
	private String[] array_tiwei = { "平卧位", "侧卧位" };
	private String[] array_qiekou = { "内侧切口", "外侧切口" };
	private String[] array_kangshengsu = { "一代头孢菌素", "二代头孢菌素", "罗红霉素", "青霉素" };
	private String[] array_cailiao = { "支撑钢板", "支撑钢钉" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_casestudy4, null);
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
		sp4 = (Spinner) view.findViewById(R.id.sp4);
		sp5 = (Spinner) view.findViewById(R.id.sp5);
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
				android.R.layout.simple_spinner_item, array_mazhui);
		ada1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(ada1);
		ArrayAdapter<String> ada2 = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, array_tiwei);
		ada2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp2.setAdapter(ada2);
		ArrayAdapter<String> ada3 = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, array_qiekou);
		ada3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp3.setAdapter(ada3);
		ArrayAdapter<String> ada4 = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, array_kangshengsu);
		ada4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp4.setAdapter(ada4);
		ArrayAdapter<String> ada5 = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, array_cailiao);
		ada5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp5.setAdapter(ada5);
	}
}
