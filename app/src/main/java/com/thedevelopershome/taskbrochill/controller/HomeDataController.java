package com.thedevelopershome.taskbrochill.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.paginate.Paginate;
import com.paginate.abslistview.LoadingListItemCreator;
import com.thedevelopershome.taskbrochill.R;
import com.thedevelopershome.taskbrochill.adapter.PostAdapter;
import com.thedevelopershome.taskbrochill.model.PostModel;
import com.thedevelopershome.taskbrochill.otherClasses.EndlessRecyclerOnScrollListener;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeDataController {

    ProgressDialog dialog;
    Context context;

    int pageNumber;

    RecyclerView recyclerView;
    PostAdapter postAdapter;
     List<PostModel> postModels;




    public HomeDataController(Context context){
        this.context=context;
        pageNumber=1;
        postModels=new ArrayList<>();
        dialog=new ProgressDialog(context);
        dialog.setTitle("Please Wait...");
        dialog.show();

    }




    public void getData(String url, final View view) {
        //final List<PostModel> postModels=new ArrayList<>();


        StringRequest getRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dialog.dismiss();
                        String objectString[] = response.split("\"");


                        if (objectString.length > 0) {

                            for (int i = 1; i < objectString.length; i = i + 2) {
                                objectString[i]=objectString[i].replace("\\","");
                                PostModel postModel = new PostModel();
                                postModel.setPost(objectString[i]);
                                postModels.add(postModel);
                            }

                            TextView emptyView = (TextView) view.findViewById(R.id.empty_view);
                            emptyView.setVisibility(view.GONE);
                            recyclerView = view.findViewById(R.id.recycler);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());

                            postAdapter = new PostAdapter(context, postModels);
                            recyclerView.setAdapter(postAdapter);


                            recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {

                                @Override
                                public void onScrolledToEnd() {
                                    boolean loading=false;
                                    if (!loading) {
                                        loading = true;
                                        pageNumber++;
                                       getMoreData("https://cultquiz.com/test/images?page="+pageNumber,view);
                                        // add 10 by 10 to tempList then notify changing in data
                                    }
                                    loading = false;
                                }
                            });

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when get error
                        Snackbar.make(view,"Error.",Snackbar.LENGTH_LONG).show();

                    }
                }
        );


        AppController.getInstance().addToRequestQueue(getRequest);

    }

    public void getMoreData(String url,final View view){
        postModels=new ArrayList<>();

        dialog.setTitle("Please Wait...");
        dialog.show();



        StringRequest getRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dialog.dismiss();
                        String objectString[] = response.split("\"");


                        if (objectString.length > 0) {

                            for (int i = 1; i < objectString.length; i = i + 2) {
                                objectString[i]=objectString[i].replace("\\","");
                                PostModel postModel = new PostModel();
                                postModel.setPost(objectString[i]);
                                postModels.add(postModel);
                            }

                            TextView emptyView = (TextView) view.findViewById(R.id.empty_view);

// ...

                            if (postModels.isEmpty()) {
                                recyclerView.setVisibility(View.GONE);
                                emptyView.setVisibility(View.VISIBLE);
                            }
                            else{
                                emptyView.setVisibility(View.GONE);
                            recyclerView = view.findViewById(R.id.recycler);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());

                            postAdapter = new PostAdapter(context, postModels);
                            postAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(postAdapter); }


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when get error
                        Snackbar.make(view,"Error.",Snackbar.LENGTH_LONG).show();

                    }
                }
        );


        AppController.getInstance().addToRequestQueue(getRequest);

    }
}
