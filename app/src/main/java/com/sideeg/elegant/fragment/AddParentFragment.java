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

public class AddParentFragment extends Fragment  {

    private static final String TAG = "AddParentFragment";
    private Button btnSubmit;
    private EditText parentName, parentPassword,parentPhone;
    private View root;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_add_parent, container, false);
        btnSubmit = root.findViewById(R.id.send_add_parent);
        parentName = root.findViewById(R.id.add_parent_name);
         parentPassword = root.findViewById(R.id.add_parent_password);
         parentPhone = root.findViewById(R.id.add_parent_phone);

        addListenerOnButton();


        return root;
    }


    private void addListenerOnButton() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NetWorkApis api = ApiClient.getClient(ApiClient.BASE_URL).create(NetWorkApis.class);
                String schoolId =new SessionManger(getContext()).getSchoolId();
                Call<BaseRespnse> loginCall = api.CreateParent(schoolId,parentName.getText().toString(), parentPassword.getText().toString(),parentPhone.getText().toString());
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
                                        ft.replace(R.id.container, new AllParentsFragment());
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
