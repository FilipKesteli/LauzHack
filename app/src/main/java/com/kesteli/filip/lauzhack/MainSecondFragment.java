package com.kesteli.filip.lauzhack;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kesteli.filip.lauzhack.ocr_ui.OcrMainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainSecondFragment extends Fragment {

    private Button btnNewScientist;

    public MainSecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_second, container, false);
        initViews(view);
        setupListeners();
        return view;
    }

    private void initViews(View view) {
        btnNewScientist = view.findViewById(R.id.btnNewScientist);
    }

    private void setupListeners() {
        btnNewScientist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OcrMainActivity.class);
                startActivity(intent);
            }
        });
    }

}
