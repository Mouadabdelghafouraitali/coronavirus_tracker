package maa.coronavirustracker.UI.Main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import maa.coronavirustracker.Data.CoronaClient;
import maa.coronavirustracker.Models.Complete;
import maa.coronavirustracker.Models.Resume;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoronaViewModel extends ViewModel {

    MutableLiveData<Resume> mutableResumeLiveData = new MutableLiveData<>();
    MutableLiveData<List<Complete>> mutableCompleteLiveData = new MutableLiveData<>();

    public MutableLiveData<Resume> getMutableResumeLiveData() {
        return mutableResumeLiveData;
    }

    public void getCoronaResumeInformation() {
        CoronaClient.getCoronaClient().getCoronaResumeInformation().enqueue(new Callback<Resume>() {
            @Override
            public void onResponse(@NonNull Call<Resume> call, @NonNull Response<Resume> response) {
                if (response.body() != null) {
                    mutableResumeLiveData.setValue(new Resume(response.body().getRecovered(), response.body().getCases(), response.body().getDeaths()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Resume> call, @NonNull Throwable t) {

            }
        });

    }

    public void getCoronaCompleteInformation() {
        ArrayList<Complete> mData = new ArrayList<>();
        CoronaClient.getCoronaClient().getCoronaCompleteInformation().enqueue(new Callback<List<Complete>>() {
            @Override
            public void onResponse(@NonNull Call<List<Complete>> call, @NonNull Response<List<Complete>> response) {
                if (response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        mData.add(new Complete(
                                response.body().get(i).getCountry(),
                                response.body().get(i).getRecovered(),
                                response.body().get(i).getCases(),
                                response.body().get(i).getCritical(),
                                response.body().get(i).getDeaths(),
                                response.body().get(i).getTodayCases(),
                                response.body().get(i).getTodayDeaths()
                        ));

                    }
                    mutableCompleteLiveData.setValue(mData);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Complete>> call, @NonNull Throwable t) {

            }
        });
    }

}
