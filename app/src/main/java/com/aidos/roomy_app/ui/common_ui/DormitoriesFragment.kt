package com.aidos.roomy_app.ui.common_ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aidos.roomy_app.R

class DormitoriesFragment : Fragment() {

    companion object {
        fun newInstance() = DormitoriesFragment()
    }

    private lateinit var viewModel: DormitoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dormitories, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DormitoriesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}