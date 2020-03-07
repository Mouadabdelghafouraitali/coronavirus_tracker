package maa.coronavirustracker.Models;

import com.google.gson.annotations.SerializedName;

public class Resume {
    @SerializedName("recovered")
    private String recovered;
    @SerializedName("cases")
    private String cases;
    @SerializedName("deaths")
    private String deaths;


    public Resume(String recovered, String cases, String deaths) {
        this.recovered = recovered;
        this.cases = cases;
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getCases() {
        return cases;
    }

    public String getDeaths() {
        return deaths;
    }


}
