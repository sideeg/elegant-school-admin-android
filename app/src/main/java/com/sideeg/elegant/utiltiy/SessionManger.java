package com.sideeg.elegant.utiltiy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import com.sideeg.elegant.App;
import com.sideeg.elegant.activities.SplashActivity;
import com.sideeg.elegant.model.SchoolData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

import androidx.annotation.StringDef;

public class SessionManger {

    private static final String MangerName = "MangerName";
    private static final String MangerPhone = "MangerPhone";
    private static SharedPreferences mPreferences;
    private static String isSessionCreated = "isSessionCreated";
    private SharedPreferences.Editor editor;
    //keys
    private static final String SCHOOL_ID = "schoolId";
    private static final String SchoolAuthToken = "SchoolAuthToken";
    private static String SchoolName = "SchoolName";


    private Context context;

    /**
     * SharedPreferences Key
     */
    private static final String LANGUAGE_KEY = "language_key";


    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ Constant.AR, Constant.EN })
    public @interface LocaleDef {
        String[] SUPPORTED_LOCALES = { Constant.AR, Constant.EN };
    }


    @SuppressLint("CommitPrefEdits")
    public SessionManger(Context context) {
        this.context = context;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = mPreferences.edit();
    }


    public SessionManger(SchoolData schoolData,Context context){
        this.context = context;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = mPreferences.edit();

        editor.putInt(SessionManger.SCHOOL_ID, schoolData.getId());
        editor.putString(SessionManger.SchoolAuthToken, schoolData.getApi_token());
        editor.putString(SessionManger.MangerName, schoolData.getMangerName());
        editor.putString(SessionManger.MangerPhone, schoolData.getMangerPhone());
        editor.putString(SessionManger.SchoolName, schoolData.getSchoolName());

        editor.apply();
        editor.commit();
    }

    public void createSession(Boolean isSessionCreated, String schoolID)
    {
        editor.putString(SessionManger.SCHOOL_ID, schoolID);

        editor.apply();
        editor.commit();
    }




    /**
     * set current pref locale
     */
    public static Context setLocale(Context mContext) {
        return updateResources(mContext, getLanguagePref(mContext));
    }

    /**
     * Set new Locale with context
     */
    public static Context setNewLocale(Context mContext, @LocaleDef String language) {
        setLanguagePref(mContext, language);
        return updateResources(mContext, language);
    }

    /**
     * Get saved Locale from SharedPreferences
     *
     * @param mContext current context
     * @return current locale key by default return english locale
     */
    public static String getLanguagePref(Context mContext) {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return mPreferences.getString(LANGUAGE_KEY, Constant.EN);
    }

    /**
     * set pref key
     */
    private static void setLanguagePref(Context mContext, String localeKey) {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mPreferences.edit().putString(LANGUAGE_KEY, localeKey).apply();
    }

    /**
     * update resource
     */
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }

    /**
     * get current locale
     */
    public static Locale getLocale(Resources res) {
        Configuration config = res.getConfiguration();
        return Build.VERSION.SDK_INT >= 24 ? config.getLocales().get(0) : config.locale;
    }


    public String getSchoolId() {
        return String.valueOf(mPreferences.getInt(SCHOOL_ID, -1));
    }



    public String getSchoolName() {
        return mPreferences.getString(SchoolName, "");
    }

    public String getMangerName() {
        return mPreferences.getString(MangerName, "");
    }

    public String getMangerPhone() {
        return mPreferences.getString(getMangerPhone(), "");
    }




    public SharedPreferences getPreference() {
        return mPreferences;

    }


    public String getSchoolAuthToken() {
        return mPreferences.getString(SchoolAuthToken,"");
    }


    public Boolean getIsSessionCreated() {
        return  mPreferences.getBoolean(SessionManger.isSessionCreated, false);
    }

    public void clearSession() {
        editor.clear();
        editor.apply();
        editor.commit();
        Intent intent = new Intent(context, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
}
