package com.example.basics.countryModule.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.loginModule.view.LoginActivity
import com.example.basics.R
import com.example.basics.countryModule.adapter.CountryAdapter
import com.example.basics.countryModule.viewModel.CountryListViewModel
import kotlinx.android.synthetic.main.activity_country_list.*

class CountryListActivity : AppCompatActivity() {
    private val countryViewModel: CountryListViewModel by lazy {
        ViewModelProvider(this).get(CountryListViewModel::class.java)
    }
    private val countryAdapter: CountryAdapter by lazy { CountryAdapter(arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)
        rv_country_list.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = countryAdapter
        }
        observe()
        countryViewModel.refresh()
        btn_cl_next.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun observe() {
        countryViewModel.countryList.observe(this,
            Observer { country ->
                country?.let {
                    pb_country_list.visibility = View.GONE
                    rv_country_list.visibility = View.VISIBLE
                    countryAdapter.updateList(it)
                }
            })

        countryViewModel.countryError.observe(this, Observer { message ->
            pb_country_list.visibility = View.GONE
            tv_country_error.visibility = View.VISIBLE
            tv_country_error.text = message
        })
    }
}