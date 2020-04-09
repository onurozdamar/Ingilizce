package com.example.ingilizce.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.example.ingilizce.Adapters.OrdersPagerAdapter;
import com.example.ingilizce.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(new OrdersPagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0: {
                        tab.setText("Anasayfa");
                        tab.setIcon(R.drawable.ic_pending);
                        break;
                    }
                    case 1: {
                        tab.setText("Kelimeler");
                        tab.setIcon(R.drawable.ic_confirmed);
                        break;
                    }
                    case 2: {
                        tab.setText("Sınavlar");
                        tab.setIcon(R.drawable.ic_delivered);
                        break;
                    }

                }
            }
        }
        );
        tabLayoutMediator.attach();

        Intent intent = getIntent();
        int index = intent.getIntExtra("position",0);
        tabLayout.selectTab(tabLayout.getTabAt(index));


    }


}
