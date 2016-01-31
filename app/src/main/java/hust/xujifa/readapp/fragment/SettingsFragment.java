package hust.xujifa.readapp.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import hust.xujifa.readapp.R;

/**
 * Created by xujifa on 2016/1/25.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);


    }
}
