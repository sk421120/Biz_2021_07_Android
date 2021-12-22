package com.callor.threedayday

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.callor.threedayday.ui.AuthFragmentParent

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

}