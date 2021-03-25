package com.exam.janus.ui.home

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exam.janus.R
import com.exam.janus.databinding.ItemListEmployeeBinding
import com.exam.janus.service.EmployeesResponse

class EmployeesAdapter(private val context: Context, val listEmployee: ArrayList<EmployeesResponse>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickItem: ClickItemList? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemListEmployeeBinding>(LayoutInflater.from(context),
        R.layout.item_list_employee, parent, false)
        return ItemEmployeeHolder(binding)
    }

    override fun getItemCount(): Int {
        return listEmployee.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemEmployeeHolder
        holder.binding.tvEmployeeId.text = listEmployee[position].id.toString()
        holder.binding.tvEmployeeName.text = listEmployee[position].employee_name
        val salary = "$"+listEmployee[position].employee_salary.toString()
        holder.binding.tvSalary.text = salary
        val age = listEmployee[position].employee_age
        if (age in 25..35){
            holder.binding.tvAge.setTextColor(Color.parseColor("#00FF00"))
        } else
            holder.binding.tvAge.setTextColor(Color.parseColor("#FF0000"))
        holder.binding.tvAge.text = age.toString()
    }

    fun setListenerClick(clickItemMovements: ClickItemList) {
        this.onClickItem = clickItemMovements
    }

    inner class ItemEmployeeHolder(val binding: ItemListEmployeeBinding): RecyclerView.ViewHolder(binding.root),
            View.OnClickListener {

        init {
            binding.containerItem.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onClickItem!!.clickButtons(v!!, adapterPosition)
        }

    }

}