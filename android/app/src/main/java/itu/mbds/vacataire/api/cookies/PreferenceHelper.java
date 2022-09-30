package itu.mbds.vacataire.api.cookies;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;

public class PreferenceHelper {

    public static final String KEY_COOKIES = "KEY_COOKIES";

    public static HashSet<String> getCookies(Context context) {
        return getStringSetPreference(context, KEY_COOKIES);
    }

    public static void setCookies(Context context, HashSet<String> cookies) {
        putStringSetPreference(context, KEY_COOKIES, cookies);
    }

    protected static void putStringPreference(Context context, String prefsName, String key, String value) {

        SharedPreferences preferences = context.getSharedPreferences(prefsName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(key, value);
        editor.commit();
    }

    protected String getStringPreference(Context context, String prefsName,
                                         String key) {

        SharedPreferences preferences = context.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE);
        String value = preferences.getString(key, "");
        return value;
    }


    protected static HashSet<String> getStringSetPreference(Context context, String prefsName) {
        SharedPreferences preferences = context.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE);
        HashSet<String> set = new HashSet<>();
        for (String key : preferences.getAll().keySet()) {
            set.add(preferences.getString(key, ""));
        }
        return set;
    }

    protected static void putStringSetPreference(Context context, String prefsName, HashSet<String> cookies) {
        int id = 0;
        for (String value: cookies) {
            putStringPreference(context, prefsName,"key"+id , value);
            id++;
        }
    }


}
