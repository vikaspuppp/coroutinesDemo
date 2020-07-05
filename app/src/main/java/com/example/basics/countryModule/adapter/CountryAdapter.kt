package com.example.basics.countryModule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.R
import com.example.basics.countryModule.model.CountryModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(var countryList: ArrayList<CountryModel>) :
    RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    class CountryHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun update(countryModel: CountryModel) {
            itemView.cell_tv_country_name.text = countryModel.countryName
            itemView.cell_tv_capital.text = countryModel.capital
            if (countryModel.flag.isNotEmpty())
                Picasso.get().load(countryModel.flag).into(itemView.cell_iv_country_flag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder =
        CountryHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        )

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.update(countryList[position])
    }

    fun updateList(list: ArrayList<CountryModel>) {
        countryList.clear()
        countryList.addAll(list)
        notifyDataSetChanged()
    }
}