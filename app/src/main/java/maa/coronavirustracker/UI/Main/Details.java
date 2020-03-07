package maa.coronavirustracker.UI.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import maa.coronavirustracker.R;
import maa.coronavirustracker.databinding.ActivityDetailsBinding;

public class Details extends AppCompatActivity implements OnChartValueSelectedListener {

    ActivityDetailsBinding activityDetailsBinding;
    private BarChart chart;
    String Country, Cases, TodayCases, Deaths, TodayDeaths, Recovered, Critical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        chart = activityDetailsBinding.chart1;
        getAllData();
        setupChart();
        setAllData();
    }

    @SuppressLint("DefaultLocale")
    private void setAllData() {
        changeColorOfString(activityDetailsBinding.Country, "• Country : ", Country, getResources().getColor(R.color.TodayCasesColor));
        changeColorOfString(activityDetailsBinding.Cases, "• Cases : ", String.format("%,d", (Integer.parseInt(Cases))), getResources().getColor(R.color.CasesColor));
        changeColorOfString(activityDetailsBinding.TodayCases, "• Today cases : ", String.format("%,d", (Integer.parseInt(TodayCases))), getResources().getColor(R.color.TodayCasesColor));
        changeColorOfString(activityDetailsBinding.Deaths, "• Deaths : ", String.format("%,d", (Integer.parseInt(Deaths))), getResources().getColor(R.color.DeathsColor));
        changeColorOfString(activityDetailsBinding.TodayDeaths, "• Today deaths : ", String.format("%,d", (Integer.parseInt(TodayCases))), getResources().getColor(R.color.TodayDeathsColor));
        changeColorOfString(activityDetailsBinding.recovered, "• Recovered : ", String.format("%,d", (Integer.parseInt(Recovered))), getResources().getColor(R.color.RecoveredColor));
        changeColorOfString(activityDetailsBinding.Critical, "• Critical : ", String.format("%,d", (Integer.parseInt(Critical))), getResources().getColor(R.color.CriticalColor));

    }

    private void getAllData() {
        Country = (String) getIntent().getSerializableExtra("Country");
        Cases = (String) getIntent().getSerializableExtra("Cases");
        TodayCases = (String) getIntent().getSerializableExtra("TodayCases");
        Deaths = (String) getIntent().getSerializableExtra("Deaths");
        TodayDeaths = (String) getIntent().getSerializableExtra("TodayDeaths");
        Recovered = (String) getIntent().getSerializableExtra("Recovered");
        Critical = (String) getIntent().getSerializableExtra("Critical");
        setData(Integer.parseInt(Cases),
                Integer.parseInt(TodayCases),
                Integer.parseInt(Deaths),
                Integer.parseInt(TodayDeaths),
                Integer.parseInt(Recovered),
                Integer.parseInt(Critical));

    }

    public void setData(int Cases, int TodayCases, int Deaths, int TodayDeaths, int Recovered, int Critical) {
        String[] mParties = new String[]{"Cases", "Today Cases", "Deaths", "Today Deaths", "Recovered", "Critical"};
        BarDataSet set1;
        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(0, Cases, mParties[0]));
        values.add(new BarEntry(1, TodayCases, mParties[1]));
        values.add(new BarEntry(2, Deaths, mParties[2]));
        values.add(new BarEntry(3, TodayDeaths, mParties[3]));
        values.add(new BarEntry(4, Recovered, mParties[4]));
        values.add(new BarEntry(5, Critical, mParties[5]));

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Horizontal Chart");
            set1.setDrawIcons(false);
            ArrayList<Integer> colors = new ArrayList<Integer>();
            colors.add(getResources().getColor(R.color.CasesColor));
            colors.add(getResources().getColor(R.color.TodayCasesColor));
            colors.add(getResources().getColor(R.color.DeathsColor));
            colors.add(getResources().getColor(R.color.TodayDeathsColor));
            colors.add(getResources().getColor(R.color.RecoveredColor));
            colors.add(getResources().getColor(R.color.CriticalColor));
            set1.setColors(colors);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            chart.setData(data);
            chart.setNoDataTextColor(Color.BLACK);
            data.setValueTextColor(Color.BLACK);
            chart.getLegend().setTextColor(Color.BLACK);
        }

    }

    private void setupChart() {
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        chart.setMaxVisibleValueCount(60);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        XAxis xl = chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(10f);
        YAxis yl = chart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f);
        YAxis yr = chart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);
        chart.setFitBars(true);
        chart.animateY(2500);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    public void changeColorOfString(TextView textView, String label, String value, int ColorHex) {
        String LabelValue = label + value;
        Spannable spannable = new SpannableString(LabelValue);
        spannable.setSpan(new ForegroundColorSpan(ColorHex), label.length(), (label + value).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), label.length(), (label + value).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable, TextView.BufferType.SPANNABLE);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

}
