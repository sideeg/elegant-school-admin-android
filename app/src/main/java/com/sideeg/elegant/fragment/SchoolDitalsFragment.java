package com.sideeg.elegant.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.sideeg.elegant.NetWorkApis.ApiClient;
import com.sideeg.elegant.NetWorkApis.NetWorkApis;
import com.sideeg.elegant.R;
import com.sideeg.elegant.model.BaseRespnse;
import com.sideeg.elegant.utiltiy.SessionManger;
import com.sideeg.elegant.utiltiy.StaticElemaents;
import com.sideeg.elegant.utiltiy.Utility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolDitalsFragment extends Fragment {

    private static final String TAG = "SchoolDitalsFragment";
    private EditText schoolName,schoolMangerName,schoolPassword,schoolPhone;
    Button send;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_school, container, false);

         schoolName = root.findViewById(R.id.add_school_Name);
         schoolMangerName = root.findViewById(R.id.add_user_Name);
         schoolPassword = root.findViewById(R.id.add_user_password);
         schoolPhone = root.findViewById(R.id.add_user_phone);
         send = root.findViewById(R.id.school_add_send);

        schoolName.setText(StaticElemaents.getSchoolData().getSchoolName());
        schoolMangerName.setText(StaticElemaents.getSchoolData().getMangerName());
        schoolPassword.setText(StaticElemaents.getSchoolData().getMangerPassowrd());
        schoolPhone.setText(StaticElemaents.getSchoolData().getMangerPhone());

        addListenerOnButton();
        return root;
    }

    private void addListenerOnButton() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NetWorkApis api = ApiClient.getClient(ApiClient.BASE_URL).create(NetWorkApis.class);
                String temp = new SessionManger(getContext()).getSchoolId();
                Call<BaseRespnse> loginCall = api.updateUser("user/"+StaticElemaents.getSchoolData().getId(),schoolMangerName.getText().toString(), schoolName.getText().toString(), schoolPassword.getText().toString(),schoolPhone.getText().toString());
                loginCall.enqueue(new Callback<BaseRespnse>() {
                    @Override
                    public void onResponse(Call<BaseRespnse> call, Response<BaseRespnse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isError()) {
                                Utility.showAlertDialog(getContext().getString(R.string.error), response.body().getMessage(), getContext());

                            } else {
                                final Dialog dialog = new Dialog(getContext());
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setContentView(R.layout.ok_dialog);
                                dialog.setCancelable(true);
                                dialog.setCanceledOnTouchOutside(false);

                                dialog.findViewById(R.id.dialogOkBtn).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                        ft.replace(R.id.container, new FragmentAllSchool());
                                        ft.commit();

                                    }
                                });
                                dialog.show();

                            }
                        } else {
                            Log.i(TAG, response.errorBody().toString());
                            Utility.showAlertDialog(getContext().getString(R.string.error), getContext().getString(R.string.servererror), getContext());

                        }
                    }

                    @Override
                    public void onFailure(Call<BaseRespnse> call, Throwable t) {
                        Utility.showAlertDialog(getContext().getString(R.string.error), t.getMessage(), getContext());
                        Utility.printLog(TAG, t.getMessage());
                    }
                });

            }
        });
    }

}
