package com.dziendobry.aisd.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dziendobry.aisd.R
import kotlinx.android.synthetic.main.fragment_b_tree.*
import kotlinx.android.synthetic.main.fragment_binary_heap.*
import kotlinx.android.synthetic.main.fragment_o_n_p.*
import kotlinx.android.synthetic.main.fragment_partition.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ONPFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ONPFragment : Fragment(R.layout.fragment_o_n_p) {

    private var checkIfNumbers: Boolean = true
    private lateinit var array: String
    private lateinit var output: String


    override fun onStart() {
        super.onStart()

        button_calculate_onp.setOnClickListener {
            checkIfNumbers = true
            calculate()
        }
    }

    private fun calculate() {
        array = onp_input_edit_text.text.toString()
        if (array.isEmpty()) {
            onp_output_text_view.text = getString(R.string.empty)
        } else {
            output = rpnCalculate(array)
            if (checkIfNumbers) {
                onp_output_text_view.text = output
            }
        }
    }

    private fun rpnCalculate(expr: String): String {
        if (expr.isEmpty()) throw IllegalArgumentException("Expresssion cannot be empty")
        val tokens = expr.split(' ').filter { it != "" }
        val stack = mutableListOf<Double>()
        for (token in tokens) {
            val d = token.toDoubleOrNull()
            if (d != null) {
                stack.add(d)
            }
            else if ((token.length > 1) || (token !in "+-*/^")) {
                onp_output_text_view.text = getString(R.string.error_message)
                checkIfNumbers = false
                break
            }
            else if (stack.size < 2) {
                onp_output_text_view.text = getString(R.string.error_message)
                checkIfNumbers = false
                break
            }
            else {
                val d1 = stack.removeAt(stack.lastIndex)
                val d2 = stack.removeAt(stack.lastIndex)
                stack.add(when (token) {
                    "+"  -> d2 + d1
                    "-"  -> d2 - d1
                    "*"  -> d2 * d1
                    "/"  -> d2 / d1
                    else -> Math.pow(d2, d1)
                })
            }
        }
        return stack[0].toString()
    }
}