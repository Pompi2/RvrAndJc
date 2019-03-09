package com.example.rvrandjc.adapters;

import com.example.rvrandjc.views.AboutFragment;
import com.example.rvrandjc.views.NotificationFragment;
import com.example.rvrandjc.views.SettingsFragment;
import com.example.rvrandjc.views.SignInFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainFragmentAdapter extends FragmentPagerAdapter {


    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new SignInFragment();
            case 1: return new NotificationFragment();
            case 2: return new SettingsFragment();
            case 3: return new AboutFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
