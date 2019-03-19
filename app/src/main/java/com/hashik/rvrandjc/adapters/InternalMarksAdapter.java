package com.hashik.rvrandjc.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.models.JSONDataModels.Data;
import com.hashik.rvrandjc.models.JSONDataModels.Internalmarks;
import com.hashik.rvrandjc.models.JSONDataModels.Semester;

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
    public void onBindViewHolder(final InternalMarksAdapter.ViewHolder viewHolder, final  int i) {

        viewHolder.setIsRecyclable(false);

        viewHolder.tvName.setText(marksList.get(i).getTitle());

        //check if view is expanded
        final boolean isExpanded = expandState.get(i);
        viewHolder.expandableLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);

        viewHolder.buttonLayout.setRotation(expandState.get(i) ? 180f : 0f);

        /*for (Data data : marksList.get(i)) {
            if (data.getTitle() != null && data.getGrade()!=null) {
                TextView textView = new TextView(context);

                textView.setText(String.format("%s                                            %s", data.getTitle(), data.getGrade()));
                textView.setTextSize(15);
                textView.setPadding(0,15,0,15);

                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                viewHolder.expandableLayout.addView(textView);
            }
        }*/
        viewHolder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(viewHolder.expandableLayout, viewHolder.buttonLayout,  i);
            }
        });
        viewHolder.completeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.buttonLayout.performClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return marksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName,tvOwnerLogin, tvOwnerUrl;
        private ImageView ivOwner;
        public RelativeLayout buttonLayout;
        public LinearLayout expandableLayout;
        public LinearLayout completeCard;

        public ViewHolder(View view) {
            super(view);
            completeCard = (LinearLayout) view.findViewById(R.id.sem_card);
            tvName = (TextView)view.findViewById(R.id.title);

            buttonLayout = (RelativeLayout) view.findViewById(R.id.button);
            expandableLayout = (LinearLayout) view.findViewById(R.id.expandableLayout);
        }
    }

    private void onClickButton(final LinearLayout expandableLayout, final RelativeLayout buttonLayout, final  int i) {

        //Simply set View to Gone if not expanded
        //Not necessary but I put simple rotation on button layout
        if (expandableLayout.getVisibility() == View.VISIBLE){
            createRotateAnimator(buttonLayout, 180f, 0f).start();
            expandableLayout.setVisibility(View.GONE);
            expandState.put(i, false);
        }else{
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