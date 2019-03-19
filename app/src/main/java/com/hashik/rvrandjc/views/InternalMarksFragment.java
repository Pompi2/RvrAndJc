package com.hashik.rvrandjc.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.adapters.InternalMarksAdapter;
import com.hashik.rvrandjc.models.GlobalApplication;
import com.hashik.rvrandjc.models.RootFragmentManager;

import java.util.Arrays;

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
        RecyclerView recyclerView = view.findViewById(R.id.internal_marks_rv);
        InternalMarksAdapter internalMarksAdapter = new InternalMarksAdapter(Arrays.asList(GlobalApplication.getUserData().getInternalmarks()));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //fetch data and on ExpandableRecyclerAdapter
        recyclerView.setAdapter(internalMarksAdapter);
        if(GlobalApplication.getUserData().getTime() != null){
            TextView lastupdate = view.findViewById(R.id.lastupdate);//Setting lastupdate
            lastupdate.setText(String.format("%s %s", getString(R.string.last_update), GlobalApplication.getUserData().getTime()));
        }
        return view;
    }

    public void onBackPressed(){
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.frag_entry_slide,R.anim.frag_exit_slide);
        transaction.replace(R.id.root_frame, new UserMainPageFragment()).commit();
    }
}
