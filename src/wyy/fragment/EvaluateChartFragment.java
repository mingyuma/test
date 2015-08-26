package wyy.fragment;

import java.util.ArrayList;

import wyy.widget.MyMarkerView;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imagemanager6.R;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class EvaluateChartFragment extends BaseFragment {

	private View view;
	private RadarChart mChart;
	private Typeface tf;
	private String[] mParties = new String[] { "耐心", "热心", "友善", "技能", "效率" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_chart3, null);
		initViews();
		initDates();
		initEvents();
		return view;
	}

	private void initViews() {
		// TODO Auto-generated method stub
		mChart = (RadarChart) view.findViewById(R.id.radarchart);
	}

	private void initDates() {
		// TODO Auto-generated method stub
		tf = Typeface.createFromAsset(getActivity().getAssets(),
				"OpenSans-Regular.ttf");

		mChart.setDescription("");
		mChart.setWebLineWidth(1.5f);
		mChart.setWebLineWidthInner(0.75f);
		mChart.setWebAlpha(100);
		// create a custom MarkerView (extend MarkerView) and specify the layout
		// to use for it
		MyMarkerView mv = new MyMarkerView(getActivity(),
				R.layout.custom_marker_view);

		// set the marker to the chart
		mChart.setMarkerView(mv);
		setData(100, mParties.length);

		XAxis xAxis = mChart.getXAxis();
		xAxis.setTypeface(tf);
		xAxis.setTextSize(9f);

		YAxis yAxis = mChart.getYAxis();
		yAxis.setTypeface(tf);
		yAxis.setLabelCount(5);
		yAxis.setTextSize(9f);
		yAxis.setStartAtZero(true);
	}

	private void initEvents() {
		// TODO Auto-generated method stub

	}

	public void setData(float mult, int cnt) {

		ArrayList<Entry> yVals1 = new ArrayList<Entry>();
		ArrayList<Entry> yVals2 = new ArrayList<Entry>();

		// IMPORTANT: In a PieChart, no values (Entry) should have the same
		// xIndex (even if from different DataSets), since no values can be
		// drawn above each other.
		for (int i = 0; i < cnt; i++) {
			yVals1.add(new Entry((float) (Math.random() / 2 * mult) + mult / 2,
					i));
		}

		for (int i = 0; i < cnt; i++) {
			yVals2.add(new Entry((float) (Math.random() / 2 * mult) + mult / 2,
					i));
		}

		ArrayList<String> xVals = new ArrayList<String>();

		for (int i = 0; i < cnt; i++)
			xVals.add(mParties[i % mParties.length]);

		RadarDataSet set1 = new RadarDataSet(yVals1, "平均评价");
		set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		set1.setDrawFilled(true);
		set1.setLineWidth(2f);
		// set1.setVisible(false);
		RadarDataSet set2 = new RadarDataSet(yVals2, "对你的评价");
		set2.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
		set2.setDrawFilled(true);
		set2.setLineWidth(2f);

		ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
		sets.add(set1);
		sets.add(set2);

		RadarData data = new RadarData(xVals, sets);
		data.setValueTypeface(tf);
		data.setValueTextSize(18f);
		data.setDrawValues(false);

		mChart.setData(data);

		mChart.invalidate();
	}
}
