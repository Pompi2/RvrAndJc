package com.hashik.rvrandjc.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.models.JSONDataModels.Attendance;
import com.hashik.rvrandjc.models.JSONDataModels.Attendancereport;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class AttendanceReportAdapter extends RecyclerView.Adapter<AttendanceReportAdapter.ViewHolder> {

    private List<Attendancereport> absentList;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private Context context;

    public AttendanceReportAdapter(List<Attendancereport> repos) {
        this.absentList = repos;
        //set initial expanded state to false
        for (int i = 0; i < repos.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public AttendanceReportAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AttendanceReportAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.setIsRecyclable(false);

        viewHolder.tvName.setText(absentList.get(i).getDate());

        //check if view is expanded
        final boolean isExpanded = expandState.get(i);
        viewHolder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        viewHolder.buttonLayout.setRotation(expandState.get(i) ? 180f : 0f);
        for (Attendance attendance : absentList.get(i).getAttendance()) {
            if (attendance.getTime() != null) {
                TextView textView = new TextView(context);
                addATextView(viewHolder, attendance);
            }
        }

        viewHolder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(viewHolder.expandableLayout, viewHolder.buttonLayout, i);
            }
        });
        viewHolder.completeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.buttonLayout.performClick();
            }
        });
    }

    private void addATextView(ViewHolder viewHolder, Attendance subject) {
        LinearLayout row = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(150, 0, 0, 0);
        row.setLayoutParams(params);
        row.setOrientation(LinearLayout.HORIZONTAL);
        TextView time = new TextView(context);
        TextView periods = new TextView(context);

        time.setTextSize(15);
        periods.setTextSize(15);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                (float) 1.0
        );
        time.setLayoutParams(param);
        periods.setLayoutParams(param);

        time.setTypeface(Typeface.DEFAULT_BOLD);
        time.setPadding(0, 10, 0, 15);
        periods.setPadding(30, 10, 0, 10);

        time.setText(subject.getTime());
        periods.setText(subject.getPeriod());
        row.addView(time);
        row.addView(periods);
        viewHolder.expandableLayout.addView(row);
    }

    @Override
    public int getItemCount() {
        return absentList.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvOwnerLogin, tvOwnerUrl;
        private ImageView ivOwner;
        public RelativeLayout buttonLayout;
        public LinearLayout expandableLayout;
        public LinearLayout completeCard;

        public ViewHolder(View view) {
            super(view);
            completeCard = (LinearLayout) view.findViewById(R.id.sem_card);
            tvName = (TextView) view.findViewById(R.id.title);

            buttonLayout = (RelativeLayout) view.findViewById(R.id.button);
            expandableLayout = (LinearLayout) view.findViewById(R.id.expandableLayout);
        }
    }

    private void onClickButton(final LinearLayout expandableLayout, final RelativeLayout buttonLayout, final int i) {

        //Simply set View to Gone if not expanded
        //Not necessary but I put simple rotation on button layout
        if (expandableLayout.getVisibility() == View.VISIBLE) {
            createRotateAnimator(buttonLayout, 180f, 0f).start();
            expandableLayout.setVisibility(View.GONE);
            expandState.put(i, false);
        } else {
            createRotateAnimator(buttonLayout, 0f, 180f).start();
            expandableLayout.setVisibility(View.VISIBLE);
            expandState.put(i, true);
        }
    }

    //Code to rotate button
    private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(500);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }
}