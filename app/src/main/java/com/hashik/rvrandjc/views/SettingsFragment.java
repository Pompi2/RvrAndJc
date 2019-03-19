package com.hashik.rvrandjc.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.models.Constants;
import com.hashik.rvrandjc.models.NotificationSubscriptionManager;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;

public class SettingsFragment extends PreferenceFragmentCompat {


    private static final String TAG = "SettingsFragment";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }


    @Override
    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        super.setPreferenceScreen(preferenceScreen);
        if (preferenceScreen != null) {
            int count = preferenceScreen.getPreferenceCount();
            for (int i = 0; i < count; i++)
                preferenceScreen.getPreference(i).setIconSpaceReserved(false);
        }
        final SwitchPreference notSwitch = (SwitchPreference) findPreference("on_off_not");
        notSwitch.setChecked(true);//Turned on initially

        final ListPreference list = (ListPreference) findPreference("not_category");

        //Getting the sub manager
        final NotificationSubscriptionManager myManager = NotificationSubscriptionManager.getInstance();
        notSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean switched = ((SwitchPreference) preference)
                        .isChecked();
                if (switched) {
                    myManager.unSubToAllChannels();
                    list.setEnabled(false); //Disable notification channel selection
                    Toast.makeText(getContext(), "Notifications disabled", Toast.LENGTH_SHORT).show();
                } else {
                    list.setEnabled(true); //Enable notification channel selection
                    list.setValueIndex(getListPreviousValue()); //Set notification selection to the previously selected value
                }
                return true;
            }
        });

        list.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int index = list.findIndexOfValue(newValue.toString());

                if (index != -1) {
                    switch (index) {
                        case 0:
                            myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 1:
                            myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 2:
                            myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 3:
                            myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 4:
                            myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 5:
                            myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 6:
                            myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 7:
                            myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 8:
                            myManager.subToAllChannels();
                            break;
                    }

                    Toast.makeText(getContext(), "Changed subscription to " + list.getEntries()[index], Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }

    private int getListPreviousValue() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String category = sp.getString("not_category", null);
        if (category == null) {
            NotificationSubscriptionManager.getInstance().subToAllChannels();
            Toast.makeText(getContext(), "Now you'll get all notifications", Toast.LENGTH_SHORT).show();
            return 8;
        } else {
            String[] list = getResources().getStringArray(R.array.not_pref); //Getting list of categories
            ArrayList<String> aList = new ArrayList<String>(Arrays.asList(list));
            int index = aList.indexOf(category);
            final String const_category = Constants.NOT_CATEGORIES[index];
            NotificationSubscriptionManager.getInstance().subToChannel(const_category);
            Toast.makeText(getContext(), "Setting notification preference to " + category, Toast.LENGTH_SHORT).show();
            return index;
        }
    }
}
