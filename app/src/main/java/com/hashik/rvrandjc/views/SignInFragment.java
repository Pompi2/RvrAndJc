package com.hashik.rvrandjc.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.models.RootFragmentManager;
import com.hashik.rvrandjc.viewmodels.SignInViewModel;


public class SignInFragment extends Fragment {
    private Button signInButton;
    private static final String TAG = "SignInFragment";
    private EditText username;
    private EditText password;
    private SignInViewModel signInViewModel;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootFragmentManager.getInstance().setCurrentFragment(0); // 0 is for signinfragment

        View myView = inflater.inflate(R.layout.fragment_sign_in, container, false);// Inflate the layout for this fragment
        //Assignment
        signInButton = myView.findViewById(R.id.sign_in_button);
        username = myView.findViewById(R.id.et_username);
        password = myView.findViewById(R.id.et_password);
        signInViewModel = ViewModelProviders.of(getActivity()).get(SignInViewModel.class);

        //Clicklisteners, Observers
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInViewModel.validateCredentials(username.getText().toString(), password.getText().toString());
            }
        });

        signInViewModel.getValidCreds().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                try {

                    if(aBoolean){
                        goToUserMainPageFragment();
                    }else{
                        //Show invalid credentials dialog
                    }
                }catch (Exception e){
                    //ignore
                }
            }
        });
        signInViewModel.getProcessing().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    //Show processing
                }else{
                    //Stop processing
                }
            }
        });
        return myView;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: Sign In fragment destroyed");
        signInViewModel.setValidCreds();
        super.onDestroy();
    }

    private void goToUserMainPageFragment(){
        Fragment fragment = new UserMainPageFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.frag_entry_slide,R.anim.frag_exit_slide);
        transaction.replace(R.id.root_frame, fragment);
        transaction.commit();
    }
}
