package wyy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.imagemanager6.R;

public class FromPeopleList extends Activity {

	private ListView listview;
	private View noDataLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fromlist);
		initViews();
		initDates();
		initEvents();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		listview = (ListView) this.findViewById(R.id.from_list);
		noDataLayout = this.findViewById(R.id.no_data_layout);

	}

	private void initDates() {
		// TODO Auto-generated method stub

	}

	private void initEvents() {
		// TODO Auto-generated method stub

	}
}
