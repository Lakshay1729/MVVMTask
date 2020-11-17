package com.example.myapplication.ui.main.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Example {
    @SerializedName("statusCode")
    @Expose
    var statusCode: Int? = null

    @SerializedName("APICODERESULT")
    @Expose
    var aPICODERESULT: String? = null

    @SerializedName("result")
    @Expose
    var result: Result? = null

}