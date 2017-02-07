package com.reminderbeep;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by nbp184 on 2017/02/07.
 */
public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
