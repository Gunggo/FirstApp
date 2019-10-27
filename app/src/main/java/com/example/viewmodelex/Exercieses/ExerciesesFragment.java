package com.example.viewmodelex.Exercieses;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.viewmodelex.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciesesFragment extends Fragment {

    private ArrayList<ExerciesesDTO> mArrayList;
    private ExerciesesAdapter eAdapter;
    private int count = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
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

        final Dialog newExer = new Dialog(getContext());
        newExer.setContentView(R.layout.dialog_newexer);

        Button cancel = newExer.findViewById(R.id.newExerCancelBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newExer.dismiss();
            }
        });

        Button saveBtn = newExer.findViewById(R.id.exerSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData(newExer);
                newExer.dismiss();
            }
        });

        //스피너
        final EditText search_bar = newExer.findViewById(R.id.exerSerach);
        final Spinner spinner_muscle = (Spinner) newExer.findViewById(R.id.spinner);
        final Spinner spinner_cate = (Spinner) newExer.findViewById(R.id.spinner2);

        String[] str1 = getResources().getStringArray(R.array.bodyaprt_arr);
        String[] str2 = getResources().getStringArray(R.array.catecory_arr);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (getContext(), R.layout.support_simple_spinner_dropdown_item, str1);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (getContext(), R.layout.support_simple_spinner_dropdown_item, str2);

        spinner_muscle.setAdapter(adapter1);
        spinner_cate.setAdapter(adapter2);

        spinner_muscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_cate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 리스트
        RecyclerView mRecyclerView = (RecyclerView) getView().findViewById(R.id.exercisesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mArrayList = new ArrayList<>();

        eAdapter = new ExerciesesAdapter(mArrayList);
        mRecyclerView.setAdapter(eAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        newExer.show();
    }

    public void setData(Dialog newExer) {
        Spinner spinner_muscle = (Spinner) newExer.findViewById(R.id.spinner);
        Spinner spinner_cate = (Spinner) newExer.findViewById(R.id.spinner2);
        EditText search_bar = newExer.findViewById(R.id.exerSerach);

        String muscleName = spinner_muscle.getSelectedItem().toString();
        String category = spinner_cate.getSelectedItem().toString();
        String exerName = search_bar.getText().toString();

        if("없음".equals(muscleName) || "없음".equals(category) || exerName.length() == 0) {
            Toast.makeText(getContext(),"운동을 선택해주세요.",Toast.LENGTH_SHORT).show();
            newExer.dismiss();
            return;
        }

        ExerciesesDTO data = new ExerciesesDTO(exerName + " (" + category + ")", muscleName);
        mArrayList.add(0, data);

        eAdapter.notifyDataSetChanged();
    }
}