package com.sideeg.elegant;

import android.app.Application;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.sideeg.elegant.NetWorkApis.ApiClient;
import com.sideeg.elegant.utiltiy.SessionManger;


import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.sender.HttpSender;

import java.util.Arrays;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

@ReportsCrashes(formUri = ApiClient.BASE_URL+"error",
        reportType = HttpSender.Type.FORM,
        httpMethod = HttpSender.Method.POST,
        formUriBasicAuthLogin = "GENERATED_USERNAME_WITH_WRITE_PERMISSIONS",
        formUriBasicAuthPassword = "GENERATED_PASSWORD",
        customReportContent = {
                ReportField.STACK_TRACE,
                ReportField.APPLICATION_LOG,
                ReportField.LOGCAT,
                ReportField.APP_VERSION_CODE,
                ReportField.APP_VERSION_NAME,
                ReportField.ANDROID_VERSION,
                ReportField.PACKAGE_NAME,
                ReportField.REPORT_ID,
                ReportField.BUILD,
                ReportField.PHONE_MODEL,
                ReportField.AVAILABLE_MEM_SIZE,
                ReportField.DEVICE_FEATURES,
                ReportField.DEVICE_ID,
                ReportField.USER_IP,
        },
        mode = ReportingInteractionMode.SILENT
)


public class App extends Application implements LifecycleObserver {


    private static PubNub mPubNub;
    public static PNConfiguration pnConfiguration;
    public static Context context;
    private static final String TAG = "Application";
    private static final String PREF_NAME = "Driver SettingsData";
    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor editor;

    @Override
    public Context getBaseContext() {
        context = super.getBaseContext();
        return context;
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static SharedPreferences getAppPrefrnce() {
        return mPreferences;
    }

    public static SharedPreferences.Editor getAppPrefrnceEditor() {
        return editor;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ACRA.init(this);
        Log.v(TAG, ACRA.LOG_TAG);
        try {
            context = getApplicationContext();
            mPreferences = context.getSharedPreferences(PREF_NAME, 0);
            editor = mPreferences.edit();

        } catch (Exception e) {
            Log.i(TAG, e.getLocalizedMessage());
        }
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.i(TAG, "Application Terminated");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onMoveToForeground() {
        // app moved to foreground
        Log.i(TAG, "onMoveToForeground");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onMoveToBackground() {
        // app moved to background
        Log.i(TAG, "onMoveToBackground");
    }
    public static PubNub getInstancePubnub() {
        return mPubNub;
    }
}

