package wyy.fragment;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.imagemanager6.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.ValueFormatter;

import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EveryMonthChartFrament extends BaseFragment implements
		OnChartValueSelectedListener {

	private BarChart mChart;
	private Typeface mTf;
	private String[] mMonths = new String[] { "一月", "二月", "三月", "四月", "五月",
			"六月", "七月", "八月", "九月", "十月", "十一月", "十二月" };
	private static final int[] VORDIPLOM_COLORS = {
	// Color.rgb(192, 255, 140),
	// Color.rgb(255, 247, 140),
	// Color.rgb(255, 208, 140),
	Color.rgb(140, 234, 255),
	// Color.rgb(255, 140, 157)
	};
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_chart1, null);
		initViews();
		initDates();
		initEvents();
		return view;
	}

	private void initViews() {
		// TODO Auto-generated method stub
		mChart = (BarChart) view.findViewById(R.id.barchart);

	}

	private void initDates() {
		// TODO Auto-generated method stub
		mChart.setNoDataText("还没有数据");
		mChart.setOnChartValueSelectedListener(this);

		mChart.setDrawBarShadow(false);
		mChart.setDrawValueAboveBar(true);
		mChart.setDescription("");
		mChart.setScaleEnabled(false);
		// if more than 60 entries are displayed in the chart, no values will be
		// drawn
		mChart.setMaxVisibleValueCount(60);
		// scaling can now only be done on x- and y-axis separately
		mChart.setPinchZoom(false);

		// draw shadows for each bar that show the maximum value
		// mChart.setDrawBarShadow(true);

		// mChart.setDrawXLabels(false);

		mChart.setDrawGridBackground(false);
		// mChart.setDrawYLabels(false);

		mTf = Typeface.createFromAsset(this.getActivity().getAssets(),
				"OpenSans-Regular.ttf");

		XAxis xAxis = mChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setTypeface(mTf);
		xAxis.setDrawGridLines(false);
		xAxis.setSpaceBetweenLabels(2);
		xAxis.setTextSize(6.0f);

		// ValueFormatter custom = new MyValueFormatter();

		YAxis leftAxis = mChart.getAxisLeft();
		leftAxis.setTypeface(mTf);
		leftAxis.setLabelCount(8);
		// leftAxis.setValueFormatter(custom);
		leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
		leftAxis.setSpaceTop(15f);

		YAxis rightAxis = mChart.getAxisRight();
		rightAxis.setDrawGridLines(false);
		rightAxis.setTypeface(mTf);
		rightAxis.setLabelCount(8);
		// rightAxis.setValueFormatter(custom);
		rightAxis.setSpaceTop(15f);

		// Legend l = mChart.getLegend();
		// l.setPosition(LegendPosition.BELOW_CHART_LEFT);
		// l.setForm(LegendForm.SQUARE);
		// l.setFormSize(9f);
		// l.setTextSize(11f);
		// l.setXEntrySpace(4f);

		setData(12, 10);
	}

	private void initEvents() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNothingSelected() {
		// TODO Auto-generated method stub

	}

	private void setData(int count, float range) {

		ArrayList<String> xVals = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			xVals.add(mMonths[i % 12]);
		}

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
		ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();

		for (int i = 0; i < count; i++) {
			float mult = (range + 1);
			int val = (int) (Math.random() * mult);
			yVals1.add(new BarEntry(val, i));
		}
		for (int i = 0; i < count; i++) {
			float mult = (range + 1);
			int val = (int) (Math.random() * mult);
			yVals2.add(new BarEntry(val, i));
		}
		ValueFormatter myformatter = new ValueFormatter() {

			private DecimalFormat mFormat;

			@Override
			public String getFormattedValue(float value) {
				mFormat = new DecimalFormat("###,###,###,##");
				return mFormat.format(value);
			}
		};
		BarDataSet set1 = new BarDataSet(yVals1, "您的人数");
		set1.setBarSpacePercent(0f);
		set1.setValueFormatter(myformatter);
		set1.setColors(VORDIPLOM_COLORS);

		BarDataSet set2 = new BarDataSet(yVals2, "平均值");
		set2.setBarSpacePercent(0f);
		set2.setColor(Color.rgb(255, 208, 140));
		set2.setValueFormatter(myformatter);

		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		dataSets.add(set1);
		dataSets.add(set2);

		BarData data = new BarData(xVals, dataSets);
		// data.setValueFormatter(new MyValueFormatter());
		data.setValueTextSize(10f);
		data.setValueTypeface(mTf);
		mChart.setData(data);
	}

}
