package com.tools.money.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ToCurrencyRate {

    @SerializedName("currency_name")
    @Expose
    private String currencyName;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("rate_for_amount")
    @Expose
    private String rateForAmount;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRateForAmount() {
        return rateForAmount;
    }

    public void setRateForAmount(String rateForAmount) {
        this.rateForAmount = rateForAmount;
    }


}
