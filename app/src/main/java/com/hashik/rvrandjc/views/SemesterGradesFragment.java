package com.hashik.rvrandjc.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.adapters.SemesterGradesAdapter;
import com.hashik.rvrandjc.models.GlobalApplication;
import com.hashik.rvrandjc.models.RootFragmentManager;

import java.util.Arrays;


/**
 * Fragment to display grades
 */
public class SemesterGradesFragment extends Fragment {

    /**
     * Instantiates a new Semester grades fragment.
     */
    public SemesterGradesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootFragmentManager.getInstance().setCurrentFragment(2); // 2 is for SemesterGradesFragment
        View view = inflater.inflate(R.layout.fragment_semester_grades, container, false); // Inflate the layout for this fragment
        RecyclerView recyclerView = view.findViewById(R.id.semester_rv);
        SemesterGradesAdapter semesterAdapter = new SemesterGradesAdapter(Arrays.asList(GlobalApplication.getUserData().getSemester()));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
//fetch data and on ExpandableRecyclerAdapter
        recyclerView.setAdapter(semesterAdapter);
        return view;
    }

    public void onBackPressed(){
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.frag_entry_slide,R.anim.frag_exit_slide);
        transaction.replace(R.id.root_frame, new UserMainPageFragment()).commit();
    }
}
