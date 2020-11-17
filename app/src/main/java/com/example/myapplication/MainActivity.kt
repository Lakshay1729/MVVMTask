package com.example.myapplication

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.ui.main.DetailsFragment
import com.example.myapplication.ui.main.Model.Example
import com.example.myapplication.ui.main.OffersFragment
import com.example.myapplication.ui.main.ViewModels.ApiViewModel
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.operators.observable.ObservableOnErrorNext
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var resultsave: JSONObject
    private lateinit var responseViewModel: ApiViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tabs) as TabLayout
        viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.setAdapter(MyAdapter(supportFragmentManager, lifecycle))
        TabLayoutMediator(tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy{tab, position ->
            when (position) {
                0 -> tab.text = "Offers"
                1 -> tab.text = "Details"
            }
        }).attach()
        responseViewModel = ViewModelProvider.AndroidViewModelFactory(application!!)
            .create(ApiViewModel::class.java).apply {
                getResult(application!!)
            }

    }
    private inner class MyAdapter(fm: FragmentManager?, lifecycle: Lifecycle) : FragmentStateAdapter(fm!!, lifecycle) {
        private val int_items = 2

        override fun createFragment(position: Int): Fragment {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = OffersFragment()
                1 -> fragment = DetailsFragment()
            }
            return fragment!!
        }

        override fun getItemCount(): Int {
            return int_items
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}