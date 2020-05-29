package com.example.voting.ui.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.voting.MainActivity;
import com.example.voting.R;
import com.example.voting.Survey;
import com.example.voting.Vote;

public class SurveyFragment extends Fragment {

    private SurveyViewModel surveyViewModel;
    private Button btnSurvey;
    private Button btnVoting;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        surveyViewModel =
                ViewModelProviders.of(this).get(SurveyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_survey, container, false);
        final TextView textView = root.findViewById(R.id.text_survey);
        surveyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    /**
     *  Notice, ONLY registered user can create voting or survey, Visitor have NO ACCESS to them.
     *
     *  In "Create" interface,
     *  Set voting button and survey button separately,
     *  so that users can enter different pages according to different requirements.
     */
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnSurvey = getActivity().findViewById(R.id.survey);
        btnSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.myUsername!=""){
                    Intent intent = new Intent(getActivity(),Survey.class);
                    startActivity(intent);}
                else {
                    Toast.makeText(getActivity(),"No Access!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVoting = getActivity().findViewById(R.id.voting);
        btnVoting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.myUsername!=""){
                    Intent intent = new Intent(getActivity(),Vote.class);
                    startActivity(intent);
                    }
                else {
                    Toast.makeText(getActivity(),"No Access!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
