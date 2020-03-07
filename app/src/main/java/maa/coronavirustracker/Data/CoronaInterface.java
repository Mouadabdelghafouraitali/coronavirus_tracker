package maa.coronavirustracker.Data;

import java.util.List;

import maa.coronavirustracker.Models.Complete;
import maa.coronavirustracker.Models.Resume;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CoronaInterface {
    @GET("all")
    public Call<Resume> getCoronaVirusResumeInformation();

    @GET("countries")
    public Call<List<Complete>> getCoronaVirusCompleteInformation();
}
