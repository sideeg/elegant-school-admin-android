package com.sideeg.elegant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sideeg.elegant.R;
import com.sideeg.elegant.fragment.SupervisorDetialsFragment;
import com.sideeg.elegant.model.SuperVisorData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class AllSupervisorAdapter extends RecyclerView.Adapter<AllSupervisorAdapter.ViewHolder> {


    private List<SuperVisorData> studentData;
    private Context context;

    public AllSupervisorAdapter(List<SuperVisorData> studentData, Context context) {
        this.studentData = studentData;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.supervisor_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.supervisorName.setText(studentData.get(i).getSupervisorName());
        viewHolder.supervisorPhone.setText(studentData.get(i).getSupervisorPhone());
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction ft = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, new SupervisorDetialsFragment());
                ft.commit();


            }
        });

    }

    @Override
    public int getItemCount() {
        return studentData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
         TextView supervisorName;
         TextView supervisorPhone;
         LinearLayout layout;
         ViewHolder(@NonNull View itemView) {
            super(itemView);
            supervisorPhone = itemView.findViewById(R.id.item_supervisor_phone);
            supervisorName = itemView.findViewById(R.id.item_supervisor_name);
            layout = itemView.findViewById(R.id.supervisor_item_layout);
        }
    }
}
