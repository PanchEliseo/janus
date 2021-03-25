package com.exam.janus.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.janus.MainActivity
import com.exam.janus.R
import com.exam.janus.service.EmployeesResponse
import com.exam.janus.ui.home.detail.EmployeeDetailActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), ClickItemList {

    private lateinit var homeViewModel: HomeViewModel
    private var listEmployee = arrayListOf<EmployeesResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listEmployee = (activity as MainActivity).reports
        if (listEmployee.isNotEmpty()){
            setListView()
        }
    }

    private fun setListView(){
        val adapter = EmployeesAdapter(requireContext(), listEmployee)
        rvEmployees.adapter = adapter
        rvEmployees.layoutManager = LinearLayoutManager(activity)
        adapter.setListenerClick(this)
    }

    override fun clickButtons(view: View, position: Int) {
        Log.i("POSITION", "$position")
        val intent = Intent(requireContext(), EmployeeDetailActivity::class.java).apply {
            putExtra("employeeId", listEmployee[position].id)
        }
        startActivity(intent)
    }
}