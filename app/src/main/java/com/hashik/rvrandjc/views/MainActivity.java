package com.hashik.rvrandjc.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Notification;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hashik.rvrandjc.adapters.MainFragmentAdapter;
import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.models.Constants;
import com.hashik.rvrandjc.models.NotificationSubscriptionManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private AHBottomNavigation bottomNavigation;
    private ViewPager mainViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assignments
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        mainViewPager = (ViewPager) findViewById(R.id.main_viewpager);

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
}