package com.example.voting.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    private Button btnShowUsername;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final TextView show_username = getActivity().findViewById(R.id.show_username);
        final EditText show_email = getActivity().findViewById(R.id.input_email);

        /**
         *  Show users' username and email & ONLY registered users can show them
         */
        btnShowUsername = getActivity().findViewById(R.id.show_username_button);
        btnShowUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MainActivity.myUsername.equals("")){
                    show_username.setText(MainActivity.myUsername);
                    show_email.setText(MainActivity.myEmail);
                } else {
                    Toast.makeText(getActivity(),"Please register first!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        /**
         *  Set users can only choose one gender & ONLY registered users can set
         */
        CheckBox female = getActivity().findViewById(R.id.female_checkBox);
        CheckBox male = getActivity().findViewById(R.id.male_checkBox);
        CheckBox transgender = getActivity().findViewById(R.id.transgender_checkBox);

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MainActivity.myUsername.equals("")){
                    CheckBox male = getActivity().findViewById(R.id.male_checkBox);
                    CheckBox transgender = getActivity().findViewById(R.id.transgender_checkBox);
                    male.setChecked(false);
                    transgender.setChecked(false);
                } else {
                    CheckBox female = getActivity().findViewById(R.id.female_checkBox);
                    female.setChecked(false);
                    Toast.makeText(getActivity(),"Please register first!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MainActivity.myUsername.equals("")){
                    CheckBox female = getActivity().findViewById(R.id.female_checkBox);
                    CheckBox transgender = getActivity().findViewById(R.id.transgender_checkBox);
                    female.setChecked(false);
                    transgender.setChecked(false);
                } else {
                    CheckBox male = getActivity().findViewById(R.id.male_checkBox);
                    male.setChecked(false);
                    Toast.makeText(getActivity(),"Please register first!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        transgender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MainActivity.myUsername.equals("")){
                    CheckBox female = getActivity().findViewById(R.id.female_checkBox);
                    CheckBox male = getActivity().findViewById(R.id.male_checkBox);
                    male.setChecked(false);
                    female.setChecked(false);
                } else {
                    CheckBox transgender = getActivity().findViewById(R.id.transgender_checkBox);
                    transgender.setChecked(false);
                    Toast.makeText(getActivity(),"Please register first!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        final ImageView save_email = getActivity().findViewById(R.id.edit_e_mail);

        save_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input_email = getActivity().findViewById(R.id.input_email);
                TextView saved_email = getActivity().findViewById(R.id.saved_email);
                if (!MainActivity.myUsername.equals("")){
                    if (saved_email.getVisibility()==View.INVISIBLE){
                        saved_email.setText(input_email.getText());
                        input_email.setVisibility(View.INVISIBLE);
                        saved_email.setVisibility(View.VISIBLE);}
                    if (input_email.getVisibility()==View.INVISIBLE){
                        input_email.setText(saved_email.getText());
                        input_email.setVisibility(View.VISIBLE);
                        saved_email.setVisibility(View.INVISIBLE);}
                } else {
                    Toast.makeText(getActivity(),"Please register first!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        /**
         *  Settings allow users can change their own photo by shooting from local camera or import from local album
         */
        
        ImageButton change_photo = getActivity().findViewById(R.id.changePhoto);

        change_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MainActivity.myUsername.equals("")){
                    // This work is not completed yet.
                    Toast.makeText(getActivity(),"Coming soon :)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"Please register first!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
