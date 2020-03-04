package com.sideeg.elegant.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.sideeg.elegant.R;
import com.sideeg.elegant.fragment.StudentDetailsFragment;
import com.sideeg.elegant.model.StudentData;
import com.sideeg.elegant.utiltiy.StaticElemaents;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class AllStudentAdapter extends RecyclerView.Adapter<AllStudentAdapter.ViewHolder> {


    private List<StudentData> studentData;
    private Context context;

    public AllStudentAdapter(List<StudentData> studentData, Context context) {
        this.studentData = studentData;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.studentName.setText(studentData.get(i).getStudentName());
        if (studentData.get(i).getStudentSupervisor() !=null)
            viewHolder.studentSuperVisorName.setText(studentData.get(i).getStudentSupervisor().getSupervisorName());
        viewHolder.studentClassName.setText(studentData.get(i).getStudentClassName());
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticElemaents.setStudentData(studentData.get(i));
                FragmentTransaction ft =((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, new StudentDetailsFragment());
                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
         TextView studentName;
         TextView studentClassName;
         TextView studentSuperVisorName;
         LinearLayout layout;
         ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentClassName = itemView.findViewById(R.id.item_student_class);
            studentName = itemView.findViewById(R.id.item_student_name);
            studentSuperVisorName = itemView.findViewById(R.id.item_student_supervisor);
            layout = itemView.findViewById(R.id.student_item_layout);
        }
    }
}
