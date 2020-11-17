package com.example.myapplication.ui.main.ViewModels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RetryPolicy
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.example.myapplication.ui.main.Model.Example
//import com.example.myapplication.ui.main.Model.Response
import com.github.leonardoxh.livedatacalladapter.Resource
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.util.*
import io.reactivex.Observable
import org.json.JSONObject

class ApiViewModel(application: Application): AndroidViewModel(application) {

    var list=MutableLiveData<Example>()

    fun getResult(application: Application){

            val requestQueue = Volley.newRequestQueue(application)
            val url = "http://13.232.62.239/talzo/dummy/test/testing_data/"
            val request = JsonObjectRequest(Request.Method.GET, url, null, {

//            Log.d("Response", list.value.toString())
//                obj=it
//                this.example=
                list.value= Gson().fromJson(it.toString(),Example::class.java)
                Log.d("parsed",list.value.toString())
//            setValue(example)

            },{
            })

            requestQueue.add(request)


    }

}