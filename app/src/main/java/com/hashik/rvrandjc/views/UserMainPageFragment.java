package com.hashik.rvrandjc.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.models.RootFragmentManager;


public class UserMainPageFragment extends Fragment {
    public UserMainPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootFragmentManager.getInstance().setCurrentFragment(1); // 1 is for UserMainPage
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_main_page, container, false);
    }
}
