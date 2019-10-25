package com.example.viewmodelex.Exercieses;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.viewmodelex.MainActivity;
import com.example.viewmodelex.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciesesFragment extends Fragment {


    public ExerciesesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exercieses, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button1 = (Button) view.findViewById(R.id.addExer);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDialog(v);
            }
        });
    }

    public void callDialog(View v) {
        Button button1 = (Button) v.findViewById(R.id.addExer);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDialog(v);
            }
        });

        final Dialog newExer = new Dialog(getContext());
        newExer.setContentView(R.layout.dialog_newexer);

        Button cancel = newExer.findViewById(R.id.newExerCancelBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newExer.dismiss();
            }
        });

        //스피너
        final Spinner spinner_muscle = (Spinner) v.findViewById(R.id.spinner);
        final Spinner spinner_cate = (Spinner) v.findViewById(R.id.spinner2);

        String[] str1 = getResources().getStringArray(R.array.bodyaprt_arr);
        String[] str2 = getResources().getStringArray(R.array.catecory_arr);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (getContext(), R.layout.dialog_newexer, str1);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (getContext(), R.layout.dialog_newexer, str2);

        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner_muscle.setAdapter(adapter1);
        spinner_cate.setAdapter(adapter2);

        spinner_muscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner_muscle.getSelectedItemPosition() > 0) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_cate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner_cate.getSelectedItemPosition() > 0) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        newExer.show();
    }
}