package com.hashik.rvrandjc.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
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
import com.hashik.rvrandjc.models.JSONDataModels.Internalmarks;
import com.hashik.rvrandjc.models.JSONDataModels.Subjects;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class InternalMarksAdapter extends RecyclerView.Adapter<InternalMarksAdapter.ViewHolder> {

    private List<Internalmarks> marksList;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private Context context;

    public InternalMarksAdapter(List<Internalmarks> repos) {
        this.marksList = repos;
        //set initial expanded state to false
        for (int i = 0; i < repos.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public InternalMarksAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final InternalMarksAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.tvName.setText(marksList.get(i).getTitle());

        //check if view is expanded
        final boolean isExpanded = expandState.get(i);
        viewHolder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        viewHolder.buttonLayout.setRotation(expandState.get(i) ? 180f : 0f);
        for (Subjects subject : marksList.get(i).getSubjects()) {
            if (subject.getCode() != null) {
                TextView textView = new TextView(context);
                addATextView(viewHolder, subject);
            }
        }
        viewHolder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(viewHolder.expandableLayout, viewHolder.buttonLayout, viewHolder.getCompleteCard(), i);
            }
        });
        viewHolder.completeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.buttonLayout.performClick();
            }
        });
    }

    private void addATextView(ViewHolder viewHolder, Subjects subject) {
        LinearLayout row = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(150, 0, 0, 0);
        row.setLayoutParams(params);
        row.setOrientation(LinearLayout.HORIZONTAL);
        TextView sub = new TextView(context);
        TextView grade = new TextView(context);
        sub.setTextSize(15);
        grade.setTextSize(15);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                (float) 1.0
        );
        sub.setLayoutParams(param);
        grade.setLayoutParams(param);

        sub.setTypeface(Typeface.DEFAULT_BOLD);
        sub.setPadding(0, 10, 0, 15);
        grade.setPadding(30, 10, 0, 10);

        sub.setText(subject.getCode());
        grade.setText(subject.getMarks());
        row.addView(sub);
        row.addView(grade);
        viewHolder.expandableLayout.addView(row);
    }

    @Override
    public int getItemCount() {
        return marksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvOwnerLogin, tvOwnerUrl;
        private ImageView ivOwner;
        public RelativeLayout buttonLayout;
        public LinearLayout expandableLayout;
        public LinearLayout completeCard;

        public LinearLayout getCompleteCard(){
            return this.completeCard;
        }

        public ViewHolder(View view) {
            super(view);
            completeCard = (LinearLayout) view.findViewById(R.id.one_card);
            tvName = (TextView) view.findViewById(R.id.title);

            buttonLayout = (RelativeLayout) view.findViewById(R.id.button);
            expandableLayout = (LinearLayout) view.findViewById(R.id.expandableLayout);
        }
    }

    private void onClickButton(final LinearLayout expandableLayout, final RelativeLayout buttonLayout, LinearLayout completeCard, final int i) {

        //Simply set View to Gone if not expanded
        //Not necessary but I put simple rotation on button layout
        if (expandableLayout.getVisibility() == View.VISIBLE) {
            createRotateAnimator(buttonLayout, 180f, 0f).start();
            final ChangeBounds transition = new ChangeBounds();
            //Transition
            transition.setDuration(300); // Sets a duration of 600 milliseconds
            TransitionManager.beginDelayedTransition(completeCard,transition);

            expandableLayout.setVisibility(View.GONE);
            expandState.put(i, false);
        } else {
            createRotateAnimator(buttonLayout, 0f, 180f).start();
            //Transition
            final ChangeBounds transition = new ChangeBounds();
            transition.setDuration(300); // Sets a duration of 600 milliseconds
            TransitionManager.beginDelayedTransition(completeCard,transition);

            expandableLayout.setVisibility(View.VISIBLE);
            expandState.put(i, true);
        }
    }

    //Code to rotate button
    private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }
}