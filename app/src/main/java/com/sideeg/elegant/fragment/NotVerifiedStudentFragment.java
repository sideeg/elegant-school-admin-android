package com.sideeg.elegant.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sideeg.elegant.NetWorkApis.ApiClient;
import com.sideeg.elegant.NetWorkApis.NetWorkApis;
import com.sideeg.elegant.R;
import com.sideeg.elegant.adapters.AllStudentAdapter;
import com.sideeg.elegant.model.getStudentResponse;
import com.sideeg.elegant.utiltiy.SessionManger;
import com.sideeg.elegant.utiltiy.Utility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotVerifiedStudentFragment extends Fragment {

    private static final String TAG = "AllStudentFragment";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AllStudentAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sup_student, container, false);

        recyclerView = root.findViewById(R.id.sub_student_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        getList();

        return root;
    }

    private void getList() {


        NetWorkApis api = ApiClient.getClient(ApiClient.BASE_URL).create(NetWorkApis.class);
        String temp =new SessionManger(getContext()).getSchoolId();
        Call<getStudentResponse> loginCall = api.studentWithNoSupervisor(temp);
        loginCall.enqueue(new Callback<getStudentResponse>() {
            @Override
            public void onResponse(Call<getStudentResponse> call, Response<getStudentResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Utility.showAlertDialog(getContext().getString(R.string.error), response.body().getMessage(), getContext());

                    } else {
                        mAdapter = new AllStudentAdapter( response.body().getData(),getContext());
                        recyclerView.setAdapter(mAdapter);

                    }
                } else {
                    Log.i(TAG, response.errorBody().toString());
                    Utility.showAlertDialog(getContext().getString(R.string.error), getContext().getString(R.string.servererror), getContext());

                }
            }

            @Override
            public void onFailure(Call<getStudentResponse> call, Throwable t) {
                Utility.showAlertDialog(getContext().getString(R.string.error), t.getMessage(), getContext());
                Utility.printLog(TAG, t.getMessage());
            }
        });

    }
}
