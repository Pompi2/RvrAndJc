package com.hashik.rvrandjc.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.adapters.AttendanceReportAdapter;
import com.hashik.rvrandjc.models.GlobalApplication;
import com.hashik.rvrandjc.models.RootFragmentManager;

import java.util.Arrays;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Fragment to show attendnence report
 */
public class AttendanceReportFragment extends Fragment {


    /**
     * Instantiates a new Attendance report fragment.
     */
    public AttendanceReportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootFragmentManager.getInstance().setCurrentFragment(4); // 4 is for AttendanceReportFragment
        View view = inflater.inflate(R.layout.fragment_attendance_report, container, false); // Inflate the layout for this fragment

        RecyclerView recyclerView = view.findViewById(R.id.attendance_rv);
        AttendanceReportAdapter adapter = new AttendanceReportAdapter(Arrays.asList(GlobalApplication.getUserData().getAttendancereport()));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //fetch data and on ExpandableRecyclerAdapter
        recyclerView.setAdapter(adapter);

        if (GlobalApplication.getUserData().getTime() != null) {
            TextView lastUpdate = view.findViewById(R.id.lastupdate);//Setting lastupdate
            lastUpdate.setText(String.format("%s %s", getString(R.string.last_update), GlobalApplication.getUserData().getTime()));
        }
        return view;
    }

    /**
     * On back pressed.
     */
    public void onBackPressed() {
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.frag_entry_slide, R.anim.frag_exit_slide);
        transaction.replace(R.id.root_frame, new UserMainPageFragment()).commit();
    }

}
