package maa.coronavirustracker.Models;


import com.google.gson.annotations.SerializedName;

public class Complete {
    @SerializedName("country")
    private String country;
    @SerializedName("recovered")
    private String recovered;
    @SerializedName("cases")
    private String cases;
    @SerializedName("critical")
    private String critical;
    @SerializedName("deaths")
    private String deaths;
    @SerializedName("todayCases")
    private String todayCases;
    @SerializedName("todayDeaths")
    private String todayDeaths;

    public Complete(String country, String recovered, String cases, String critical, String deaths, String todayCases, String todayDeaths) {
        this.country = country;
        this.recovered = recovered;
        this.cases = cases;
        this.critical = critical;
        this.deaths = deaths;
        this.todayCases = todayCases;
        this.todayDeaths = todayDeaths;
    }

    public String getCountry() {
        return country;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getCases() {
        return cases;
    }

    public String getCritical() {
        return critical;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

}
