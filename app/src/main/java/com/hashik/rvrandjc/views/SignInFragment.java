package com.hashik.rvrandjc.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.models.RootFragmentManager;


public class SignInFragment extends Fragment {
    Button signInButton;
    private static final String TAG = "SignInFragment";

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootFragmentManager.getInstance().setCurrentFragment(0); // 0 is for signinfragment

        View myView = inflater.inflate(R.layout.fragment_sign_in, container, false);// Inflate the layout for this fragment
        signInButton = myView.findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new UserMainPageFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.frag_entry_slide,R.anim.frag_exit_slide);
                transaction.replace(R.id.root_frame, fragment);
                transaction.commit();
            }
        });
        return myView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Sign In fragment destroyed");
    }
}
