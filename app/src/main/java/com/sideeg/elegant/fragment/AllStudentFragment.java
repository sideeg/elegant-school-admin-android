package com.sideeg.elegant.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sideeg.elegant.R;
import com.sideeg.elegant.adapters.AllStudentAdapter;
import com.sideeg.elegant.model.StudentData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllStudentFragment extends Fragment {


    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AllStudentAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_student, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.student_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        getList();

        FloatingActionButton actionButton = root.findViewById(R.id.butoon_add_student);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, new AddStudentFragment());
                ft.commit();
            }
        });
        return root;
    }

    private void getList() {
        List<StudentData> studends = new ArrayList();
        studends.add(new StudentData("علي احمد", "الخامس عمر", "هند جعفر"));
        studends.add(new StudentData(" احمد حسن", "الثامن اتحاد", "هند جعفر"));
        studends.add(new StudentData("يوسف الصاوي ", "الثالث ابوبكر", "مني الوليد"));
        studends.add(new StudentData("يعقوب احمد", "الرابع عمر", "هند جعفر"));
        studends.add(new StudentData("يعقوب احمد", "الرابع عمر", "هند جعفر"));
        studends.add(new StudentData("محمد احمد احمد", "الرابع عمر", "هند جعفر"));
        studends.add(new StudentData("يعقوب احمد", "الرابع عمر", "هند جعفر"));
        studends.add(new StudentData("يعقوب احمد", "الرابع عمر", "ملاك التاج"));
        studends.add(new StudentData("يعقوب احمد", "الرابع عمر", "هند جعفر"));
        studends.add(new StudentData("يوسف يعقوب", "الرابع عمر", "هند جعفر"));
        studends.add(new StudentData("يعقوب احمد", "الرابع عمر", "اسراء عبد الله"));
        studends.add(new StudentData("احمد محمد", "الرابع عمر", "هند جعفر"));
        studends.add(new StudentData("يعقوب احمد", "الرابع عمر", "هند جعفر"));
        mAdapter = new AllStudentAdapter(studends, getContext());
        recyclerView.setAdapter(mAdapter);


//        NetWorkApis api = ApiClient.getClient(ApiClient.BASE_URL).create(NetWorkApis.class);
//        String temp =new SessionManger(ctx).getAuthToken();
//        Call<CansleResaonResponse> loginCall = api.cancleReson(temp);
//        loginCall.enqueue(new Callback<CansleResaonResponse>() {
//            @Override
//            public void onResponse(Call<CansleResaonResponse> call, Response<CansleResaonResponse> response) {
//                if (response.isSuccessful()) {
//                    if (response.body().isError()) {
//                        Utility.showAlertDialog(ctx.getString(R.string.error), response.body().getMessage(), ctx);
//
//                    } else {
//                        mAdapter = new CancleResonAdapter( response.body().getData(),ctx);
//                        recyclerView.setAdapter(mAdapter);
//                        dialog.show();
//
//                    }
//                } else {
//                    Log.i(TAG, response.errorBody().toString());
//                    Utility.showAlertDialog(ctx.getString(R.string.error), ctx.getString(R.string.servererror), ctx);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CansleResaonResponse> call, Throwable t) {
//                Utility.showAlertDialog(ctx.getString(R.string.error), t.getMessage(), ctx);
//                Utility.printLog(TAG, t.getMessage());
//            }
//        });
//
//    }
    }
}
