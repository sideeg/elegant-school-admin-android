package com.sideeg.elegant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sideeg.elegant.R;
import com.sideeg.elegant.fragment.SchoolDitalsFragment;
import com.sideeg.elegant.model.SchoolData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class AllSchoolAdapter extends RecyclerView.Adapter<AllSchoolAdapter.ViewHolder> {


    private List<SchoolData> studentData;
    private Context context;

    public AllSchoolAdapter(List<SchoolData> studentData, Context context) {
        this.studentData = studentData;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.school_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mangerName.setText(studentData.get(i).getMangerName());
        viewHolder.mangerPhone.setText(studentData.get(i).getMangerPhone());
        viewHolder.schoolName.setText(studentData.get(i).getSchoolName());
        viewHolder.mangerPassword.setText(studentData.get(i).getMangerPassowrd());
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft =((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, new SchoolDitalsFragment());
                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView mangerPassword;
        TextView mangerName;
         TextView schoolName;
         TextView mangerPhone;
         LinearLayout layout;
         ViewHolder(@NonNull View itemView) {
            super(itemView);
            schoolName = itemView.findViewById(R.id.item_school_name);
            mangerName = itemView.findViewById(R.id.item_manger_name);
            mangerPhone = itemView.findViewById(R.id.item_manger_phone);
            mangerPassword = itemView.findViewById(R.id.item_manger_password);
            layout = itemView.findViewById(R.id.school_item_layout);
        }
    }
}
