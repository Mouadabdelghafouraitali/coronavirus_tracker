package maa.coronavirustracker.Data;

import java.util.List;

import maa.coronavirustracker.Constants.Constants;
import maa.coronavirustracker.Models.Complete;
import maa.coronavirustracker.Models.Resume;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoronaClient {

    private CoronaInterface mCoronaInterface;
    private static CoronaClient mCoronaClient;

    public CoronaClient() {
        Retrofit mRetrofit = new Retrofit.Builder().baseUrl(Constants.BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        mCoronaInterface = mRetrofit.create(CoronaInterface.class);
    }

    public static CoronaClient getCoronaClient() {
        if (mCoronaClient != null)
            return mCoronaClient;
        else {
            mCoronaClient = new CoronaClient();
            return mCoronaClient;
        }
    }

    public Call<List<Complete>> getCoronaCompleteInformation() {
        return mCoronaInterface.getCoronaVirusCompleteInformation();
    }

    public Call<Resume> getCoronaResumeInformation() {
        return mCoronaInterface.getCoronaVirusResumeInformation();
    }

}
