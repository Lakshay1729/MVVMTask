package com.example.myapplication.ui.main.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Cupon {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

}