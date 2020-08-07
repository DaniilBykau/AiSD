package com.dziendobry.aisd.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dziendobry.aisd.R
import com.dziendobry.aisd.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_partition.*


class PartitionFragment : BaseFragment(R.layout.fragment_partition) {

    private lateinit var array: String
    private lateinit var sortedArray: String
    private var checkIfNumbers: Boolean = true
    private  var output:Int = 0


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.confirm_menu -> {
                checkIfNumbers = true
                calculate()
            }
        }
        return true
    }

    private fun calculate() {
        array = partition_input_edit_text.text.toString()
        if (array.isEmpty()) {
            partition_sorted_array_text_view.text = getString(R.string.empty)
        } else {
            createIntArray()
            if (checkIfNumbers && sortedArray.isNotEmpty()) {
                partition_sorted_array_text_view.text = sortedArray
                partition_output_text_view.text = output.toString()
            }
        }

    }

    private fun createIntArray() {

        val arrayInt = arrayOfNulls<Int>(array.length)
        for (i in 1..array.length) {
            if (array[i-1].toInt()==32||(array[i-1].toInt() in 48..57)) {
                //
            }
            else{
                Toast.makeText(
                    context,
                    getString(R.string.partition_toast_write_numbers),
                    Toast.LENGTH_SHORT
                ).show()
                checkIfNumbers = false
                break
            }
        }
        if(array[array.length-1]==' '){
            Toast.makeText(
                context,
                getString(R.string.partition_toast_write_numbers),
                Toast.LENGTH_SHORT
            ).show()
            checkIfNumbers = false
        }

        if (checkIfNumbers) {
            val arrayWithDivision: List<String> = array.split(" ")
            val arraySize = arrayWithDivision.size
            for (i in 1..arrayWithDivision.size) {
                arrayInt[i - 1] = arrayWithDivision[i - 1].toInt()
            }
            output = calculatePartition(arrayInt, arraySize)
            sortedArray = ""
            for (i in 1..arraySize) {
                sortedArray += arrayInt[i - 1].toString() + " "
            }
        }
    }

    private fun calculatePartition(arrayInt: Array<Int?>, arraySize :Int): Int {
        val r = arraySize-1
        val l = 0

        val x = arrayInt[l]

        var l_m = l - 1
        var r_m = r + 1

        while (true) {
            while (true) {
                l_m += 1
                if (arrayInt[l_m]!! >= x!!) {
                    break
                }
            }
            while (true) {
                r_m -= 1
                if (arrayInt[r_m]!! <= x) {
                    break
                }
            }
            if (l_m < r_m) {
                val t = arrayInt[l_m]
                arrayInt[l_m] = arrayInt[r_m]
                arrayInt[r_m] = t
            } else {
                return r_m
            }
        }

    }
}

