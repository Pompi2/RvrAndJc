package com.hashik.rvrandjc.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.models.RootFragmentManager;

/**
 * Fragment to display internal marks
 */
public class InternalMarksFragment extends Fragment {


    public InternalMarksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootFragmentManager.getInstance().setCurrentFragment(3); // 3 is for InternalMarksFragment
        View view = inflater.inflate(R.layout.fragment_internal_marks, container, false); // Inflate the layout for this fragment

        return view;
    }

    public void onBackPressed(){
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.frag_entry_slide,R.anim.frag_exit_slide);
        transaction.replace(R.id.root_frame, new UserMainPageFragment()).commit();
    }
}
