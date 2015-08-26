package wyy.fragment;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imagemanager6.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;
import com.github.mikephil.charting.utils.ValueFormatter;

public class PeopleChartFragment extends BaseFragment implements
		OnChartValueSelectedListener {

	private View view;
	private PieChart mChart;
	protected HorizontalBarChart mHChart;
	private Typeface tf;
	protected String[] mParties = new String[] { "ƒ–", "≈Æ" };
	protected String[] mAges = new String[] { "18ÀÍ-30ÀÍ", "30ÀÍ-40ÀÍ", "40ÀÍ-50ÀÍ",
			"50ÀÍ-60ÀÍ", "60ÀÍ“‘…œ" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_chart2, null);
		initViews();
		initDates();
		initEvents();
		return view;
	}

	private void initViews() {
		// TODO Auto-generated method stub
		mChart = (PieChart) view.findViewById(R.id.piechart);
		mHChart = (HorizontalBarChart) view
				.findViewById(R.id.horizontabarchart);
	}

	private void initDates() {
		// TODO Auto-generated method stub
		PieInitDates();
		mHChart.setOnChartValueSelectedListener(this);
		// mChart.setHighlightEnabled(false);

		mHChart.setDrawBarShadow(false);
		mHChart.setClickable(false);
		mHChart.setDrawValueAboveBar(true);

		mHChart.setDescription("");

		// if more than 60 entries are displayed in the chart, no values will be
		// drawn
		mHChart.setMaxVisibleValueCount(60);
		mHChart.setScaleEnabled(false);
		// scaling can now only be done on x- and y-axis separately
		mHChart.setPinchZoom(false);

		// draw shadows for each bar that show the maximum value
		// mChart.setDrawBarShadow(true);

		// mChart.setDrawXLabels(false);

		mHChart.setDrawGridBackground(false);
		mHChart.animateY(2500);
		setHData(mAges.length, 10);
	}

	private void PieInitDates() {
		mChart.setUsePercentValues(true);

		// change the color of the center-hole
		// mChart.setHoleColor(Color.rgb(235, 235, 235));
		mChart.setHoleColorTransparent(true);

		tf = Typeface.createFromAsset(getActivity().getAssets(),
				"OpenSans-Regular.ttf");
		mChart.setCenterTextTypeface(Typeface.createFromAsset(getActivity()
				.getAssets(), "OpenSans-Light.ttf"));

		mChart.setHoleRadius(60f);

		mChart.setDescription("");

		mChart.setDrawCenterText(true);

		mChart.setDrawHoleEnabled(false);

		mChart.setRotationAngle(0);
		// enable rotation of the chart by touch
		mChart.setRotationEnabled(true);

		// mChart.setDrawUnitsInChart(true);
		// add a selection listener
		mChart.setOnChartValueSelectedListener(this);
		// mChart.setTouchEnabled(false);
		// mChart.setCenterText("MPAndroidChart\nby Philipp Jahoda");
		setData(1, 100);

		mChart.animateXY(1500, 1500);
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

		float mult = range;

		ArrayList<Entry> yVals1 = new ArrayList<Entry>();

		// IMPORTANT: In a PieChart, no values (Entry) should have the same
		// xIndex (even if from different DataSets), since no values can be
		// drawn above each other.
		for (int i = 0; i < count + 1; i++) {
			yVals1.add(new Entry((float) (Math.random() * mult) + mult / 5, i));
		}

		ArrayList<String> xVals = new ArrayList<String>();

		for (int i = 0; i < count + 1; i++)
			xVals.add(mParties[i % mParties.length]);

		PieDataSet dataSet = new PieDataSet(yVals1, " ");
		dataSet.setSliceSpace(3f);

		// add a lot of colors

		ArrayList<Integer> colors = new ArrayList<Integer>();

		for (int c : ColorTemplate.VORDIPLOM_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.JOYFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.COLORFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.LIBERTY_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.PASTEL_COLORS)
			colors.add(c);

		colors.add(ColorTemplate.getHoloBlue());

		dataSet.setColors(colors);

		PieData data = new PieData(xVals, dataSet);
		data.setValueFormatter(new PercentFormatter());
		data.setValueTextSize(16f);
		data.setValueTextColor(Color.BLACK);
		data.setValueTypeface(tf);
		mChart.setData(data);

		// undo all highlights
		mChart.highlightValues(null);

		mChart.invalidate();
	}

	private void setHData(int count, float range) {

		ArrayList<String> xVals = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			xVals.add(mAges[i % 12]);
		}

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
		ValueFormatter myformatter = new ValueFormatter() {

			private DecimalFormat mFormat;

			@Override
			public String getFormattedValue(float value) {
				mFormat = new DecimalFormat("###,###,###,##");
				return mFormat.format(value);
			}
		};
		for (int i = 0; i < count; i++) {
			float mult = (range + 1);
			int val = (int) (Math.random() * mult);
			yVals1.add(new BarEntry(val, i));
		}

		BarDataSet set1 = new BarDataSet(yVals1, "ƒÍ¡‰∂Œ»À ˝");
		set1.setBarSpacePercent(35f);
		set1.setValueFormatter(myformatter);
		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		dataSets.add(set1);

		BarData data = new BarData(xVals, dataSets);
		data.setValueTextSize(10f);
		data.setValueTypeface(tf);

		mHChart.setData(data);
	}
}
