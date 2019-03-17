package com.hashik.rvrandjc.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.models.RootFragmentManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class UserMainPageFragment extends Fragment {
    private Button signOut;

    public UserMainPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootFragmentManager.getInstance().setCurrentFragment(1); // 1 is for UserMainPage
        // Inflate the layout for this fragment
        View userLayout =  inflater.inflate(R.layout.fragment_user_main_page, container, false);
        signOut = userLayout.findViewById(R.id.logout);
        LinearLayout semesterGradesLayout = userLayout.findViewById(R.id.semester_grades_label);

        //Click listeners
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initiating logout
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                transaction.setCustomAnimations(R.anim.frag_entry_slide,R.anim.frag_exit_slide);
                transaction.replace(R.id.root_frame, new SignInFragment()).commit();
            }
        });

        semesterGradesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initiating transition
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                transaction.setCustomAnimations(R.anim.frag_entry_slide,R.anim.frag_exit_slide);
                transaction.replace(R.id.root_frame, new SemesterGradesFragment()).commit();
            }
        });
        return userLayout;
    }

    public void onBackPressed(){
        Toast.makeText(getActivity(),"Back pressed",Toast.LENGTH_LONG).show();
    }
}
