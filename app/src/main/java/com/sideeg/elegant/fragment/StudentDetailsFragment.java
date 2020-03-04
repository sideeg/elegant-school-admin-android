package com.sideeg.elegant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.sideeg.elegant.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class StudentDetailsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_student, container, false);

        EditText studentName = root.findViewById(R.id.add_syudent_Name);
        EditText studentclassname = root.findViewById(R.id.add_student_class);
        Spinner spinnerParent = root.findViewById(R.id.spinner_parent);
        Spinner spinnerSuperVisor = root.findViewById(R.id.spinner_supervisor);


        return root;
    }
}
