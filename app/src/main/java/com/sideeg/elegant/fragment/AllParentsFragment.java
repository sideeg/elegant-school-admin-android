package com.sideeg.elegant.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sideeg.elegant.NetWorkApis.ApiClient;
import com.sideeg.elegant.NetWorkApis.NetWorkApis;
import com.sideeg.elegant.R;
import com.sideeg.elegant.adapters.AllParentAdapter;
import com.sideeg.elegant.adapters.AllStudentAdapter;
import com.sideeg.elegant.model.getParentResponse;
import com.sideeg.elegant.model.getStudentResponse;
import com.sideeg.elegant.utiltiy.SessionManger;
import com.sideeg.elegant.utiltiy.Utility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllParentsFragment extends Fragment {


    private static final String TAG = "AllStudentFragment";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AllParentAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_parent, container, false);

        recyclerView =  root.findViewById(R.id.parents_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        getList();

        FloatingActionButton actionButton = root.findViewById(R.id.butoon_add_parent);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, new AddParentFragment());
                ft.commit();
            }
        });
        return root;
    }

    private void getList() {


        NetWorkApis api = ApiClient.getClient(ApiClient.BASE_URL).create(NetWorkApis.class);
        String temp =new SessionManger(getContext()).getSchoolId();
        Call<getParentResponse> loginCall = api.getparent(temp);
        loginCall.enqueue(new Callback<getParentResponse>() {
            @Override
            public void onResponse(Call<getParentResponse> call, Response<getParentResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Utility.showAlertDialog(getContext().getString(R.string.error), response.body().getMessage(), getContext());

                    } else {
                        mAdapter = new AllParentAdapter( response.body().getData(),getContext());
                        recyclerView.setAdapter(mAdapter);

                    }
                } else {
                    Log.i(TAG, response.errorBody().toString());
                    Utility.showAlertDialog(getContext().getString(R.string.error), getContext().getString(R.string.servererror), getContext());

                }
            }

            @Override
            public void onFailure(Call<getParentResponse> call, Throwable t) {
                Utility.showAlertDialog(getContext().getString(R.string.error), t.getMessage(), getContext());
                Utility.printLog(TAG, t.getMessage());
            }
        });

    }
    }

