package wyy.fragment;

import passenger.activity.FindDoctorActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.imagemanager6.R;

/**
 * 
 * 
 * 作者：wyy 时间：2015年7月21日 上午9:15:45 修改人：wyy 修改时间：2015年7月21日 上午9:15:45 修改备注：
 * 
 *
 */
@SuppressLint("InflateParams")
public class CaseStudy2 extends BaseFragment {
	private View view;

	private String[] array_weizhi = { "左", "右" };
	private String[] array_buwei = { "胫骨平台骨折", "肱骨近端骨折", "肱骨干骨折", "肱骨远端骨折",
			"尺桡骨近端骨折", "尺桡骨远端骨折", "股骨骨折", "胫腓骨骨折" };
	private String[] array_schatzker = { "Ⅰ型", "Ⅱ型", "Ⅲ型", "Ⅳ型", "Ⅴ型" };
	private String[] array_sanzhu = { "内", "外", "后", "内+外", "内+后", "外+后",
			"内+外+后" };

	private Spinner sp1;
	private Spinner sp2;
	private Spinner sp3;
	private Spinner sp4;

	private ImageView iv1;
	private ImageView iv2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_casestudy2, null);

		initViews();
		initDates();
		initEvents();

		return view;
	}

	private void initViews() {
		// TODO Auto-generated method stub
		sp1 = (Spinner) view.findViewById(R.id.casestudy_spinner1);
		sp2 = (Spinner) view.findViewById(R.id.casestudy_spinner2);
		sp3 = (Spinner) view.findViewById(R.id.casestudy_spinner3);
		sp4 = (Spinner) view.findViewById(R.id.casestudy_spinner4);

		iv1 = (ImageView) view.findViewById(R.id.x_guang);
		iv2 = (ImageView) view.findViewById(R.id.ct);
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
				android.R.layout.simple_spinner_item, array_weizhi);
		ada1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(ada1);
		ArrayAdapter<String> ada2 = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, array_buwei);
		ada2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp2.setAdapter(ada2);
		ArrayAdapter<String> ada3 = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, array_schatzker);
		ada3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp3.setAdapter(ada3);
		ArrayAdapter<String> ada4 = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, array_sanzhu);
		ada4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp4.setAdapter(ada4);
	}
}
