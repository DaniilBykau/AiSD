package com.dziendobry.aisd.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dziendobry.aisd.R
import com.dziendobry.aisd.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_binary_heap.*
import kotlinx.android.synthetic.main.fragment_partition.*


class BinaryHeapFragment : BaseFragment(R.layout.fragment_binary_heap) {

    private lateinit var array: String
    private lateinit var sortedArray: String
    private var checkIfNumbers: Boolean = true

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
        array = binary_heap_input_edit_text.text.toString()
        if (array.isEmpty()) {
            binary_heap_sorted_array_text_view.text = getString(R.string.empty)
        } else {
            createIntArray()
            if (checkIfNumbers && sortedArray.isNotEmpty()) {
                binary_heap_sorted_array_text_view.text = sortedArray
            }
        }
    }

    private fun createIntArray() {
        val arrayInt = arrayOfNulls<Int>(array.length)
        for (i in 1..array.length) {
            if (array[i - 1].toInt() == 32 || (array[i - 1].toInt() in 48..57) || array[i - 1].toInt() == 45) {
                //
            } else {
                Toast.makeText(
                    context,
                    getString(R.string.partition_toast_write_numbers),
                    Toast.LENGTH_SHORT
                ).show()
                checkIfNumbers = false
                break
            }
        }
        if (array[array.length - 1] == ' ') {
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
            BuildHeap(arrayInt, arraySize)
            sortedArray = ""
            for (i in 1..arraySize) {
                sortedArray += arrayInt[i - 1].toString() + " "
            }
        }
    }

    private fun BuildHeap(arrayInt: Array<Int?>, arraySize: Int) {
        val startIdx: Int
        var n = arraySize
        if (arraySize % 2 == 0) {
            startIdx = n / 2 - 1
        } else {
            n--
            startIdx = n / 2 - 1
            n++
        }
        for (i in startIdx downTo 0) {
            heapify(arrayInt, n, i)
        }
    }

    private fun heapify(arr: Array<Int?>, n: Int, i: Int) {
        var largest = i
        val l = 2 * i + 1
        val r = 2 * i + 2


        if (l < n && arr[l]!! > arr[largest]!!) {
            largest = l
        }

        if (r < n && arr[r]!! > arr[largest]!!) {
            largest = r
        }

        if (largest != i) {
            val t = arr[i]
            arr[i] = arr[largest]
            arr[largest] = t
            heapify(arr, n, largest)
        }

    }
}