package com.example.voting.ui.account;

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final TextView show_username = getActivity().findViewById(R.id.show_username);
        btnShowUsername = getActivity().findViewById(R.id.show_username_button);
        btnShowUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.myUsername!=""){
                    show_username.setText(MainActivity.myUsername);}
                else {
                    Toast.makeText(getActivity(),"Please register first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
