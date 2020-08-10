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
import com.dziendobry.aisd.func.ToONP;

public class ToONPFragment extends Fragment {

    TextView to_onp_output_text_view;
    EditText to_onp_input_edit_text;

    com.google.android.material.floatingactionbutton.FloatingActionButton button_calculate_to_onp;
    EditText to_onp_input_plus_edit_text;
    EditText to_onp_input_minus_edit_text;
    EditText to_onp_input_mult_edit_text;
    EditText to_onp_input_division_edit_text;
    Boolean checkIfNumbers = true;


    public ToONPFragment() {
        // Required empty public constructor
        //a+b*c/d-b*a+e–a*g
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_to_o_n_p, container, false);

        to_onp_output_text_view = v.findViewById(R.id.to_onp_output_text_view);
        to_onp_input_edit_text = v.findViewById(R.id.to_onp_input_edit_text);
        to_onp_input_plus_edit_text = v.findViewById(R.id.to_onp_input_plus_edit_text);
        to_onp_input_minus_edit_text = v.findViewById(R.id.to_onp_input_minus_edit_text);
        to_onp_input_mult_edit_text = v.findViewById(R.id.to_onp_input_mult_edit_text);
        to_onp_input_division_edit_text = v.findViewById(R.id.to_onp_input_division_edit_text);
        button_calculate_to_onp = v.findViewById(R.id.button_calculate_to_onp);

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
        button_calculate_to_onp.setOnClickListener(listener);
    }

    private void calculate() {
        String array = "";
        int plus = 0;
        int minus = 0;
        int div = 0;
        int mult = 0;

        array = to_onp_input_edit_text.getText().toString();
        plus = Integer.parseInt(to_onp_input_plus_edit_text.getText().toString());
        minus = Integer.parseInt(to_onp_input_minus_edit_text.getText().toString());
        div = Integer.parseInt(to_onp_input_division_edit_text.getText().toString());
        mult = Integer.parseInt(to_onp_input_mult_edit_text.getText().toString());

        if (array.isEmpty()||plus==0||minus==0||div==0||mult==0) {
            to_onp_output_text_view.setText(getString(R.string.empty));
        } else {
            array = createIntArray(array, plus, minus, div, mult);
            if (checkIfNumbers) {
                to_onp_output_text_view.setText(array);
            }
        }
    }

    private String createIntArray(String array, int plus, int minus, int div, int mult) {
        int[] arrayInt = new int[array.length()];
        int arraySize = 0;
        for (int i = 1; i <= array.length(); i++){
            if (array.charAt(i-1)=='*'||(array.charAt(i-1)>='a' && array.charAt(i-1)<='z')||array.charAt(i-1)=='/'||array.charAt(i-1)=='+'||array.charAt(i-1)=='-') {
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
            arraySize = array.length();
        }
        return calculateToONP( arraySize,array, plus, minus, div, mult);
    }

    private String calculateToONP(int arraySize, String array, int plus, int minus, int div, int mult) {
        ToONP toONP = new ToONP(plus, minus, div, mult);
        return  ToONP.infixToPostfix(array);
    }

}