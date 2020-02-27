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
import com.sideeg.elegant.utiltiy.Utility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSuperVisorFragment extends Fragment {

    String TAG = "AddSuperVisorFragment";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_supervisor, container, false);

        final EditText supervisorName = root.findViewById(R.id.add_supervisor_name);
        final EditText supervisorDirection = root.findViewById(R.id.add_supervisor_direction);
        final EditText supervisorPassword = root.findViewById(R.id.add_supervisor_password);
        EditText supervisorPhone = root.findViewById(R.id.add_supervisor_phone);
        Button button = root.findViewById(R.id.send_add_supervisor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetWorkApis api = ApiClient.getClient(ApiClient.BASE_URL).create(NetWorkApis.class);
                String temp =new SessionManger(getContext()).getSchoolId();
                Call<BaseRespnse> loginCall = api.CreateSupervisor(temp,supervisorName.getText().toString(),supervisorDirection.getText().toString(),supervisorPassword.getText().toString(),supervisorPassword.getText().toString());
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
                                        ft.replace(R.id.container, new AllSuperViserFragment());
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

        return root;
    }
}
