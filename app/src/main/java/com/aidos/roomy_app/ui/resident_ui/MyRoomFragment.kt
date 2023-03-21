package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aidos.roomy_app.R
import dagger.android.support.DaggerFragment

class MyRoomFragment : DaggerFragment() {

    companion object {
        fun newInstance() = MyRoomFragment()
    }

    private lateinit var viewModel: MyRoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_room, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyRoomViewModel::class.java)
        // TODO: Use the ViewModel
    }

}