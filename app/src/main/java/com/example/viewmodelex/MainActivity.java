package com.example.viewmodelex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.viewmodelex.Explore.ExeploreFragment;
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

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, workoutsFragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction1 = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()) {
                    case R.id.workouts:
                        transaction1.replace(R.id.frameLayout, workoutsFragment).commitAllowingStateLoss();
                        break;
                    case R.id.exercises:
                        transaction1.replace(R.id.frameLayout, exerciesesFragment).commitAllowingStateLoss();
                        break;
                    case R.id.logs:
                        transaction1.replace(R.id.frameLayout, logsFragment).commitAllowingStateLoss();
                        break;
                    case R.id.explore:
                        transaction1.replace(R.id.frameLayout, exeploreFragment).commitAllowingStateLoss();
                        break;
                    case R.id.setting:
                        transaction1.replace(R.id.frameLayout, settingFragment).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });
    }
}
