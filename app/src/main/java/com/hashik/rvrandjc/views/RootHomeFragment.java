package com.hashik.rvrandjc.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.models.RootFragmentManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class RootHomeFragment extends Fragment {

    private static final String TAG = "RootHomeFragment";
    private Boolean login;
    public RootHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(login == null){
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
            login = sp.getBoolean("login", false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        if(login){
            RootFragmentManager.getInstance().setCurrentFragment(1); // 1 is for usermainpagefragment
        }
        switch (RootFragmentManager.getInstance().getCurrentFragment()){
            case 0: transaction.replace(R.id.root_frame, new SignInFragment());
            break;
            case 1: transaction.replace(R.id.root_frame, new UserMainPageFragment());
            break;
            case 2: transaction.replace(R.id.root_frame, new SemesterGradesFragment());
            break;
            case 3: transaction.replace(R.id.root_frame, new InternalMarksFragment());
            break;
            case 4: transaction.replace(R.id.root_frame, new AttendanceReportFragment());
            break;
            default: transaction.replace(R.id.root_frame, new SignInFragment());

        }
        transaction.commit();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_root_home, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: RootFragment destroyed");
    }
}
