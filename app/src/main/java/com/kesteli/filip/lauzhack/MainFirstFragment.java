package com.kesteli.filip.lauzhack;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFirstFragment extends Fragment {

    private DatabaseReference databaseReference;
    private DatabaseReference childTexts;
    private DatabaseReference childText;

    private Button btnWriter;
    private EditText etWriterSummarize;

    public MainFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_first, container, false);
        setupFirebase();
        initViews(view);
        setupListeners();
        return view;
    }

    private void setupFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void initViews(View view) {
        btnWriter = view.findViewById(R.id.btnNewWriter);
        etWriterSummarize = view.findViewById(R.id.etWriterSummarize);
    }

    private void setupListeners() {
        btnWriter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTextToFirebase();
//                showDialogWritterSummary();
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private Map<String, Integer> keywordsBeginningMap = new HashMap<>();

    /**
     * Label is 1 for the writers
     */
    private void addTextToFirebase() {
        childTexts = databaseReference.child("Texts");
        childText = childTexts.child("Text");
        childText.child("content").setValue(etWriterSummarize.getText().toString());
        childText.child("label").setValue(1);
        keywordsBeginningMap.put("ex", 0);
        childText.child("keywords").setValue(keywordsBeginningMap);
    }
}
