package com.example.myapplication.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.ui.main.Model.Cupon
import com.example.myapplication.ui.main.ViewModels.ApiViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class OffersFragment : Fragment() {
    private var button: Button?=null
    private var image2: ImageView?=null
    private var image1: ImageView?=null
    private lateinit var flipper: ViewFlipper


public var array=ArrayList<Cupon>()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_offers, container, false)
        val recycler_view=root.findViewById<RecyclerView>(R.id.recycler_view)
        recycler_view.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        flipper = root.findViewById<ViewFlipper>(R.id.view_flipper);
        image1=root.findViewById<ImageView>(R.id.first_banner)
        image2=root.findViewById<ImageView>(R.id.first_banner)
        button=root.findViewById<Button>(R.id.button_map)
        flipper.inAnimation=AnimationUtils.loadAnimation(context,R.anim.slide_left)
        flipper.outAnimation=AnimationUtils.loadAnimation(context,R.anim.slide_right)

        flipper.isAutoStart=true
        flipper.flipInterval=100
        flipper.startFlipping()
//
         ViewModelProvider.AndroidViewModelFactory(activity?.application!!)
            .create(ApiViewModel::class.java).apply {
                getResult(activity?.application!!)
            }.list.observe(viewLifecycleOwner,Observer(){

                 val result=it.result

                 Glide.with(this).load(it.result?.banner?.get(0)).into(image1!!)
                 Glide.with(this).load(it.result?.banner?.get(1)).into(image2!!)
                 button!!.setOnClickListener(View.OnClickListener {
                     Toast.makeText(activity,"Latitude :${result?.latitudes}\nLongitude :${result?.longitude}",Toast.LENGTH_LONG).show()

//
                 })
                Log.d("Array",result?.banner.toString())
                recycler_view.adapter=MyAdapter(result?.cupons)
            })

        return root
    }


    class MyAdapter(jsonArray: List<Cupon>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  val array=jsonArray

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val v:View=LayoutInflater.from(parent.context).inflate(R.layout.cupons_layout,parent,false)
            return MyViewHolder(v)
        }

        override fun getItemCount(): Int {
           return array?.size!!
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
           MyViewHolder(holder.itemView).bind(array?.get(position))
        }

        class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
            fun bind(array: Cupon?) {
               itemView.findViewById<TextView>(R.id.title1).text=array?.title

                itemView.findViewById<TextView>(R.id.description1).text=array?.description

                itemView.findViewById<TextView>(R.id.price1).text=array?.price
            }

        }
    }
}


