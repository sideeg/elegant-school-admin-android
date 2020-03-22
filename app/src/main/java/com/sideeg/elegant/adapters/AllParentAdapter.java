package com.sideeg.elegant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sideeg.elegant.R;
import com.sideeg.elegant.fragment.ParentDetailsFragment;
import com.sideeg.elegant.fragment.SchoolDitalsFragment;
import com.sideeg.elegant.model.ParentData;
import com.sideeg.elegant.model.SchoolData;
import com.sideeg.elegant.utiltiy.StaticElemaents;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class AllParentAdapter extends RecyclerView.Adapter<AllParentAdapter.ViewHolder> {


    private List<ParentData> parentData;
    private Context context;

    public AllParentAdapter(List<ParentData> parentDataList, Context context) {
        this.parentData = parentDataList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.parentName.setText(parentData.get(i).getName());
        viewHolder.parentPhone.setText(parentData.get(i).getPhone());
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticElemaents.setParentData(parentData.get(i));
                FragmentTransaction ft =((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, new ParentDetailsFragment());
                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return parentData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView parentName;
         TextView parentPhone;
         LinearLayout layout;
         ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentName = itemView.findViewById(R.id.item_parent_name);
            parentPhone = itemView.findViewById(R.id.item_parent_phone);
            layout = itemView.findViewById(R.id.parent_item_layout);

        }
    }
}
