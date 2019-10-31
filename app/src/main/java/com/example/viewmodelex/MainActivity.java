package com.example.viewmodelex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.viewmodelex.Exercieses.ExerciesesFragment;
import com.example.viewmodelex.Explore.ExeploreFragment;
import com.example.viewmodelex.Log.LogsFragment;
import com.example.viewmodelex.WorkOut.RecordWorkFragment;
import com.example.viewmodelex.WorkOut.WorkoutsFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private WorkoutsFragment workoutsFragment = new WorkoutsFragment();
    private RecordWorkFragment recordWorkFragment= new RecordWorkFragment();
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
    public void callRecord() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, recordWorkFragment).commitAllowingStateLoss();

    }

}
