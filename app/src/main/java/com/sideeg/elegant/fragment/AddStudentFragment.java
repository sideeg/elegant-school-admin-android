package com.sideeg.elegant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sideeg.elegant.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class AddStudentFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_student, container, false);
        return root;
    }
}
