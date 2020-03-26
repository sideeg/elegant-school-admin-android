package com.sideeg.elegant.utiltiy;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;
import com.sideeg.elegant.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SuppressLint("SimpleDateFormat")
public class Utility {
    public static void hideSoftKeyboard(AppCompatActivity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static boolean validateEmail(String email) {
        boolean isValid = false;


        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }

        return isValid;
    }

    public static String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);

        return formattedDate;
    }

    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        return telephonyManager.getDeviceId();

        //return "123456789101112DummyId2";
    }
    //Process dialog

    public static ProgressDialog GetProcessDialog(AppCompatActivity activity) {
        // prepare the dialog box
        ProgressDialog dialog = new ProgressDialog(activity, 5);
        // make the progress bar cancelable
        dialog.setCancelable(true);
        // set a message text
        dialog.setMessage("Loading...");

        // show it
        return dialog;
    }

    public RelativeLayout.LayoutParams getRelativelayoutParams(int width, int height) {
        RelativeLayout.LayoutParams lp = null;

        lp = new RelativeLayout.LayoutParams(/*RelativeLayout.LayoutParams.WRAP_CONTENT*/width, /*RelativeLayout.LayoutParams.WRAP_CONTENT*/height);


        return lp;
    }

    public LinearLayout.LayoutParams getLinearLayoutParams(int width, int height) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        return params;
    }

    public static void printLog(String... msg) {
        String str = "";
        for (String i : msg) {
            str = str + "\n" + i;
        }
        if (true) {
            Log.i("DriverApp", str);
        }

    }

    public static void ShowAlert(String msg, Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(context.getResources().getString(R.string.uhOh));
        alertDialogBuilder.setMessage(msg).setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivity = null;
        boolean isNetworkAvail = false;
        try {
            connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connectivity != null) {
                connectivity = null;
            }
        }
        return isNetworkAvail;
    }

