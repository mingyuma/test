package wyy.activity;

import wyy.utils.Constants;
import wyy.utils.RulesEngine;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.imagemanager6.R;

public class SchatzkerActivity extends Activity {
	private Spinner taxian;
	private Spinner fenli;
	private Spinner type;
	private EditText ed_zhiliaofangan;
	private TextView tv_tishi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schatzker);
		initViews();
		initDates();
		initEvents();
		ObserveView();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		taxian = (Spinner) this.findViewById(R.id.sp_taxian);
		fenli = (Spinner) this.findViewById(R.id.sp_fenli);
		type = (Spinner) this.findViewById(R.id.sp_type);
		ed_zhiliaofangan = (EditText) this
				.findViewById(R.id.schatzker_zhiliaofangan);
		tv_tishi = (TextView) this.findViewById(R.id.schatzker_tishi);
	}

	private void initDates() {
		// TODO Auto-generated method stub
		setTaxianAdapter();
		setTypeAdapter();
		setFenliAdapter();
		OnItemSelectedListener otsl = new Spinner.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ObserveView();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		};

		type.setOnItemSelectedListener(otsl);
		fenli.setOnItemSelectedListener(otsl);
		taxian.setOnItemSelectedListener(otsl);
	}

	private void initEvents() {
		// TODO Auto-generated method stub

	}

	private void setTaxianAdapter() {
		// TODO Auto-generated method stub
		ArrayAdapter<String> ada = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Constants.itemts3mm);
		ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		taxian.setAdapter(ada);
	}

	private void setTypeAdapter() {
		ArrayAdapter<String> ada = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Constants.itemsSchatzker);
		ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		type.setAdapter(ada);
	}

	private void setFenliAdapter() {
		// TODO Auto-generated method stub
		ArrayAdapter<String> ada = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Constants.itemts3mm);
		ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		fenli.setAdapter(ada);
	}

	private void ObserveView() {
		// TODO Auto-generated method stub
		String sType = Constants.itemsSchatzker[type.getSelectedItemPosition()];
		String sFenli = Constants.itemts3mm[fenli.getSelectedItemPosition()];
		String sTaxian = Constants.itemts3mm[taxian.getSelectedItemPosition()];
		String parse = RulesEngine.Parse(sType, sFenli, sTaxian);
		if (parse.equals("no")) {
			ed_zhiliaofangan.setVisibility(View.GONE);
			tv_tishi.setVisibility(View.VISIBLE);
		} else {
			ed_zhiliaofangan.setVisibility(View.VISIBLE);
			ed_zhiliaofangan.setText(parse);
			tv_tishi.setVisibility(View.GONE);
		}
		if ((!sType.equals("Schatzker¢Ò–Õ")) && (!sType.equals("Schatzker¢Ú–Õ"))) {
			View view = (View) taxian.getParent();
			view.setVisibility(View.GONE);
			
		} else {
			View view = (View) taxian.getParent();
			view.setVisibility(View.VISIBLE);
		}

	}

}
