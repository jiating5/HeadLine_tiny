package com.jt.headlinemodule.mvvm.view.fargment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jt.headlinemodule.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeadLineFragment extends Fragment {


    public HeadLineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_head_line, container, false);
    }

}
