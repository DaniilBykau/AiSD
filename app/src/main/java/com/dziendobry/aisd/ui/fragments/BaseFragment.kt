package com.dziendobry.aisd.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.dziendobry.aisd.R
import com.dziendobry.aisd.ui.MainActivity


open class BaseFragment(layout: Int) : Fragment(layout) {
    override fun onStart() {
        setHasOptionsMenu(true)
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.confirm_menu, menu)
    }
}