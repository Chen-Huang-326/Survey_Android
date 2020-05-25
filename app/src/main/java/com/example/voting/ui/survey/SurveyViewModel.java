package com.example.voting.ui.survey;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SurveyViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SurveyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Create yours!");
    }

    public LiveData<String> getText() {
        return mText;
    }


}