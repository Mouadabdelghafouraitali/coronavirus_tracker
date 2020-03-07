package maa.coronavirustracker.UI.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import maa.coronavirustracker.Adapter.CoronaAdapter;
import maa.coronavirustracker.Models.Complete;
import maa.coronavirustracker.R;
import maa.coronavirustracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    RecyclerView.LayoutManager mLayout;
    CoronaAdapter mCoronaAdapter;
    private ArrayList<Complete> mDataList = new ArrayList<>();
    CoronaViewModel mCoronaViewModel;
    ActivityMainBinding mainBinding;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCoronaViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CoronaViewModel.class);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupRecyclerView(mainBinding.RecyclerViewData);
        mCoronaViewModel.getCoronaResumeInformation();
        mCoronaViewModel.mutableResumeLiveData.observe(this, resume -> {
            displayViews(true);
            mainBinding.Confirmed.setText(String.format("%,d", (Integer.parseInt(resume.getCases()))));
            mainBinding.Recovered.setText(String.format("%,d", (Integer.parseInt(resume.getRecovered()))));
            mainBinding.Deaths.setText(String.format("%,d", (Integer.parseInt(resume.getDeaths()))));
        });
        mCoronaViewModel.getCoronaCompleteInformation();
        mCoronaViewModel.mutableCompleteLiveData.observe(this, completes -> {
            mDataList = new ArrayList<>(completes);
            mCoronaAdapter = new CoronaAdapter(getApplicationContext(), mDataList, position -> sendToDetails(mDataList.get(position).getCountry(),
                    mDataList.get(position).getCases(),
                    mDataList.get(position).getTodayCases(),
                    mDataList.get(position).getDeaths(),
                    mDataList.get(position).getTodayDeaths(),
                    mDataList.get(position).getRecovered(),
                    mDataList.get(position).getCritical()
            ));
            mainBinding.RecyclerViewData.setAdapter(mCoronaAdapter);
        });
    }


    private void displayViews(boolean visibility) {
        if (visibility == true) {
            mainBinding.Status.setVisibility(View.VISIBLE);
            mainBinding.Loading.setVisibility(View.GONE);
        } else {
            mainBinding.Status.setVisibility(View.GONE);
            mainBinding.Loading.setVisibility(View.VISIBLE);
        }
    }


    public void setupRecyclerView(RecyclerView mRecyclerView) {
        mLayout = new GridLayoutManager(getApplicationContext(), 1);
        mRecyclerView.setLayoutManager(mLayout);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    public void sendToDetails(String country, String cases, String todayCases, String deaths, String todayDeaths, String recovered, String critical) {

        Intent Go = new Intent(MainActivity.this, Details.class);
        Go.putExtra("Country", country);
        Go.putExtra("Cases", cases);
        Go.putExtra("TodayCases", todayCases);
        Go.putExtra("Deaths", deaths);
        Go.putExtra("TodayDeaths", todayDeaths);
        Go.putExtra("Recovered", recovered);
        Go.putExtra("Critical", critical);
        startActivity(Go);
    }


}
