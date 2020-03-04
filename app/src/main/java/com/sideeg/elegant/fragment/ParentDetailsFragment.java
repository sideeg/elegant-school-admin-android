package com.sideeg.elegant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sideeg.elegant.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ParentDetailsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_student_detials, container, false);
        return root;
    }
}
