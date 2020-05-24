package com.tools.money.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ConvertRateResponse {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("base_currency_code")
    @Expose
    private String baseCurrencyCode;
    @SerializedName("base_currency_name")
    @Expose
    private String baseCurrencyName;
    @SerializedName("rates")
    @Expose
    private Map<String, ToCurrencyRate> rates;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBaseCurrencyCode() {
        return baseCurrencyCode;
    }

    public void setBaseCurrencyCode(String baseCurrencyCode) {
        this.baseCurrencyCode = baseCurrencyCode;
    }

    public String getBaseCurrencyName() {
        return baseCurrencyName;
    }

    public void setBaseCurrencyName(String baseCurrencyName) {
        this.baseCurrencyName = baseCurrencyName;
    }

    public Map<String, ToCurrencyRate> getRates() {
        return rates;
    }

    public void setRates(Map<String, ToCurrencyRate> rates) {
        this.rates = rates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

}
