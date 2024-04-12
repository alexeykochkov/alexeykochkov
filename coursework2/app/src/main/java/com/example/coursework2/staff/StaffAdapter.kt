package com.example.coursework2.staff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework2.databinding.FragmentCustomViewBinding
import com.example.coursework2.databinding.FragmentCustomViewSecondBinding
import com.example.coursework2.popular.PopularViewHolder

class StaffAdapter: RecyclerView.Adapter<StaffViewHolder>() {

    private var staffInfo = mutableListOf<StaffInfo>()
    var onClick: (StaffInfo) -> Unit = {

    }

    fun addPoular(staffInfo: List<StaffInfo>) {
        this.staffInfo.addAll(staffInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder {
        return StaffViewHolder(
            FragmentCustomViewSecondBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )
    }

    override fun getItemCount(): Int {
        return staffInfo.size
    }

    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
        holder.bind(staffInfo[position])
    }

}