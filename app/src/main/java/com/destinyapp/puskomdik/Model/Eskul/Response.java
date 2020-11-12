package com.destinyapp.puskomdik.Model.Eskul;

import androidx.annotation.Nullable;

import com.destinyapp.puskomdik.Model.DataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("statusCode")
    @Expose
    @Nullable
    public String statusCode;

    @SerializedName("statusMessage")
    @Expose
    @Nullable
    public String statusMessage;

    @SerializedName("data")
    @Nullable
    List<DataModel> data;
}
