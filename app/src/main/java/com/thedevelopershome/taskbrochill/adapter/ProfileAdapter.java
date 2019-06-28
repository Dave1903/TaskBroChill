package com.thedevelopershome.taskbrochill.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.thedevelopershome.taskbrochill.R;
import com.thedevelopershome.taskbrochill.activity.DownloadActivity;
import com.thedevelopershome.taskbrochill.model.PostModel;
import com.thedevelopershome.taskbrochill.model.ProfileModel;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {

    private Context context;
    private List<ProfileModel> profileModels;

    public ProfileAdapter(Context context, List<ProfileModel> profileModels) {
        this.context = context;
        this.profileModels = profileModels;
    }

    @NonNull
    @Override
    public ProfileAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.name_item,viewGroup,false);

        return new ProfileAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.post.setImageResource(profileModels.get(i).getImage());
        final int j=i;
        myViewHolder.print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DownloadActivity.class);
                intent.putExtra("var",j);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profileModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView post;
        Button print;
        public MyViewHolder(View itemView) {
            super(itemView);

            post = itemView.findViewById(R.id.postimage);
            print = itemView.findViewById(R.id.print_button);


        }

    }
}
