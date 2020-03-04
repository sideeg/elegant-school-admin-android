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

public class SchoolDitalsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_school, container, false);

        EditText schoolName = root.findViewById(R.id.add_school_Name);
        EditText schoolMangerName = root.findViewById(R.id.add_user_Name);
        EditText schoolPassword = root.findViewById(R.id.add_user_password);
        EditText schoolPhone = root.findViewById(R.id.add_user_phone);

        schoolName.setText(StaticElemaents.getSchoolData().getSchoolName());
        schoolMangerName.setText(StaticElemaents.getSchoolData().getMangerName());
        schoolPassword.setText(StaticElemaents.getSchoolData().getMangerPassowrd());
        schoolPhone.setText(StaticElemaents.getSchoolData().getMangerPhone());
        return root;
    }
}