//    /**
//     * Show dialog confirm.
//     *
//     * @param activity the activity
//     * @param title    the title
//     * @param message  the message
//     * @param flag     the flag
//     * @return the alert dialog
//     */
//    @SuppressWarnings("deprecation")
//    public AlertDialog showDialogConfirm(final AppCompatActivity activity, String title,
//                                                                String message, final boolean flag) {
//        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
//        // activity.getWindow().setBackgroundDrawableResource(R.color.orange);
//        //alertDialog.setIcon(R.drawable.dialog_icon);
//        alertDialog.setTitle(title);
//        alertDialog.setMessage(message);
//        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(final DialogInterface dialog, final int which) {
//                dialog.dismiss();
//
//            }
//        });
//        alertDialog.setCancelable(false);
//        return alertDialog;
//    }

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDateTimeStringGMT() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDateTimeString = dateFormat.format(date);
        String currentDateTimeWithformat = Utility.changeDateTimeFormate(currentDateTimeString, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss");
        //Log.i("", "onActivityResult flight currentDateTimeWithformat..." + currentDateTimeWithformat);
        Date currentDateTimeDate = Utility.convertStringIntoDate(currentDateTimeWithformat, "yyyy-MM-dd hh:mm:ss");
        //Log.i("", "onActivityResult flight currentDateTimeDate..." + currentDateTimeDate);
        String gmtDateTime = Utility.getLocalTimeToGMT(currentDateTimeDate);
        //Log.i("", "onActivityResult gmtDateTime..............."+gmtDateTime);
        String currentDateTimeGMT = Utility.changeDateFormate(gmtDateTime, "MM/dd/yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss");
        //Log.i("", "onActivityResult currentDateTimeGMT..............."+currentDateTimeGMT);
        return currentDateTimeGMT;
    }

    @SuppressLint("SimpleDateFormat")
    public static String changeDateTimeFormate(String inputDate, String inputFormat, String outFormate) {

        String time24 = null;
        try {
            //String now = new SimpleDateFormat("hh:mm aa").format(new java.util.Date().getTime());
            //System.out.println("onActivityResult time in 12 hour format : " + inputDate);
            SimpleDateFormat inFormat = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outFormat = new SimpleDateFormat(outFormate);
            time24 = outFormat.format(inFormat.parse(inputDate));
            //System.out.println("onActivityResult time in 24 hour format : " + time24);
        } catch (Exception e) {
            //System.out.println("Exception : " + e.getMessage());
        }
        return time24;

    }

    @SuppressLint("SimpleDateFormat")
    public static Date convertStringIntoDate(String dateString, String inputFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(inputFormat);
        //String dateInString = "7-Jun-2013";
        //System.out.println("dateString......."+dateString);
        Date date = null;


        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getLocalTimeToGMT(Date localTime) {
        //Date will return local time in Java
        //Date localTime = new Date();

        //creating DateFormat for converting time from local timezone to GMT
        DateFormat converter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        //getting GMT timezone, you can get any timezone e.g. UTC
        converter.setTimeZone(TimeZone.getTimeZone("GMT"));

        //System.out.println("local time : " + localTime);;
        //System.out.println("time in GMT : " + converter.format(localTime));
        return converter.format(localTime);
        //Read more: http://javarevisited.blogspot.com/2012/04/how-to-convert-local-time-to-gmt-in.html#ixzz2i5QriBRI
    }

    public static String changeDateFormate(String inputDate, String inputFormate, String outputFormate) {
        //String dateStr = "Jul 27, 2011 8:35:29 AM";
        DateFormat readFormat = new SimpleDateFormat(inputFormate);
        DateFormat writeFormat = new SimpleDateFormat(outputFormate);
        Date date = null;
        try {
            date = readFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            @SuppressWarnings("unused")
            String formattedDate = writeFormat.format(date);
        }
        return writeFormat.format(date);

    }


    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static int getWidth(Context mContext) {
        int width = 0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT > 12) {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        } else {
            width = display.getWidth();  // deprecated
        }
        return width;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static int getHeight(Context mContext) {
        int height = 0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT > 12) {
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        } else {
            height = display.getHeight();  // deprecated
        }
        return height;
    }


    /**
     * custom method to show alert dialog
     *
     * @param msg:     String to be set as alert dialog title
     * @param title:   String to be displayed as alert dialog message
     * @param context: contains activity context
     */
    public static void showAlertDialog(String title, String msg, Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        // show it
        alertDialog.show();

    }


    /**
     * to copy stream (Image) from
     *
     * @param: inputStream
     * @param: outputStream
     * @throws: IOException
     */
    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    /**
     * custom method to show alert dialog
     *
     * @param mContext: String to set the title of alert dialog
     */
    public static void aDialogOnPermissionDenied(final Context mContext) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle(mContext.getResources().getString(R.string.uhOh));
        alertDialogBuilder.setMessage(mContext.getResources().getString(R.string.reGrantPermissionMsg));
        alertDialogBuilder.setPositiveButton(mContext.getResources().getString(R.string.appSetting),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
                        Intent settingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri);
                        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mContext.startActivity(settingsIntent);
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        // show it
        alertDialog.show();
    }
    /************************************************************/




    /******************************************************/


    public static void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

    /******************************************************/

    public static String getCurrentGmtTime() {
        String curentdateTime = "";
        Calendar calendar = Calendar.getInstance(Locale.US);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        curentdateTime = sdf.format(date);
        return curentdateTime;
    }

    /******************************************************/

    public static String getCurrentTimeFormat() {
        String curentdateTime = "";
        Date date = new Date();

        String strDateFormat = "hh:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        curentdateTime = sdf.format(date);
        return curentdateTime;
    }

    /*****************************************************/

    public static String getStringRequest(String url) {
        String response = "";
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url(url).build();

        try {
            Response getResponse = client.newCall(request).execute();
            response = getResponse.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /************************************************************/

    /**************************************************************************************************************/
/**************************************************************************************************************/
    /**
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return
     */

    public static String sentingDateFormat(int year, int monthOfYear, int dayOfMonth) {
        monthOfYear = monthOfYear + 1;
        String month = "" + monthOfYear;
        String day = "" + dayOfMonth;
        if (monthOfYear <= 9) {
            month = 0 + month;
        }
        if (dayOfMonth <= 9) {
            day = 0 + day;
        }
        String dateFormat = month + "/" + day + "/" + year;

        return dateFormat;
    }

    /****************************************************************************************************/
    public static void  showSettingsAlert(final AppCompatActivity activity) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);

        // Setting Dialog Title
        alertDialog.setTitle(activity.getString(R.string.updaterequest));
        alertDialog.setCancelable(false);

        // Setting Dialog Message
        alertDialog.setMessage(activity.getString(R.string.pleaseupdate));


        // On pressing Settings button
        alertDialog.setPositiveButton(activity.getString(R.string.close), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);

            }
        });


        // Showing Alert Message
        try {
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean isMyServiceRunning(Class<?> serviceClass, Context activity) {
        ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**********************************************************************************************/
    public static Dialog showPopupWithOneButton(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_dialog_with_1button);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        //setLanguageForDialog(dialog);
        TextView title_popup = dialog.findViewById(R.id.title_popup);
        TextView text_for_popup = dialog.findViewById(R.id.text_for_popup);
        TextView yes_button = dialog.findViewById(R.id.yes_button);
        /*setTypefaceMuliRegular(context,title_popup);
        setTypefaceMuliRegular(context,text_for_popup);
		setTypefaceMuliBold(context,yes_button);*/
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return dialog;
    }


    public static String currentVersion(Context context) {
        String currentVersion = "";
        try {
            currentVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            Log.d("currentVersion", currentVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return currentVersion;
    }

    public static String changeAppLanguage(String lang, Context ctx) {
        if (lang.equalsIgnoreCase(""))
            return lang;

        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        ctx.getResources().updateConfiguration(config, ctx.getResources().getDisplayMetrics());
        return lang;
    }

    public static Locale getCurrentLocale(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }

    public static String round(double value) {
        @SuppressLint("DefaultLocale") String s = String.format("%.2f", value);
        Utility.printLog("rounded value=" + s);
        return s;
    }

    public static String convertToEnglish(String value) {
        String newValue = (((((((((((value + "")
                .replaceAll("١", "1")).replaceAll("٢", "2"))
                .replaceAll("٣", "3")).replaceAll("٤", "4"))
                .replaceAll("٥", "5")).replaceAll("٦", "6"))
                .replaceAll("٧", "7")).replaceAll("٨", "8"))
                .replaceAll("٩", "9")).replaceAll("٠", "0"));
        return newValue;
    }

    public static void showSnackBar(View view, Context context, String message) {
        //Typeface muliRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Hind-Regular.ttf");
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundResource(R.drawable.orange_gradient_without_curve);
//        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(context.getResources().getColor(R.color.white));
//        textView.setMaxLines(5);
//        //textView.setTypeface(muliRegular);
//        textView.setTextSize(25);
//        snackbar.show();
    }

    public static boolean reputeMissingPermeation(Activity context){
        RuntimePermissionHelper helper =RuntimePermissionHelper.getInstance(context);
        if (helper.isAllPermissionAvailable()){
            return true;
        }else {
            helper.requestPermissionsIfDenied();
        }
        return false;
    }
    public static boolean isLocationEnabled(Context context) {
        LocationManager mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
