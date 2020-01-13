package com.sideeg.elegant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sideeg.elegant.R;
import com.sideeg.elegant.adapters.AllStudentAdapter;
import com.sideeg.elegant.adapters.AllSupervisorAdapter;
import com.sideeg.elegant.model.StudentData;
import com.sideeg.elegant.model.SuperVisorData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllSuperViserFragment extends Fragment {


    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AllSupervisorAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_supervasior, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.supervisor_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        getList();

        FloatingActionButton actionButton = root.findViewById(R.id.add_supervisor);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, new AddSuperVisorFragment());
                ft.commit();
            }
        });
        return root;
    }

    private void getList() {
        ArrayList<SuperVisorData> supervisor = new ArrayList();
        supervisor.add(new SuperVisorData("علي احمد",   "095851478 "));
        supervisor.add(new SuperVisorData(" احمد حسن", "095821475522 "));
        supervisor.add(new SuperVisorData("يوسف الصاوي ", " 095214785625"));
        supervisor.add(new SuperVisorData("يعقوب احمد", " 09522684552"));
        supervisor.add(new SuperVisorData("يعقوب احمد", "4125874125"));
        supervisor.add(new SuperVisorData("محمد احمد احمد", " 09584214574"));
        supervisor.add(new SuperVisorData("يعقوب احمد", " 095214525147"));
        supervisor.add(new SuperVisorData("يعقوب احمد", "7458245874"));
        supervisor.add(new SuperVisorData("يعقوب احمد", " 235841525"));
        supervisor.add(new SuperVisorData("يوسف يعقوب", " 0965214785" ));
        supervisor.add(new SuperVisorData("يعقوب احمد", " 0932514685"));
        supervisor.add(new SuperVisorData("احمد محمد",  "099952851 "));
        supervisor.add(new SuperVisorData("يعقوب احمد", " 01236521458"));
        mAdapter = new AllSupervisorAdapter(supervisor, getContext());
        recyclerView.setAdapter(mAdapter);

    }
    }
