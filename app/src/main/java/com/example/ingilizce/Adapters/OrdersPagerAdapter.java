package com.example.ingilizce.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ingilizce.Fragments.AnasayfaFragment;
import com.example.ingilizce.Fragments.KelimelerFragment;
import com.example.ingilizce.Fragments.SinavlarFragment;

public class OrdersPagerAdapter extends FragmentStateAdapter {

    public OrdersPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AnasayfaFragment();
            case 1:
                return new KelimelerFragment();
            default:
                return new SinavlarFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
