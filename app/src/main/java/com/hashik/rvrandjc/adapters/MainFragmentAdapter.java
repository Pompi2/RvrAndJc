package com.hashik.rvrandjc.adapters;

import com.hashik.rvrandjc.views.AboutFragment;
import com.hashik.rvrandjc.views.NotificationFragment;
import com.hashik.rvrandjc.views.RootHomeFragment;
import com.hashik.rvrandjc.views.SettingsFragment;
import com.hashik.rvrandjc.views.SignInFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MainFragmentAdapter extends FragmentPagerAdapter {


    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RootHomeFragment();
            case 1:
                return new NotificationFragment();
            case 2:
                return new SettingsFragment();
            case 3:
                return new AboutFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
