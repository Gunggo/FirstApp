package com.example.viewmodelex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.viewmodelex.Exercieses.ExerciesesFragment;
import com.example.viewmodelex.Explore.ExeploreFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private WorkoutsFragment workoutsFragment = new WorkoutsFragment();
    private SettingFragment settingFragment = new SettingFragment();
    private LogsFragment logsFragment = new LogsFragment();
    private ExeploreFragment exeploreFragment = new ExeploreFragment();
    private ExerciesesFragment exerciesesFragment = new ExerciesesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //초기화
        String bannerid = getResources().getString(R.string.ad_unit_id_1);
        MobileAds.initialize(getApplicationContext(), bannerid);

        //테스트 광고 부르기
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest
                .Builder()
                .addTestDevice("HASH_DEVICE_ID") // 이거 빼면 진짜광고. 이건 테스트 코드
                .build();
        mAdView.loadAd(adRequest);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, workoutsFragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction1 = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()) {
                    case R.id.workouts:
                        transaction1.replace(R.id.frameLayout, workoutsFragment)
                                .commitAllowingStateLoss();
                        break;
                    case R.id.exercises:
                        transaction1.replace(R.id.frameLayout, exerciesesFragment)
                                .commitAllowingStateLoss();
                        break;
                    case R.id.logs:
                        transaction1.replace(R.id.frameLayout, logsFragment)
                                .commitAllowingStateLoss();
                        break;
                    case R.id.explore:
                        transaction1.replace(R.id.frameLayout, exeploreFragment)
                                .commitAllowingStateLoss();
                        break;
                    case R.id.setting:
                        transaction1.replace(R.id.frameLayout, settingFragment)
                                .commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });
    }

    public void getDialog(View view) {
        final Dialog newExer = new Dialog(this);
        newExer.setContentView(R.layout.dialog_newexer);

        Button cancel = newExer.findViewById(R.id.newExerCancelBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newExer.dismiss();
            }
        });

        //스피너
        final Spinner spinner_muscle = (Spinner) view.findViewById(R.id.spinner);
        final Spinner spinner_cate = (Spinner) view.findViewById(R.id.spinner2);

        String[] str1 = getResources().getStringArray(R.array.bodyaprt_arr);
        String[] str2 = getResources().getStringArray(R.array.catecory_arr);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this, R.layout.support_simple_spinner_dropdown_item, str1);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (this, R.layout.support_simple_spinner_dropdown_item, str2);

//        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        adapter2.createFromResource(getContext(),R.array.catecory_arr,R.layout.support_simple_spinner_dropdown_item);
//        adapter1.createFromResource(getContext(),R.array.bodyaprt_arr,R.layout.support_simple_spinner_dropdown_item);

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
