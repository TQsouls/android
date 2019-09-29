package com.example.myapplication2.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myapplication2.R;

public class LoginFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_login,container,false);
    }
}
