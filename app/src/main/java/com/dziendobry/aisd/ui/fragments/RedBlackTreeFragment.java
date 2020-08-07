package com.dziendobry.aisd.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dziendobry.aisd.R;
import com.dziendobry.aisd.func.RedBlackTree;


public class RedBlackTreeFragment extends Fragment {

    TextView red_black_tree_description_text_view;
    EditText red_black_tree_input_edit_text;
    com.google.android.material.floatingactionbutton.FloatingActionButton button_calculate_red_black_tree;
    Boolean checkIfNumbers = true;

    public RedBlackTreeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_red_black_tree, container, false);
        red_black_tree_description_text_view = v.findViewById(R.id.red_black_tree_description_text_view);
        red_black_tree_input_edit_text = v.findViewById(R.id.red_black_tree_input_edit_text);
        button_calculate_red_black_tree = v.findViewById(R.id.button_calculate_red_black_tree);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfNumbers = true;
                calculate();
            }
        };

        button_calculate_red_black_tree.setOnClickListener(listener);
    }

    private void calculate() {
        String array = "";
        array = red_black_tree_input_edit_text.getText().toString();
        if (array.isEmpty()) {
            red_black_tree_description_text_view.setText(getString(R.string.empty));
        } else {
            array = createIntArray(array);
            if (checkIfNumbers) {
                red_black_tree_description_text_view.setText(array);
            }
        }
    }

    private String createIntArray(String array) {
        int[] arrayInt = new int[array.length()];
        int arraySize = 0;
        for (int i = 1; i <= array.length(); i++){
            if (array.charAt(i-1)==' '||(array.charAt(i-1)>='0' && array.charAt(i-1)<='9')) {
                //
            }
            else{
                Toast.makeText(
                        (getActivity()),
                        getString(R.string.partition_toast_write_numbers),
                        Toast.LENGTH_SHORT
                ).show();
                checkIfNumbers = false;
                break;
            }
        }
        if(array.charAt(array.length()-1)==' '){
            Toast.makeText(
                    (getActivity()),
                    getString(R.string.partition_toast_write_numbers),
                    Toast.LENGTH_SHORT
            ).show();
            checkIfNumbers = false;
        }

        if (checkIfNumbers) {
            String[] arrayWithDivision = array.split(" ");
            arraySize = arrayWithDivision.length;
            for (int i = 1; i < arrayWithDivision.length + 1; i++) {
                arrayInt[i - 1] = (Integer.parseInt(arrayWithDivision[i - 1]));
            }

        }
        return calculateRedBlackTree(arrayInt, arraySize,array);
    }

    private String calculateRedBlackTree(int[] arrayInt, int arraySize,String array) {
        RedBlackTree bst = new RedBlackTree();

        for (int i = 1; i < arraySize+1; i++) {
            bst.insert(arrayInt[i-1]);
        }
        bst.prettyPrint();
        return bst.arr;
    }
}