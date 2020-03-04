package com.sideeg.elegant.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sideeg.elegant.R;
import com.sideeg.elegant.fragment.AllParentsFragment;
import com.sideeg.elegant.fragment.AllStudentFragment;
import com.sideeg.elegant.fragment.AllSuperViserFragment;
import com.sideeg.elegant.fragment.FragmentAllSchool;
import com.sideeg.elegant.fragment.NotVerifiedStudentFragment;
import com.sideeg.elegant.fragment.VerifiedStudentFragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
        Menu menu = navigationView.getMenu();
        if (getIntent().getStringExtra("admin") != null) {

            menu.removeItem(R.id.nav_all_student);
            menu.removeItem(R.id.nav_all_superviser);
            menu.removeItem(R.id.nav_non_verified_student);
            menu.removeItem(R.id.nav_verified_student);
            menu.removeItem(R.id.nav_all_parent);
            menu.add(12,125,100,"المدارس");
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentAllSchool());
            fragmentTransaction.commit();
        }else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, new AllStudentFragment());
            ft.commit();
        }

    }


    /*
    *********************************************************************************************************************
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//         Handle navigation view item clicks here.
        int id = item.getItemId();

        drawer.closeDrawer(GravityCompat.START);
        if (id == R.id.nav_aboutus) {
            return true;

        }
        else if (id == 125){
            ft.replace(R.id.container, new FragmentAllSchool());
            ft.commit();
            return true;
        } else if (id== R.id.nav_all_student){
            ft.replace(R.id.container, new AllStudentFragment());
            ft.commit();
            return true;

        } else if (id== R.id.nav_all_parent){
            ft.replace(R.id.container, new AllParentsFragment());
            ft.commit();
            return true;

        }else if (id== R.id.nav_all_superviser){
            ft.replace(R.id.container, new AllSuperViserFragment());
            ft.commit();
            return true;

        }else if (id== R.id.nav_verified_student){
            ft.replace(R.id.container, new VerifiedStudentFragment());
            ft.commit();
            return true;

        }else if (id== R.id.nav_non_verified_student){
            ft.replace(R.id.container, new NotVerifiedStudentFragment());
            ft.commit();
            return true;

        }else if (id== R.id.nav_share){
            shareApplication();
            return true;

        }else if (id== R.id.nav_logout){
            return true;

        }else if (id== R.id.nav_call_us){
            Intent phoneIntent = new Intent(Intent.ACTION_CALL);
            phoneIntent.setData(Uri.parse("tel: 0999005491"));

            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                                Manifest.permission.CALL_PHONE)) {
                            // Show an explanation to the user *asynchronously* -- don't block
                            // this thread waiting for the user's response! After the user
                            // sees the explanation, try again to request the permission.
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.CALL_PHONE},
                                    1);
                        } else {
                            // No explanation needed; request the permission
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.CALL_PHONE},
                                    1);

                            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        }


                    }else {
                        startActivity(phoneIntent);
                        Log.i("finished making a call=", "");
                    }
                }

            } catch (ActivityNotFoundException ex) {

                Toast.makeText(MainActivity.this, "call faild,please try again later.", Toast.LENGTH_SHORT).show();
            }
            return true;

        }
        return false;
    }

    private void shareApplication() {
        ApplicationInfo app = getApplicationContext().getApplicationInfo();
        String filePath = app.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);

        // MIME of .apk is "application/vnd.android.package-archive".
        // but Bluetooth does not accept this. Let's use "*/*" instead.
        intent.setType("*/*");

        // Append file and send Intent
        File originalApk = new File(filePath);

        try {
            //Make new directory in new location
            File tempFile = new File(getExternalCacheDir() + "/ExtractedApk");
            //If directory doesn't exists create new
            if (!tempFile.isDirectory())
                if (!tempFile.mkdirs())
                    return;
            //Get application's name and convert to lowercase
            tempFile = new File(tempFile.getPath() + "/" + getString(app.labelRes).replace(" ","").toLowerCase() + ".apk");
            //If file doesn't exists create new
            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }
            //Copy file to new location
            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
            //Open share dialog
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
            startActivity(Intent.createChooser(intent, "Share app via"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean doubleBackToExitPressedOnce;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (drawer.isDrawerOpen(navigationView)){
            drawer.closeDrawer(navigationView);
            return true;
        }else {
        }
        if (doubleBackToExitPressedOnce) {
            //super.onBackPressed();
            //System.exit(0);
            //finishAndRemoveTask();
            finishAffinity();
            //return true;

        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1000);
        return false;
    }
}
