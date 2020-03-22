package com.sideeg.elegant.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sideeg.elegant.NetWorkApis.ApiClient;
import com.sideeg.elegant.NetWorkApis.NetWorkApis;
import com.sideeg.elegant.R;
import com.sideeg.elegant.model.LoginResponse;
import com.sideeg.elegant.utiltiy.SessionManger;
import com.sideeg.elegant.utiltiy.Utility;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG ="LoginActivity" ;

    private EditText schoolName,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        schoolName = findViewById(R.id.usrusr);
        password = findViewById(R.id.pswrd);

        findViewById(R.id.sin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginRequest();
            }
        });
    }


    private void loginRequest() {

        final ProgressDialog loadingBar = ProgressDialog.show(this,"loading","loading.....");
        //loadingBar.setVisibility(View.VISIBLE);
        NetWorkApis api = ApiClient.getClient(ApiClient.BASE_URL).create(NetWorkApis.class);
        String temp = schoolName.getText().toString();
        Call<LoginResponse> loginCall = api.login(schoolName.getText().toString(),password.getText().toString());
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Utility.showAlertDialog(getString(R.string.error), response.body().getMessage(), LoginActivity.this);
                        Log.i(TAG, response.body().getMessage());
                        loadingBar.dismiss();
                    }else {
                        loadingBar.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        new SessionManger(response.body().getData(),getBaseContext());
                        if (response.body().getData().getId() == 2)
                            intent.putExtra("admin","yes");

                        finish();
                        startActivity(intent);
                    }
                } else {
                    Log.i(TAG, response.errorBody().toString());
                    Utility.showAlertDialog(getString(R.string.error), getString(R.string.servererror), LoginActivity.this);
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loadingBar.dismiss();
                Utility.showAlertDialog(getString(R.string.error), t.getMessage(), LoginActivity.this);
                Utility.printLog(TAG,t.getMessage());
            }
        });
    }

    public interface Communicator {
        void getClicked(String phoneNumber);
    }

    private boolean doubleBackToExitPressedOnce;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
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
