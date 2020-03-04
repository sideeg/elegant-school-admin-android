package com.sideeg.elegant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sideeg.elegant.R;
import com.sideeg.elegant.utiltiy.StaticElemaents;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SupervisorDetialsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_supervisor, container, false);

        EditText supervisorName = root.findViewById(R.id.add_supervisor_name);
        EditText supervisorPhone = root.findViewById(R.id.add_supervisor_phone);
        EditText superviorDirection = root.findViewById(R.id.add_supervisor_direction);
        EditText supervisorPassword = root.findViewById(R.id.add_supervisor_password);

        supervisorName.setText(StaticElemaents.getSuperVisorData().getSupervisorName());
        supervisorPhone.setText(StaticElemaents.getSuperVisorData().getSupervisorPhone());
        supervisorPassword.setText(StaticElemaents.getSuperVisorData().getPassword());
        superviorDirection.setText(StaticElemaents.getSuperVisorData().getDiscission());

        return root;
    }
}
