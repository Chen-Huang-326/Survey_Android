package com.example.voting.ui.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.voting.R;
import com.example.voting.TypeSelection;
import com.example.voting.ui.survey.SurveyViewModel;

public class SurveyFragment extends Fragment {

    private SurveyViewModel surveyViewModel;
    private ImageButton btnSurvey;

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

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnSurvey = (ImageButton) getActivity().findViewById(R.id.CreateNewSurvey);
        btnSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TypeSelection.class);
                startActivity(intent);
            }
        });
    }
}
