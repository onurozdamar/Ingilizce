package com.example.ingilizce.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ingilizce.Fragments.MainFragment;
import com.example.ingilizce.Fragments.WordsFragment;
import com.example.ingilizce.Fragments.QuizzesFragment;

public class OrdersPagerAdapter extends FragmentStateAdapter {

    public OrdersPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return new WordsFragment();
            default:
                return new QuizzesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
