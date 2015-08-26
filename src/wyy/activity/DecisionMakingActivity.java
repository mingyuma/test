package wyy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.imagemanager6.R;

public class DecisionMakingActivity extends Activity {

	private ListView listview;

	private String[] strs = { "Schatzker分型治疗方案决策支持" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decisionmaking);
		initViews();
		initDates();
		initEvents();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		listview = (ListView) this.findViewById(R.id.decision_listview);
	}

	private void initDates() {
		// TODO Auto-generated method stub

	}

	private void initEvents() {
		// TODO Auto-generated method stub
		listview.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strs));
		listview.setOnItemClickListener(new OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DecisionMakingActivity.this,
						SchatzkerActivity.class);
				startActivity(intent);
			}
		});
	}
}
