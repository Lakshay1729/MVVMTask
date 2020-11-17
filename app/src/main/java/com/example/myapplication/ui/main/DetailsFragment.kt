package com.example.myapplication.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.ui.main.Model.Result
import com.example.myapplication.ui.main.ViewModels.ApiViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import org.json.JSONObject


class DetailsFragment : Fragment() {


    private  var result=Result()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view=  inflater.inflate(R.layout.fragment_detail, container, false)
        val textView: TextView = view.findViewById<TextView>(R.id.description_body)
        val description_title=view.findViewById<TextView>(R.id.description_title)
        val imageView: ImageView =view.findViewById<ImageView>(R.id.details_imageview)
        ViewModelProvider.AndroidViewModelFactory(activity?.application!!)
            .create(ApiViewModel::class.java).apply {
                getResult(activity?.application!!)
            }.list.observe(viewLifecycleOwner, Observer(){
                Log.d("Observable",it.toString())
                result= it.result!!
                textView.text=result.descriptionBody
                description_title.text=result.descriptionTitle
                Glide.with(this).load(result.decriptionImage).into(imageView)

            })
        return view
    }

}