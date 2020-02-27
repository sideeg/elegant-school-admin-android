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
import com.sideeg.elegant.adapters.AllSchoolAdapter;
import com.sideeg.elegant.adapters.AllStudentAdapter;
import com.sideeg.elegant.adapters.AllSupervisorAdapter;
import com.sideeg.elegant.model.SchoolData;
import com.sideeg.elegant.model.SuperVisorData;
import com.sideeg.elegant.model.getStudentResponse;
import com.sideeg.elegant.model.getUsersResponse;
import com.sideeg.elegant.utiltiy.SessionManger;
import com.sideeg.elegant.utiltiy.Utility;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAllSchool extends Fragment {


    private static final String TAG = "FragmentAllSchool";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AllSchoolAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_school, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.school_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        getList();

        FloatingActionButton actionButton = root.findViewById(R.id.butoon_add_school);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, new AddSchoolFragment());
                ft.commit();
            }
        });
        return root;
    }

    private void getList() {
        ArrayList<SchoolData> supervisor = new ArrayList();
//        supervisor.add(new SchoolData("الزهراء الخاصة اساس ","اسحاق عمر",   "095851478 ","gfh55"));
//        supervisor.add(new SchoolData("عمر ابن الخطاب الاساسية بنين  ","رشيد سعيد", "095821475522 ","sideeg4588"));
//        supervisor.add(new SchoolData(" الاتحاد الثانوية ", "ابراهيم صديق"," 095214785625","4785"));
//        supervisor.add(new SchoolData(" مدارس الصفا الخاصة", "حسن يعقوب"," 09522684552","test535"));
//        supervisor.add(new SchoolData(" ابن رشد ", "اسماعيل ازهري","4125874125","lap1458"));
//        supervisor.add(new SchoolData("الموهبيين ","المنذر عبد الملك", " 09584214574","458df"));
//        supervisor.add(new SchoolData("المجلس الافريقي ","يوسف شاهين", " 095214525147","45577"));
//        supervisor.add(new SchoolData(" ", "علي جمعة","7458245874","lap147852"));
//        supervisor.add(new SchoolData("يعقوب احمد","عبد اللطيف اسحاق", " 235841525","199725"));
//        supervisor.add(new SchoolData("يوسف يعقوب", "شيماء الخالدي"," 0965214785" ,""));
//        supervisor.add(new SchoolData("يعقوب احمد", "نهال عبد الغني"," 0932514685",""));
//        supervisor.add(new SchoolData("احمد محمد",  "ابتسام يوسف","099952851 ",""));
//        supervisor.add(new SchoolData("يعقوب احمد", "الهادي الخضر"," 01236521458",""));
//        mAdapter = new AllSchoolAdapter(supervisor, getContext());
//        recyclerView.setAdapter(mAdapter);

        NetWorkApis api = ApiClient.getClient(ApiClient.BASE_URL).create(NetWorkApis.class);
        String temp =new SessionManger(getContext()).getSchoolId();
        Call<getUsersResponse> loginCall = api.getUser();
        loginCall.enqueue(new Callback<getUsersResponse>() {
            @Override
            public void onResponse(Call<getUsersResponse> call, Response<getUsersResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Utility.showAlertDialog(getContext().getString(R.string.error), response.body().getMessage(), getContext());

                    } else {
                        mAdapter = new AllSchoolAdapter( response.body().getData(),getContext());
                        recyclerView.setAdapter(mAdapter);

                    }
                } else {
                    Log.i(TAG, response.errorBody().toString());
                    Utility.showAlertDialog(getContext().getString(R.string.error), getContext().getString(R.string.servererror), getContext());

                }
            }

            @Override
            public void onFailure(Call<getUsersResponse> call, Throwable t) {
                Utility.showAlertDialog(getContext().getString(R.string.error), t.getMessage(), getContext());
                Utility.printLog(TAG, t.getMessage());
            }
        });

    }
}


