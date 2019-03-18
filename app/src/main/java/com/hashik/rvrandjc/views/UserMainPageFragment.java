package com.hashik.rvrandjc.views;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.models.GlobalApplication;
import com.hashik.rvrandjc.models.JSONDataModels.JSONData;
import com.hashik.rvrandjc.models.RootFragmentManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class UserMainPageFragment extends Fragment {
    private Button signOut;
    private TextView rolno;
    private TextView gpa;
    private TextView rank;
    public UserMainPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (GlobalApplication.getUserData() == null) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
            Gson gson = new Gson();
            String json = sp.getString("userdata", null);
            JSONData userData = gson.fromJson(json, JSONData.class);
            GlobalApplication.setUserData(userData);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootFragmentManager.getInstance().setCurrentFragment(1); // 1 is for UserMainPage
        // Inflate the layout for this fragment
        View userLayout = inflater.inflate(R.layout.fragment_user_main_page, container, false);
        signOut = userLayout.findViewById(R.id.logout);
        LinearLayout semesterGradesLayout = userLayout.findViewById(R.id.semester_grades_label);
        LinearLayout internalMarksLayout = userLayout.findViewById(R.id.internal_marks_label);
        LinearLayout attendanceReportLayout = userLayout.findViewById(R.id.attendance_report_label);
        LinearLayout webSiteOpen = userLayout.findViewById(R.id.open_site);
        rolno = userLayout.findViewById(R.id.roll_no);
        gpa = userLayout.findViewById(R.id.gpa);
        rank = userLayout.findViewById(R.id.rank);

        //Click listeners
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initiating logout
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                GlobalApplication.setUserData(null);
                sp.edit().putBoolean("login",false).apply();// setting login flag to null
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                transaction.setCustomAnimations(R.anim.frag_entry_slide, R.anim.frag_exit_slide);
                transaction.replace(R.id.root_frame, new SignInFragment()).commit();
            }
        });

        semesterGradesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initiating transition
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                transaction.setCustomAnimations(R.anim.frag_entry_slide, R.anim.frag_exit_slide);
                transaction.replace(R.id.root_frame, new SemesterGradesFragment()).commit();
            }
        });
        internalMarksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initiating transition
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                transaction.setCustomAnimations(R.anim.frag_entry_slide, R.anim.frag_exit_slide);
                transaction.replace(R.id.root_frame, new InternalMarksFragment()).commit();
            }
        });
        attendanceReportLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initiating transition
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                transaction.setCustomAnimations(R.anim.frag_entry_slide, R.anim.frag_exit_slide);
                transaction.replace(R.id.root_frame, new AttendanceReportFragment()).commit();
            }
        });
        webSiteOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = "http://rvrjcce.ac.in/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try {
                    getActivity().startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    // Chrome browser presumably not installed so allow user to choose instead
                    intent.setPackage(null);
                    getActivity().startActivity(intent);
                }
            }
        });

        return userLayout;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(GlobalApplication.getUserData().getUser() != null){
            gpa.setText(GlobalApplication.getUserData().getUser().getCgpa());
            rank.setText(GlobalApplication.getUserData().getUser().getRank());
            rolno.setText(GlobalApplication.getUserData().getUser().getNumber());
        }else{
            signOut.performClick(); //Logout if the userdata object is null null may have caused due to clearing of cache or data
        }
    }

    public void onBackPressed() {
        Toast.makeText(getActivity(), "Back pressed", Toast.LENGTH_LONG).show();
    }
}
