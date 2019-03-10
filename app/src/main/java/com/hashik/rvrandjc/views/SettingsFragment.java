package com.hashik.rvrandjc.views;

import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;

import com.hashik.rvrandjc.R;
import com.hashik.rvrandjc.models.Constants;
import com.hashik.rvrandjc.models.MyNotificationManager;
import com.hashik.rvrandjc.models.NotificationSubscriptionManager;

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
                if(switched){
                    myManager.unSubToAllChannels();
                    list.setEnabled(false); //Disable notification channel selection
                    Toast.makeText(getContext(), "Notifications disabled", Toast.LENGTH_SHORT).show();
                }else{
                    myManager.subToAllChannels();
                    list.setEnabled(true); //Enable notification channel selection
                    list.setValueIndex(8); //Set notification selection to all
                    Toast.makeText(getContext(), "Setting notification preference to all", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        list.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int index = list.findIndexOfValue(newValue.toString());

                if (index != -1) {
                    switch (index){
                        case 0: myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 1: myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 2: myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 3: myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 4: myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 5: myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 6: myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 7: myManager.subToChannel(Constants.NOT_CATEGORIES[index]);
                            break;
                        case 8: myManager.subToAllChannels();
                            break;
                    }

                    Toast.makeText(getContext(),"Changed subscription to "+list.getEntries()[index], Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }
}
