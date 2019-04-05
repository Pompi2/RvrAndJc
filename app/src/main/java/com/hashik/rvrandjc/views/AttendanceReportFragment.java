package com.hashik.rvrandjc.views;


import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.adapters.AttendanceReportAdapter;
import com.hashik.rvrandjc.adapters.InternalMarksAdapter;
import com.hashik.rvrandjc.models.GlobalApplication;
import com.hashik.rvrandjc.models.JSONDataModels.Overview;
import com.hashik.rvrandjc.models.JSONDataModels.Subjects;
import com.hashik.rvrandjc.models.MyAnimation;
import com.hashik.rvrandjc.models.RootFragmentManager;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Fragment to show attendnence report
 */
public class AttendanceReportFragment extends Fragment {
    LinearLayout expandableLayout;
    RelativeLayout buttonLayout;

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
        final CardView overViewCard = view.findViewById(R.id.overview_card);
        fillOverViewCard(overViewCard);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //Adding animation
        recyclerView.setLayoutAnimation(MyAnimation.getRecyclerViewAniation());
        recyclerView.setAdapter(adapter);

        if (GlobalApplication.getUserData().getTime() != null) {
            TextView lastUpdate = view.findViewById(R.id.lastupdate);//Setting lastupdate
            lastUpdate.setText(String.format("%s %s", getString(R.string.last_update), GlobalApplication.getUserData().getTime()));
        }

        final RelativeLayout buttonLayout = overViewCard.findViewById(R.id.button);
        LinearLayout completeCard = overViewCard.findViewById(R.id.one_card);

        buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                LinearLayout expandableLayout = overViewCard.findViewById(R.id.expandableLayout);
                onClickButton(expandableLayout, buttonLayout);
            }
        });
        completeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLayout.performClick();
            }
        });

        return view;
    }

    private void onClickButton(LinearLayout expandableLayout, RelativeLayout buttonLayout) {
        //Simply set View to Gone if not expanded
        //Not necessary but I put simple rotation on button layout
        if (expandableLayout.getVisibility() == View.VISIBLE) {
            createRotateAnimator(buttonLayout, 180f, 0f).start();
            expandableLayout.setVisibility(View.GONE);
        } else {
            createRotateAnimator(buttonLayout, 0f, 180f).start();
            expandableLayout.setVisibility(View.VISIBLE);
        }
    }

    private void fillOverViewCard(CardView overViewCard) {
        TextView title = overViewCard.findViewById(R.id.title);
        LinearLayout expandableLayout = overViewCard.findViewById(R.id.expandableLayout);
        title.setText(getString(R.string.attendance_overview));
        if (GlobalApplication.getUserData().getUser().getOverview() != null) {
            for (Overview overview : GlobalApplication.getUserData().getUser().getOverview()) {
                addATextView(expandableLayout, overview);
            }
        }
        expandableLayout.setVisibility(View.GONE);
    }

    //Code to rotate button
    private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(500);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
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

    private void addATextView(LinearLayout expandableLayout, Overview overview) {
        LinearLayout row = new LinearLayout(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(150, 0, 0, 0);
        row.setLayoutParams(params);
        row.setOrientation(LinearLayout.HORIZONTAL);
        TextView sub = new TextView(getActivity());
        TextView count = new TextView(getActivity());

        sub.setTextSize(15);
        count.setTextSize(15);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                (float) 1.0
        );
        sub.setLayoutParams(param);
        count.setLayoutParams(param);

        sub.setTypeface(Typeface.DEFAULT_BOLD);
        sub.setPadding(0, 10, 0, 15);
        count.setPadding(30, 10, 0, 10);

        sub.setText(overview.getCode());
        count.setText(overview.getAtt());
        if (overview.getCode().equalsIgnoreCase("TPC") ||
                overview.getCode().equalsIgnoreCase("TCC") ||
                overview.getCode().equalsIgnoreCase("Percentage")) {
            count.setTypeface(Typeface.DEFAULT_BOLD);
        }
        row.addView(sub);
        row.addView(count);
        expandableLayout.addView(row);
    }

}
