package com.aidos.roomy_app.ui.admin_ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aidos.roomy_app.R

class EditRoomFragment : Fragment() {

    companion object {
        fun newInstance() = EditRoomFragment()
    }

    private lateinit var viewModel: EditRoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_room, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditRoomViewModel::class.java)
        // TODO: Use the ViewModel
    }

}