/*
 * Copyright (C) 2017-2022 crDroid Android Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.crdroid.settings;

import android.os.Bundle;
import android.os.UserHandle;
import android.content.Context;
import android.provider.Settings;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;

import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;
 
 public class crDroidSettingsLayout extends SettingsPreferenceFragment {
 
     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setSigmaDashboardStyle();
         addPreferencesFromResource(R.xml.matrixx_dashboard);
     }

    public void onResume() {
        super.onResume();
        // hideToolbar();
        setSigmaDashboardStyle();
    }

    private void setSigmaDashboardStyle() {
        int mDashBoardStyle = getSettingsDashboardStyle();
        final PreferenceScreen mScreen = getPreferenceScreen();
        final int mCount = mScreen.getPreferenceCount();
        for (int i = 0; i < mCount; i++) {
            final Preference mPreference = mScreen.getPreference(i);

            String mKey = mPreference.getKey();

            if (mKey == null) continue;

            if (mKey.equals("sigma_settings_logo")) {
                mPreference.setLayoutResource(R.layout.sigma_settings_logo);
                continue;
            }

            if (mDashBoardStyle == 1 ){
                if (mKey.equals("ui_settings_category")) {
                     mPreference.setLayoutResource(R.layout.dot_dashboard_preference_top);
                 } else if (mKey.equals("about_matrixx")) {
                     mPreference.setLayoutResource(R.layout.dot_dashboard_preference_bottom);
                 } else {
                     mPreference.setLayoutResource(R.layout.dot_dashboard_preference_middle);
                 }
             } else if (mDashBoardStyle == 2) {
                 mPreference.setLayoutResource(R.layout.nad_dashboard_preference);
             } else  if (mDashBoardStyle == 3){
                             if (mKey.equals("ui_settings_category")) {
                     mPreference.setLayoutResource(R.layout.sigma_toolbox_preference_top);
                 } else if (mKey.equals("about_matrixx")) {
                     mPreference.setLayoutResource(R.layout.sigma_toolbox_preference_bottom);
                 } else {
                     mPreference.setLayoutResource(R.layout.sigma_toolbox_preference_middle);
                 }
             }
         }
     }

     private int getSettingsDashboardStyle() {
         return Settings.System.getIntForUser(getContext().getContentResolver(),
                 Settings.System.SETTINGS_DASHBOARD_STYLE, 2, UserHandle.USER_CURRENT);
     }
 
     @Override
     public int getMetricsCategory() {
         return MetricsProto.MetricsEvent.CRDROID_SETTINGS;
     }
}
