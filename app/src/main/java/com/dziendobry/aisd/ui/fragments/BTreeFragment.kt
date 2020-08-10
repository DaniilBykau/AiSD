package com.dziendobry.aisd.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dziendobry.aisd.R
import kotlinx.android.synthetic.main.fragment_b_tree.*
import kotlinx.android.synthetic.main.fragment_binary_heap.*
import kotlinx.android.synthetic.main.fragment_partition.*
import kotlin.math.pow

class BTreeFragment : Fragment(R.layout.fragment_b_tree) {

    private var checkIfNumbers: Boolean = true
    private lateinit var keys: String
    private lateinit var nodes: String


    override fun onStart() {
        super.onStart()
        button_calculate_b_tree.setOnClickListener {
            checkIfNumbers = true
            calculate()
        }
    }

    private fun calculate() {
        keys = b_tree_input_T_edit_text.text.toString()
        nodes = b_tree_input_H_edit_text.text.toString()
        if (keys.isEmpty() || nodes.isEmpty()) {
            b_tree_output_text_view.text = getString(R.string.empty)
        } else {
            var T_int = keys.toDouble()
            var H_int = nodes.toDouble()
            calculateBTree(T_int, H_int)
        }
    }

    private fun calculateBTree(T: Double, H: Double) {
        var result = ""
        var node_max = (1 - (2 * T).pow(H + 1)) / (1 - 2 * T)
        var node_min = 1 + 2 * ((1 - T.pow(H)) / (1 - T))
        var key_max = (2 * T).pow(H + 1) - 1
        var key_min = 2 * (T.pow(H)) - 1
        val output = "node max = $node_max\n" +
                "node min = $node_min\n" +
                "key max = $key_max\n" +
                "key min = $key_min\n"
        if (checkIfNumbers) {
            b_tree_output_text_view.text = output
        }
    }
}