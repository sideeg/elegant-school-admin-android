package com.sideeg.elegant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sideeg.elegant.R;
import com.sideeg.elegant.adapters.AllSchoolAdapter;
import com.sideeg.elegant.adapters.AllSupervisorAdapter;
import com.sideeg.elegant.model.SchoolData;
import com.sideeg.elegant.model.SuperVisorData;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentAllSchool extends Fragment {


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
        supervisor.add(new SchoolData("الزهراء الخاصة اساس ","اسحاق عمر",   "095851478 ","gfh55"));
        supervisor.add(new SchoolData("عمر ابن الخطاب الاساسية بنين  ","رشيد سعيد", "095821475522 ","sideeg4588"));
        supervisor.add(new SchoolData(" الاتحاد الثانوية ", "ابراهيم صديق"," 095214785625","4785"));
        supervisor.add(new SchoolData(" مدارس الصفا الخاصة", "حسن يعقوب"," 09522684552","test535"));
        supervisor.add(new SchoolData(" ابن رشد ", "اسماعيل ازهري","4125874125","lap1458"));
        supervisor.add(new SchoolData("الموهبيين ","المنذر عبد الملك", " 09584214574","458df"));
        supervisor.add(new SchoolData("المجلس الافريقي ","يوسف شاهين", " 095214525147","45577"));
        supervisor.add(new SchoolData(" ", "علي جمعة","7458245874","lap147852"));
        supervisor.add(new SchoolData("يعقوب احمد","عبد اللطيف اسحاق", " 235841525","199725"));
        supervisor.add(new SchoolData("يوسف يعقوب", "شيماء الخالدي"," 0965214785" ,""));
        supervisor.add(new SchoolData("يعقوب احمد", "نهال عبد الغني"," 0932514685",""));
        supervisor.add(new SchoolData("احمد محمد",  "ابتسام يوسف","099952851 ",""));
        supervisor.add(new SchoolData("يعقوب احمد", "الهادي الخضر"," 01236521458",""));
        mAdapter = new AllSchoolAdapter(supervisor, getContext());
        recyclerView.setAdapter(mAdapter);

    }
}


