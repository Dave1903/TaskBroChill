package com.thedevelopershome.taskbrochill.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thedevelopershome.taskbrochill.R;
import com.thedevelopershome.taskbrochill.adapter.PostAdapter;
import com.thedevelopershome.taskbrochill.adapter.ProfileAdapter;
import com.thedevelopershome.taskbrochill.model.ProfileModel;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {

    RecyclerView recyclerView;
    ProfileAdapter profileAdapter;
    List<ProfileModel> profileModels;

    private int data[]={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        profileModels=new ArrayList<>();
        for(int i=0;i<data.length;i++){
            profileModels.add(new ProfileModel(data[i]));
        }

        recyclerView = view.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        profileAdapter = new ProfileAdapter(getActivity(), profileModels);
        recyclerView.setAdapter(profileAdapter);
        return  view;

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
