package com.reminderbeep;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by nbp184 on 2017/02/07.
 */
public class SettingsFragment extends PreferenceFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
