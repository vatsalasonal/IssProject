package com.issapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.issapp.R
import com.issapp.model.IssPeopleDate

open class AstronautIssLocationTimeAdapter(
    private val context: Context,
    private var list: ArrayList<IssPeopleDate>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView = itemView.findViewById(R.id.textViewName)
        var textViewCrafts: TextView = itemView.findViewById(R.id.textViewCrafts)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            textViewName.text = recyclerViewModel.name
            textViewCrafts.text = recyclerViewModel.craft
        }
    }

    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvLocation: TextView = itemView.findViewById(R.id.tvLocation)
        var tvTimes: TextView = itemView.findViewById(R.id.tvTimes)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            tvLocation.text = recyclerViewModel.name
            tvTimes.text = recyclerViewModel.craft
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return View1ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.recyclerview_astronauts_list, parent, false)
            )
        }
        return View2ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recyclerview_location_times, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType === VIEW_TYPE_ONE) {
            (holder as View1ViewHolder).bind(position)
        } else {
            (holder as View2ViewHolder).bind(position)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }

    override fun getItemCount(): Int {
        return list.size
    }
}