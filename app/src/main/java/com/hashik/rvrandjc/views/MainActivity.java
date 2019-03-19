package com.hashik.rvrandjc.views;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.adapters.MainFragmentAdapter;
import com.hashik.rvrandjc.models.Constants;
import com.hashik.rvrandjc.models.NotificationSubscriptionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";
    private AHBottomNavigation bottomNavigation;
    private ViewPager mainViewPager;
    public ProgressBar progressBar;

    @Override
    public void onBackPressed() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        boolean handled = false;
        if (bottomNavigation.getCurrentItem() == 0) { // Handle back button by fragment only if it's pressed on home item
            for (Fragment f : fragmentList) {
                if (f instanceof SemesterGradesFragment) {
                    handled = true;
                    ((SemesterGradesFragment) f).onBackPressed();
                    return;
                }else if (f instanceof InternalMarksFragment) {
                    handled = true;
                    ((InternalMarksFragment) f).onBackPressed();
                    return;
                }else if(f instanceof AttendanceReportFragment){
                    handled = true;
                    ((AttendanceReportFragment) f).onBackPressed();
                    return;
                }
            }
        }

        if(!handled){ // If the fragments can't handle it, the main activity should handle it
            //Dialog on click listener
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (DialogInterface.BUTTON_POSITIVE == which) {
                        MainActivity.super.onBackPressed();
                    }
                }
            };
            //Showing the alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assignments
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        mainViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        //Initializations
        initializeBottomNav();

        //Adapters
        mainViewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));

        //Listeners
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                /*This method will be invoked when the current page is scrolled,
                either as part of a programmatically initiated smooth scroll or a user initiated touch scroll*/
                bottomNavigation.setCurrentItem(position); //Changing the tab to the state(position)
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //Getting shard preferences of notification to assign the channel every time the apps starts to provide consistency, reliability of notification
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if(sp.getBoolean("firstrun",true)){
            //Doing first run stuff
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("on_off_not",true);
            editor.putBoolean("firstrun",false); //Setting first time running flag to false
            editor.apply();
            setNotificationPreferences(sp);
        }
        if(sp.getBoolean("on_off_not",false)){
            setNotificationPreferences(sp);
        }else{
            Toast.makeText(this, "App notifications are turned off", Toast.LENGTH_SHORT).show();
        }
    }

    private void setNotificationPreferences(SharedPreferences sp) {
        Log.d(TAG, "setNotificationPreferences: Changing Notification preferences");
        final String category = sp.getString("not_category",null);
        if(category == null){
            sp.edit().putString("not_category","All").apply();
            NotificationSubscriptionManager.getInstance().subToAllChannels();
        }else{
            String[] list  = getResources().getStringArray(R.array.not_pref);
            ArrayList<String> aList = new ArrayList<String>(Arrays.asList(list));
            int index = aList.indexOf(category);
            final String const_category = Constants.NOT_CATEGORIES[index];
            NotificationSubscriptionManager.getInstance().subToChannel(const_category);
        }
    }

    private void initializeBottomNav() {

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.Home, R.drawable.ic_home, R.color.colorAccent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.notification, R.drawable.ic_notification, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.Settings, R.drawable.ic_settings, R.color.colorAccent);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.About, R.drawable.ic_about, R.color.colorAccent);
        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#F5F5F5"));

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                mainViewPager.setCurrentItem(position); //OnClick of tab setting the current view pager to the position
                return true;
            }
        });

        //Settings a notification
        bottomNavigation.setNotification("1",1);
    }

    public void diableBottomBarButtons() {
        if(bottomNavigation != null){
            bottomNavigation.disableItemAtPosition(0);
            bottomNavigation.disableItemAtPosition(1);
            bottomNavigation.disableItemAtPosition(2);
            bottomNavigation.disableItemAtPosition(3);
        }
    }

    public void enableBottomBarButtons() {
        if (bottomNavigation != null) {
            bottomNavigation.enableItemAtPosition(0);
            bottomNavigation.enableItemAtPosition(1);
            bottomNavigation.enableItemAtPosition(2);
            bottomNavigation.enableItemAtPosition(3);
        }
    }
}